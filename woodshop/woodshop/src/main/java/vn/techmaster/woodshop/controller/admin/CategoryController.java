package vn.techmaster.woodshop.controller.admin;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.techmaster.woodshop.dto.CategoryDTO;
import vn.techmaster.woodshop.entity.Category;
import vn.techmaster.woodshop.entity.Status;
import vn.techmaster.woodshop.service.CategoryServiceIpml;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
    @Autowired
    private CategoryServiceIpml categoryServiceIpml;

    @GetMapping(value = "saveOrEdit")
    public String saveOrEdit(Model model) {
        model.addAttribute("category", new CategoryDTO());
        return "admin/categories/form-add-edit-category";
    }

    @PostMapping(value = "/save")
    private ModelAndView AddCategory(@Valid @ModelAttribute("category") CategoryDTO dto, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return new ModelAndView("admin/categories/form-add-edit-category");
        }
        Category entity = new Category();
        dto.setUpdated_at(new Date());
        if (dto.getIsCheck()) {
            dto.setCreated_at(categoryServiceIpml.findById(dto.getId()).get().getCreated_at());
            Optional<Category> dtb = categoryServiceIpml.findById(dto.getId());
            if (!dto.getName().equalsIgnoreCase(dtb.get().getName())) {
                if (categoryServiceIpml.existsByName(dto.getName())) {
                    model.addAttribute("message", "Tên danh mục đã tồn tại");
                    return new ModelAndView("forward:/admin/categories", model);
                }
            }
            if (!dto.getCode().equalsIgnoreCase(dtb.get().getCode())) {
                if (categoryServiceIpml.existsByCode(dto.getCode())) {
                    model.addAttribute("message", "Code danh mục đã tồn tại");
                    return new ModelAndView("forward:/admin/categories", model);
                }
            }
        } else {
            dto.setCreated_at(new Date());
            if (categoryServiceIpml.existsByNameOrCode(dto.getName(), dto.getCode())) {
                model.addAttribute("message", "Danh mục đã tồn tại");
                return new ModelAndView("forward:/admin/categories", model);
            }
        }
        BeanUtils.copyProperties(dto, entity);
        for (Status s : Status.values()) {
            if (s.name().equals(dto.getStatus())) {
                entity.setStatus(s);
            }
        }
        categoryServiceIpml.save(entity);
        model.addAttribute("message", "Lưu danh mục thành công");
        return new ModelAndView("forward:/admin/categories", model);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCategory (ModelMap model , @PathVariable("id") String id ) {
        Optional<Category> category = categoryServiceIpml.findById(id) ;
        CategoryDTO categoryDTO = new CategoryDTO() ;
        if(category.isPresent()) {
            Category entity = category.get();
            BeanUtils.copyProperties(entity , categoryDTO);
            categoryDTO.setIsCheck(true);
            model.addAttribute("category" , categoryDTO) ;
            return new ModelAndView("admin/categories/form-add-edit-category" , model) ;
        }

        model.addAttribute("message" , "Không tồn tại danh mục này ") ;
        return new ModelAndView("forward:/admin/categories" , model) ;
    }

    @GetMapping(value = "delete/{id}")
    public ModelAndView deleteCategoryById(@PathVariable("id") String id , ModelMap model) {
        Optional<Category> category = categoryServiceIpml.findById(id) ;
        Category entity = categoryServiceIpml.findById(id).get();
        if(category.isPresent()) {
            entity.setStatus(Status.NGUNGBAN);
            entity.setUpdated_at(new Date());
            categoryServiceIpml.addCategory(entity) ;
            model.addAttribute("message" , "Xóa danh mục thành công") ;
            return new ModelAndView("forward:/admin/categories" , model) ;
        }
        model.addAttribute("mesage" , "Không tìm thấy danh mục này ") ;
        return new ModelAndView("forward:/admin/categories" , model) ;

    }

    @GetMapping("/search")
    public String findCategoryByName(Model model, @RequestParam(name = "name", required = false) String name) {
        List<Category> categoryList = null;

        if (StringUtils.hasText(name)) {
            categoryList = categoryServiceIpml.findByNameContaining(name);
        } else {
            categoryList = (List<Category>) categoryServiceIpml.findAll();
        }
        model.addAttribute("categories", categoryList);

        return "admin/categories/table-data-category";
    }

    @RequestMapping("")
    private String list(ModelMap model,
                        @RequestParam(name = "name", required = false) String name,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage-1,pageSize, Sort.by("name"));
        Page<Category> resultPage = null;

        if(StringUtils.hasText(name)){
            resultPage = categoryServiceIpml.findByNameContaining(name,pageable);
            model.addAttribute("name", name);
        }
        else
            resultPage = categoryServiceIpml.findAll(pageable);

        int totalPages = resultPage.getTotalPages();
        if(totalPages > 0){
            int start = Math.max(1, currentPage-2);
            int end = Math.min(currentPage+2, totalPages);

            if(totalPages > 4){
                if(end == totalPages) start = end-4;
                else if (start == 1) end = start+4;
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("categoryPage", resultPage);
        return "admin/categories/table-data-category";
    }
}
