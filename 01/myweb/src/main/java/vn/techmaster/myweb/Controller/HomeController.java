package vn.techmaster.myweb.Controller;

import java.util.List;
import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.myweb.Repository.ListStudent;
import vn.techmaster.myweb.model.BMI;
import vn.techmaster.myweb.model.Student;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RestController
    
public class HomeController {
    // Bài 1
    @GetMapping("/random/{length}")
    @ResponseBody
    public String randomString(@PathVariable("length") int length) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

    // Bài 2
    @GetMapping(value = "/quote", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String quote() {
        String[] quote = { "Kiến tha lâu đầy tổ", "Có công mài sắt, có ngày nên kim", "Không thầy đố mày làm nên",
                "Học thầy không tày học bạn" };
        int rnd = new Random().nextInt(quote.length);
        return quote[rnd];
    }

    // BÀi 3
    @PostMapping(value = "/bmi", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BMI caculatorBMI(@RequestBody BMI bmi) {
        return bmi;
    }

    // Bài 4    
    @GetMapping(value = "/student", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Student> getStudent(ListStudent student) {
        return student.getStudent();
    }
}
