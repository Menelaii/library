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

import ru.pp.library.dto.PersonDTO;
import ru.pp.library.dto.RichPersonDTO;
import ru.pp.library.entities.Person;
import ru.pp.library.services.PeopleService;

@RestController
@RequestMapping("/people")
public class PeopleController extends AbstractController {
    
    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<PersonDTO> getPeople() {
        return peopleService.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RichPersonDTO getPerson(@PathVariable("id") int id) {
        return convertToRichDTO(peopleService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid RichPersonDTO richPersonDTO,
        BindingResult bindingResult) {

        throwInvalidIfHasErrors(bindingResult);

        peopleService.save(convertToEntity(richPersonDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id,
                                             @RequestBody @Valid RichPersonDTO richPersonDTO,
                                             BindingResult bindingResult) {

        throwInvalidIfHasErrors(bindingResult);

        peopleService.update(id, convertToEntity(richPersonDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private PersonDTO convertToDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    private RichPersonDTO convertToRichDTO(Person person) {
        return modelMapper.map(person, RichPersonDTO.class);
    }

    private Person convertToEntity(RichPersonDTO richPersonDTO) {
        return modelMapper.map(richPersonDTO, Person.class);
    }
}
