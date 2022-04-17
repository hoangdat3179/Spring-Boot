package vn.techmaster.JobHunt.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import vn.techmaster.JobHunt.model.Employer;
import vn.techmaster.JobHunt.request.EmployerRequest;
import vn.techmaster.JobHunt.respository.EmployerRepo;
import vn.techmaster.JobHunt.service.StorageService;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    @Autowired
    private EmployerRepo employerRepo;
    @Autowired
    private StorageService storageService;

    @GetMapping
    public String listAllEmployer(Model model) {
        model.addAttribute("employers", employerRepo.getAll());
        return "employers";
    }

    @GetMapping(value = "/{id}")
    public String showEmployerDetailById(Model model, @PathVariable String id) {
        model.addAttribute("employer", employerRepo.findById(id));
        return "employer";
    }

    @GetMapping(value = "/add")
    public String addEmployerForm(Model model) {
        model.addAttribute("employer", new EmployerRequest("", "", "", "", null));
        return "employer_add";
    }

    @PostMapping(value = "/add", consumes = { "multipart/form-data" })
    public String addEmployer(@Valid @ModelAttribute("employer") EmployerRequest employerRequest, BindingResult result,
            Model model) {
        if (employerRequest.logo().getOriginalFilename().isEmpty()) {
            result.addError(new FieldError("employer", "logo", "logo is required"));
        }
        // Nếu có lỗi trả về trình duyệt
        if (result.hasErrors()) {
            System.out.println("ERROR:" + result.toString());
            return "employer_add";
        }

        // Thêm vào cơ sở dữ liệu
        Employer emp = employerRepo.add(Employer.builder().name(employerRequest.name())
                .website(employerRequest.website()).email(employerRequest.email()).build());
        // Lưu logo vào ổ cứng
        try {
            String logoFileName = storageService.saveFile(employerRequest.logo(), emp.getId());
            employerRepo.updateLogo(emp.getId(), logoFileName);
        } catch (IOException e) {
            //Nếu lưu file bị lỗi thì hãy xóa bản ghi Employer
            e.printStackTrace();
        }

        return "redirect:/employer";
    }
    @GetMapping(value = "/delete/{id}")
    public String deleteEmployerById(@PathVariable String id) {
        Employer emp = employerRepo.deleteById(id);
        storageService.deleteFile(emp.getLogo_path());
        return "redirect:/employer";
    }

}
