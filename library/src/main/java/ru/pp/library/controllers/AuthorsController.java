package ru.pp.library.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pp.library.dto.AuthorDTO;
import ru.pp.library.entities.Author;
import ru.pp.library.services.AuthorsService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorsController extends AbstractController {

    private final AuthorsService authorsService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthorsController(AuthorsService authorsService, ModelMapper modelMapper) {
        this.authorsService = authorsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<AuthorDTO> getGenres() {
        return authorsService.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AuthorDTO getGenre(@PathVariable("id") int id) {
        return convertToDTO(authorsService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid AuthorDTO authorDTO,
                                             BindingResult bindingResult) {

        throwInvalidIfHasErrors(bindingResult);

        authorsService.save(convertToEntity(authorDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        authorsService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody @Valid AuthorDTO authorDTO,
                                             BindingResult bindingResult) {

        throwInvalidIfHasErrors(bindingResult);

        authorsService.update(id, convertToEntity(authorDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private AuthorDTO convertToDTO(Author author) {
        return modelMapper.map(author, AuthorDTO.class);
    }

    private Author convertToEntity(AuthorDTO authorDTO) {
        return modelMapper.map(authorDTO, Author.class);
    }
}
