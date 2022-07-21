package com.olnester.bookslist.controller;

import com.olnester.bookslist.entity.BooksEntity;
import com.olnester.bookslist.exception.BookAlreadyExists;
import com.olnester.bookslist.exception.BookNotFoundException;
import com.olnester.bookslist.service.BooksService;
import java.security.SecureRandom;
import java.util.List;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BooksController {

    @Value("${welcome.message}")
    private String message;

    @Autowired
    private BooksService booksService;

    @PostMapping
    public String addBook(@ModelAttribute("addbookform") BooksEntity booksEntity) {
        try {
            booksService.addBook(booksEntity);
            return "addbook";
        } catch (BookAlreadyExists e) {
            return "addbook";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook2(@PathVariable("id") Long id) {
        try {
            booksService.deleteBook(id);
            return "redirect:/books";
        } catch (BookNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        try {
            booksService.deleteBook(id);
            return "redirect:/books";
        } catch (BookNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public String getAllBooks(Model model) {
        List<BooksEntity> books = booksService.getAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("addbookform", new BooksEntity());
        return "bookslist";
    }

    @PutMapping("/{id}")
    public String updateBook2(BooksEntity booksEntity, @PathVariable Long id, Model model) {
        try {
            BooksEntity book = booksService.getBookById(id);

            model.addAttribute("book", book);

            booksService.editBook(booksEntity, id);
            return "editbook";
        } catch (BookNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/addbook")
    public String addBook(Model model) {
        model.addAttribute("addbookform", new BooksEntity());
        return "addbook";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("savebook") BooksEntity booksEntity) {
        try {
            booksService.addBook(booksEntity);
            return "redirect:/books";
        } catch (BookAlreadyExists e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/randombook")
    public String addRandomBook() {
        BooksEntity books = new BooksEntity();
        books.setBookName(randomString(10));
        books.setAuthor(randomString(10));
        books.setPrice(randomNumber(5));
        try {
            booksService.addBook(books);
        } catch (BookAlreadyExists e) {
            throw new RuntimeException(e);
        }
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String editPage(Model model, @PathVariable("id") Long id) {
        try {
            BooksEntity book = booksService.getBookById(id);
            model.addAttribute("book", book);
        } catch (BookNotFoundException e) {
            throw new RuntimeException(e);
        }

        return "editbook";
    }


    public static String randomString(int count) {
        return new RandomString(count).nextString();
    }

    public Double randomNumber(int count) {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(1000);
        return Double.valueOf(String.format("%03d", num));
    }

}