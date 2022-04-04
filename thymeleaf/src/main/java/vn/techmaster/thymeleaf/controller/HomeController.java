package vn.techmaster.thymeleaf.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.techmaster.thymeleaf.model.Book;


@Controller
@RequestMapping("/")
public class HomeController {
  @GetMapping
  public String homePage() {
    return "index";
  }

  @GetMapping(value="/json", produces = "application/json")
  @ResponseBody
  public Book returnBook() {
    return new Book("De men phieu luu ky", "To Hoai");
  }
  @GetMapping(value="/xml", produces = MediaType.APPLICATION_XML_VALUE)
  @ResponseBody
  public Book returnBookXML() {
    return new Book("De men phieu luu ky", "To Hoai");
  }
}

