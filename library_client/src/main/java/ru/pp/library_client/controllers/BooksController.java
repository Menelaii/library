package ru.pp.library_client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pp.library_client.communication.AuthorsCommunication;
import ru.pp.library_client.communication.BooksCommunication;
import ru.pp.library_client.communication.GenresCommunication;
import ru.pp.library_client.communication.LibraryCardsCommunication;
import ru.pp.library_client.dto.LibraryCardDTO;
import ru.pp.library_client.dto.RichBookDTO;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksCommunication booksCommunication;
    private final LibraryCardsCommunication libraryCardsCommunication;
    private final GenresCommunication genresCommunication;
    private final AuthorsCommunication authorsCommunication;

    @Autowired
    public BooksController(BooksCommunication booksCommunication, LibraryCardsCommunication libraryCardsCommunication, GenresCommunication genresCommunication, AuthorsCommunication authorsCommunication) {
        this.booksCommunication = booksCommunication;
        this.libraryCardsCommunication = libraryCardsCommunication;
        this.genresCommunication = genresCommunication;
        this.authorsCommunication = authorsCommunication;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", booksCommunication.getBooks());
        return "books/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        RichBookDTO book = booksCommunication.getBook(id);
        model.addAttribute("book", book);

        if(book.getCurrentOwner() == null) {
            model.addAttribute("libraryCards", libraryCardsCommunication.getLibraryCards());
            model.addAttribute("libraryCard", new LibraryCardDTO());
        }

        return "books/book";
    }

    @GetMapping("/new")
    public String newBook(Model model, @ModelAttribute("book") RichBookDTO book) {
        model.addAttribute("genres", genresCommunication.getGenres());
        model.addAttribute("authors", authorsCommunication.getAuthors());
        return "books/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksCommunication.getBook(id));
        model.addAttribute("genres", genresCommunication.getGenres());
        model.addAttribute("authors", authorsCommunication.getAuthors());
        return "books/edit";
    }

    @PostMapping
    public String create(@ModelAttribute("book") RichBookDTO book) {
        booksCommunication.save(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") RichBookDTO book) {
        booksCommunication.save(book);
        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksCommunication.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("libraryCard") LibraryCardDTO libraryCardDTO) {
        booksCommunication.assign(id, libraryCardDTO);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        booksCommunication.release(id);
        return "redirect:/books/" + id;
    }
}
