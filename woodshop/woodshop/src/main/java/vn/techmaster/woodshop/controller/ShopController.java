package vn.techmaster.woodshop.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.woodshop.dto.CategoryDTO;
import vn.techmaster.woodshop.entity.Product;
import vn.techmaster.woodshop.service.CategoryServiceIpml;
import vn.techmaster.woodshop.service.ProductServiceIpml;
import vn.techmaster.woodshop.service.StorageService;
import java.util.List;

@Controller
@RequestMapping("client/shop")
public class ShopController {

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

    @GetMapping("/find")
    public String search (Model model , @RequestParam("keyword") String keyword) {
        return listByPage(1, model , keyword) ;
    }
    @GetMapping("/{pageNumber}")
    public String listByPage(@PathVariable("pageNumber") int pageNumber, Model model,
                             @RequestParam("keyword") String keyword){
        int currentPage = pageNumber;

        Page<Product> page ;
        Pageable pageable = PageRequest.of(pageNumber-1,6);
        page = productServiceIpml.findByNameContaining(keyword,pageable);

        List<Product> productList = page.getContent();
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("productList",productList);
        model.addAttribute("keyword",keyword);
        return "client/shop";
    }

    @GetMapping("product-photos/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serverFile(@PathVariable String fileName) {
        Resource file = storageService.loadAsResource(fileName);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; fileName=\"" + file.getFilename() + "\"").body(file);
    }
}