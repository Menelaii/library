package ru.pp.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pp.library.entities.Author;
import ru.pp.library.services.AuthorsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController extends AbstractController {

    private AuthorsService authorsService;

    @Autowired
    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping
    public List<Author> getGenres() {
        return authorsService.findAll();
    }

    @GetMapping("/{id}")
    public Author getGenre(@PathVariable("id") int id) {
        return authorsService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Author author,
                                             BindingResult bindingResult) {

        throwInvalidIfHasErrors(bindingResult);

        authorsService.save(author);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        authorsService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody @Valid Author author,
                                             BindingResult bindingResult) {

        throwInvalidIfHasErrors(bindingResult);

        authorsService.update(id, author);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
