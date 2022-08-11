package vn.techmaster.woodshop.controller.admin;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.techmaster.woodshop.dto.CategoryDTO;
import vn.techmaster.woodshop.dto.ProductDTO;
import vn.techmaster.woodshop.entity.Product;
import vn.techmaster.woodshop.service.CategoryServiceIpml;
import vn.techmaster.woodshop.service.ProductServiceIpml;
import vn.techmaster.woodshop.service.StorageService;
import vn.techmaster.woodshop.utils.FileUploadUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("admin/products")
public class ProductController {

    @Autowired
    private CategoryServiceIpml categoryServiceIpml;

    @Autowired
    private ProductServiceIpml productServiceIpml;

    @Autowired
    StorageService storageService;

    ModelMapper modelMapper = new ModelMapper();

    @ModelAttribute("categories")
    public List<CategoryDTO> getCategories() {
        return categoryServiceIpml.findAll().stream()
                .map(item -> {
                    CategoryDTO dto = new CategoryDTO();
                    modelMapper.map(item, dto);
                    return dto;
                }).toList();
    }

    @GetMapping()
    public String getAll(Model model) {
        return  listByPage(1,model,"");
    }

    @GetMapping("/saveOrEdit")
    public String showSaveOrEditForm(Model model) {
        ProductDTO dto = new ProductDTO();
        dto.setIsCheck(false);
        model.addAttribute("product", dto);
        return "admin/products/form-add-edit-product";
    }

    @PostMapping("/save")
    public ModelAndView saveProduct(ModelMap model, @Valid @ModelAttribute("product") ProductDTO dto,
                                    BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return new ModelAndView("admin/products/form-add-edit-product") ;
        }
        dto.setCreated_at(new Date());
        dto.setUpdate_at(new Date());
        Product entity = new Product();
        modelMapper.map(dto, entity);
        entity.setCategory(categoryServiceIpml.findById(dto.getCategory_id()).get());

        if (!dto.getImageMultipartFile().isEmpty()) {
            String fileName = StringUtils.cleanPath(dto.getImageMultipartFile().getOriginalFilename());
            entity.setImage(fileName);

            String uploadDir = "product-photos/" + entity.getId();
            try {
                FileUploadUtils.saveFile(uploadDir, fileName, dto.getImageMultipartFile());
            } catch (IOException e) {
                throw new IOException("Could not file upload :" + fileName);
            }
        }
        productServiceIpml.save(entity);
        model.addAttribute("message", "Lưu sản phẩm thành công");
        return new ModelAndView("redirect:/admin/products" , model) ;
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(ModelMap model, @PathVariable("id") String id) {
        Optional<Product> product = productServiceIpml.findById(id);
        ProductDTO dto = new ProductDTO();

        if (product.isPresent()) {
            Product entity = product.get();
            BeanUtils.copyProperties(entity, dto);

            dto.setCategory_id(entity.getCategory().getId());
            dto.setUpdate_at(new Date());
            dto.setIsCheck(true);
            model.addAttribute("product", dto);
            return new ModelAndView("admin/products/form-add-edit-product", model);
        }
        model.addAttribute("message", "Cập nhật thất bại");
        return new ModelAndView("forward:/admin/products", model);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteProduct(ModelMap model, @PathVariable("id") String id)
            throws IOException {
        Optional<Product> opt = productServiceIpml.findById(id);
        if (opt.isPresent()) {
            if (!StringUtils.isEmpty(opt.get().getImage())) {
                storageService.delete(opt.get().getImage());
            }
            productServiceIpml.delete(opt.get());
            model.addAttribute("message", "Xóa thành công");
        } else {
            model.addAttribute("message", "Xóa thất bại");
        }
        return new ModelAndView("forward:/admin/products", model);
    }

    @GetMapping("/search")
    public String search (Model model , @RequestParam("keyword") String keyword) {
        return listByPage(1, model , keyword) ;
    }
    @GetMapping("/{pageNumber}")
    public String listByPage(@PathVariable("pageNumber") int pageNumber, Model model,
                             @RequestParam("keyword") String keyword){
        int currentPage = pageNumber;

        Page<Product> page ;
        Pageable pageable = PageRequest.of(pageNumber-1,3);
        page = productServiceIpml.findByNameContaining(keyword,pageable);

        List<Product> productList = page.getContent();
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("productList",productList);
        model.addAttribute("keyword",keyword);
        return "admin/products/table-data-product";
    }

    @GetMapping("product-photos/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serverFile(@PathVariable String fileName) {
        Resource file = storageService.loadAsResource(fileName);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; fileName=\"" + file.getFilename() + "\"").body(file);
    }
}
