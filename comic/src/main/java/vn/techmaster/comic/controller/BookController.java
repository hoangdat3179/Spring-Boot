package vn.techmaster.comic.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.comic.exception.NotFoundException;
import vn.techmaster.comic.model.Book;
import vn.techmaster.comic.service.IBookService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {
    @Autowired
    private IBookService bookService;

    @Operation(summary = "Get all books")
    @GetMapping(value = "/")
    public ResponseEntity<List<Book>> findAllBooks() {
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{bookId}")
    @Operation(summary = "Get a book by id")
    public ResponseEntity<Book> findBookById(
            @Parameter(description = "id of book to be searched") @PathVariable long bookId) {
        Optional<Book> optionalBook = bookService.findById(bookId);
        if (optionalBook.isPresent()) {
            return ResponseEntity.ok(optionalBook.get()); // return 200, with json body
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
        }
    }

    @PostMapping("/")
    @Operation(summary = "Create a new book")
    public ResponseEntity<Book> addBook(@Parameter(description = "book to be created") @RequestBody Book book) {
        Book newBook = bookService.save(book);
        try {
            return ResponseEntity.created(new URI("/api/books/" + newBook.getId())).body(newBook);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{bookId}")
    @Operation(summary = "Update a book by id", parameters = {
            @Parameter(name = "bookId", in = ParameterIn.PATH, required = true, description = "id của sách") })
    public ResponseEntity<Void> updateBook(@RequestBody Book book, @PathVariable long bookId) {
        try {
            bookService.update(bookId, book);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{bookId}")
    public ResponseEntity<Void> deleteBookById(@PathVariable long bookId) {
        try {
            bookService.deleteById(bookId);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
