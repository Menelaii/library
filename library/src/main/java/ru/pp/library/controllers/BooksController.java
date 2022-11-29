package ru.pp.library.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.pp.library.entities.Book;
import ru.pp.library.entities.Person;
import ru.pp.library.services.BooksService;

@RestController
@RequestMapping("/books")
public class BooksController extends AbstractController {

    private BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return booksService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") int id) {
        return booksService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Book book,
        BindingResult bindingResult) {

        throwInvalidIfHasErrors(bindingResult);

        booksService.save(book);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody @Valid Book book,
        BindingResult bindingResult) {

        throwInvalidIfHasErrors(bindingResult);

        booksService.update(id, book);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}/assign")
    public ResponseEntity<HttpStatus> assign(@PathVariable("id") int id, @RequestBody Person person) {
        booksService.assign(id, person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}/release")
    public ResponseEntity<HttpStatus> release(@PathVariable("id") int id) {
        booksService.release(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
