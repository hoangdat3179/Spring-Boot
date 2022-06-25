package vn.techmaster.comic.service;

import vn.techmaster.comic.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
  List<Book> findAll();

  Optional<Book> findById(Long id);

  Book save(Book book);

  void update(long id, Book book);

  void deleteById(long id);
}
