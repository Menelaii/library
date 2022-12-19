package ru.pp.library_client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pp.library_client.communication.LibraryCardsCommunication;
import ru.pp.library_client.dto.RichLibraryCardDTO;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final LibraryCardsCommunication libraryCardsCommunication;

    @Autowired
    public PeopleController(LibraryCardsCommunication libraryCardsCommunication) {
        this.libraryCardsCommunication = libraryCardsCommunication;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("libraryCards", libraryCardsCommunication.getLibraryCards());
        return "people/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("libraryCard", libraryCardsCommunication.getPerson(id));
        return "people/person";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("libraryCard", new RichLibraryCardDTO());
        return "people/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("libraryCard", libraryCardsCommunication.getPerson(id));
        return "people/edit";
    }

    @PostMapping
    public String create(@ModelAttribute("libraryCard") RichLibraryCardDTO libraryCard) {
        libraryCardsCommunication.save(libraryCard);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        libraryCardsCommunication.delete(id);
        return "redirect:/people";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("libraryCard") RichLibraryCardDTO libraryCard) {
        libraryCardsCommunication.save(libraryCard);
        return "redirect:/people";
    }
}
