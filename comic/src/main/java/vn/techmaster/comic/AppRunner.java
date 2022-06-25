package vn.techmaster.comic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.techmaster.comic.service.BookService;

@Component
public class AppRunner implements CommandLineRunner {
    @Autowired
    BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        bookService.generateBook();
    }

}
