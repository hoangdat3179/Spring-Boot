package vn.techmaster.comic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.comic.exception.NotFoundException;
import vn.techmaster.comic.model.Author;
import vn.techmaster.comic.model.Book;
import vn.techmaster.comic.model.Category;
import vn.techmaster.comic.model.Chapter;
import vn.techmaster.comic.repository.BookRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepo bookRespository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> findAll() {
        return bookRespository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRespository.findById(id);
    }

    @Override
    public Book save(Book book) {
        Book newbook = new Book(book.getTitle(),book.getDescription(),book.getView());
        return bookRespository.save(newbook);
    }

    @Override
    public void update(long id, Book book) {
        Book updatedBook = new Book(book.getTitle(),book.getDescription(),book.getView());
        Optional<Book> optionalBook = bookRespository.findById(id);
        if (optionalBook.isPresent()) {
           bookRespository.save(updatedBook);
        } else {
            throw new NotFoundException("Không tìm thấy truyện");
        }
    }

    @Override
    public void deleteById(long id) {
        Optional<Book> optionalBook = bookRespository.findById(id);
        if (optionalBook.isPresent()) {
            bookRespository.delete(optionalBook.get());
        } else {
            throw new NotFoundException("Không tìm thấy truyện");
        }
    }

        @Transactional
        public void generateBook() {

            Chapter chapter =new Chapter("Chương 1");
            Chapter chapter1 =new Chapter("Chương 1");
            Chapter chapter2 =new Chapter("Chương 2");
            Chapter chapter3 =new Chapter("Chương 2");
            Chapter chapter4 =new Chapter("Chương 3");
            Chapter chapter5 =new Chapter("Chương 1");
            Chapter chapter6 =new Chapter("Chương 2");


            Author author =new Author("Chiêu Tài Tiến Bảo");
            Author author2 =new Author("Thần Đông");
            Author author4 =new Author("Vũ Phong");


            Category category = new Category("Tiên Hiệp");
            Category category1 = new Category("Kiếm Hiệp");
            Category category2 = new Category("Ngôn Tình");
            Category category3 = new Category("Đam Mỹ");
            Category category4 = new Category("Action");

            Book book1 = new Book("MÊ VỢ KHÔNG LỐI VỀ","HH","1000");
            book1.addCategory(category);
            book1.addCategory(category3);
            book1.addAuthor(author);
            book1.addChapter(chapter);
            book1.addChapter(chapter3);

            Book book2 = new Book("LINH VŨ THIÊN HẠ","KKK","1000");
            book2.addCategory(category);
            book2.addCategory(category1);
            book2.addCategory(category4);
            book2.addAuthor(author4);
            book2.addChapter(chapter1);
            book2.addChapter(chapter2);

            Book book3 = new Book("THẾ GIỚI HOÀN MỸ","BBB","1000");
            book3.addCategory(category);
            book3.addCategory(category1);
            book3.addCategory(category2);
            book3.addAuthor(author2);
            book3.addChapter(chapter5);
            book3.addChapter(chapter6);
            book3.addChapter(chapter4);


            em.persist(book1);
            em.persist(book2);
            em.persist(book3);

            em.flush();
        }

    }
