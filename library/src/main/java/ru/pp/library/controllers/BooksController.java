package ru.pp.library.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import ru.pp.library.dto.BookDTO;
import ru.pp.library.dto.PersonDTO;
import ru.pp.library.dto.RichBookDTO;
import ru.pp.library.entities.Book;
import ru.pp.library.entities.Person;
import ru.pp.library.services.BooksService;

@RestController
@RequestMapping("/books")
public class BooksController extends AbstractController {

    private BooksService booksService;
    private ModelMapper modelMapper;

    @Autowired
    public BooksController(BooksService booksService, ModelMapper modelMapper) {
        this.booksService = booksService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<BookDTO> getBooks() {
        return booksService.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable("id") int id) {
        return convertToRichDTO(booksService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid RichBookDTO richBookDTO,
        BindingResult bindingResult) {

        throwInvalidIfHasErrors(bindingResult);

        booksService.save(convertToEntity(richBookDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id,
                                             @RequestBody @Valid RichBookDTO richBookDTO,
                                             BindingResult bindingResult) {

        throwInvalidIfHasErrors(bindingResult);

        booksService.update(id, convertToEntity(richBookDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}/assign")
    public ResponseEntity<HttpStatus> assign(@PathVariable("id") int id, @RequestBody PersonDTO personDTO) {
        booksService.assign(id, modelMapper.map(personDTO, Person.class));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}/release")
    public ResponseEntity<HttpStatus> release(@PathVariable("id") int id) {
        booksService.release(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private BookDTO convertToDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    private RichBookDTO convertToRichDTO(Book book) {
        return modelMapper.map(book, RichBookDTO.class);
    }

    private Book convertToEntity(RichBookDTO richBookDTO) {
        return modelMapper.map(richBookDTO, Book.class);
    }
}
