package ru.pp.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pp.library.entities.Genre;
import ru.pp.library.services.GenresService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenresController extends AbstractController{

    private GenresService genresService;

    @Autowired
    public GenresController(GenresService genresService) {
        this.genresService = genresService;
    }

    @GetMapping
    public List<Genre> getGenres() {
        return genresService.findAll();
    }

    @GetMapping("/{id}")
    public Genre getGenre(@PathVariable("id") int id) {
        return genresService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Genre genre,
                                             BindingResult bindingResult) {

        throwInvalidIfHasErrors(bindingResult);

        genresService.save(genre);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        genresService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody @Valid Genre genre,
                                             BindingResult bindingResult) {

        throwInvalidIfHasErrors(bindingResult);

        genresService.update(id, genre);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
