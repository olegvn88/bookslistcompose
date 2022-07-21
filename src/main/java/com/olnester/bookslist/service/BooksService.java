package com.olnester.bookslist.service;

import com.olnester.bookslist.entity.BooksEntity;
import com.olnester.bookslist.exception.BookAlreadyExists;
import com.olnester.bookslist.exception.BookNotFoundException;
import com.olnester.bookslist.repository.BooksRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

    public static final String BOOK_ALREADY_EXISTS = "Book already exists";
    public static final String BOOK_NOT_FOUND = "Book not found";
    @Autowired
    private BooksRepo booksRepo;

    public BooksEntity addBook(BooksEntity booksEntity) throws BookAlreadyExists {
        Optional<BooksEntity> books = booksRepo.findByBookName(booksEntity.getBookName());
        if (books.isPresent()) {
            throw new BookAlreadyExists(BOOK_ALREADY_EXISTS);
        }
        return booksRepo.save(booksEntity);
    }

    public BooksEntity getBookById(Long id) throws BookNotFoundException {
        Optional<BooksEntity> book = booksRepo.findById(id);
        if (book.isEmpty()) {
            throw new BookNotFoundException(BOOK_NOT_FOUND);
        }
        return book.get();
    }

    public void deleteBook(Long id) throws BookNotFoundException {
        Optional<BooksEntity> book = booksRepo.findById(id);
        if (book.isEmpty()) {
            throw new BookNotFoundException(BOOK_NOT_FOUND);
        }
        booksRepo.deleteById(id);
    }

    public List<BooksEntity> getAllBooks() {
        return booksRepo.findAll();
    }

    public BooksEntity editBook(BooksEntity booksEntity, Long id) throws BookNotFoundException {
        Optional<BooksEntity> books = booksRepo.findById(id);
        if (books.isEmpty()) {
            throw new BookNotFoundException(BOOK_NOT_FOUND);
        }

        BooksEntity book = books.get();
        book.setAuthor(booksEntity.getAuthor());
        book.setBookName(booksEntity.getBookName());
        book.setPrice(booksEntity.getPrice());
        booksRepo.save(book);
        return book;
    }
}
