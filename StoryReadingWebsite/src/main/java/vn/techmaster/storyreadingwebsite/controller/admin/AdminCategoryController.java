package vn.techmaster.storyreadingwebsite.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.techmaster.storyreadingwebsite.entity.Category;
import vn.techmaster.storyreadingwebsite.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminCategoryController {

    @Autowired
    private CategoryRepository categoryRepo;

    //Lấy danh sách thể loại
    @GetMapping("/categories")
    public String showCategoryList(Model model){
        List<Category> listCategories = categoryRepo.findAll();
        model.addAttribute("listCategories",listCategories);
        return "/admin/categories";
    }


    //Thêm thể loại
    @GetMapping("/categories/new")
    public String showCreateNewCategoryFrom(Model model){
        model.addAttribute("category", new Category());
        return "/admin/category_form";
    }

    @PostMapping("/categories/save")
    public String saveCategory(Category category){
        categoryRepo.save(category);
        return "redirect:/admin/categories";
    }


    // Sửa thể loại
    @GetMapping("/categories/edit/{id}")
    public String showEditCategoryForm(@PathVariable("id") Long id, Model model){
        Category category = categoryRepo.findById(id).get();
        model.addAttribute("category", category);
        return "/admin/category_form";
    }


    //Xóa thể loại
    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        categoryRepo.deleteById(id);
        return "redirect:/admin/categories";
    }
}
