package ru.pp.library.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pp.library.dto.GenreDTO;
import ru.pp.library.entities.Genre;
import ru.pp.library.services.GenresService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/genres")
public class GenresController extends AbstractController{

    private final GenresService genresService;
    private final ModelMapper modelMapper;

    @Autowired
    public GenresController(GenresService genresService, ModelMapper modelMapper) {
        this.genresService = genresService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<GenreDTO> getGenres() {
        return genresService.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public GenreDTO getGenre(@PathVariable("id") int id) {
        return convertToDTO(genresService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid GenreDTO genreDTO,
                                             BindingResult bindingResult) {

        throwInvalidIfHasErrors(bindingResult);

        genresService.save(convertToEntity(genreDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        genresService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody @Valid GenreDTO genreDTO,
                                             BindingResult bindingResult) {

        throwInvalidIfHasErrors(bindingResult);

        genresService.update(id, convertToEntity(genreDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private GenreDTO convertToDTO(Genre genre) {
        return modelMapper.map(genre, GenreDTO.class);
    }

    private Genre convertToEntity(GenreDTO genreDTO) {
        return modelMapper.map(genreDTO, Genre.class);
    }
}
