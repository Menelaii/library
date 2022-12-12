package ru.pp.library_client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pp.library_client.communication.AuthorsCommunication;
import ru.pp.library_client.dto.AuthorDTO;

@Controller
@RequestMapping("/authors")
public class AuthorsController {

    private final AuthorsCommunication authorsCommunication;

    @Autowired
    public AuthorsController(AuthorsCommunication authorsCommunication) {
        this.authorsCommunication = authorsCommunication;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("authors", authorsCommunication.getAuthors());
        return "authors/authors";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("author", authorsCommunication.getAuthor(id));
        return "authors/author";
    }

    @GetMapping("/new")
    public String newAuthor(Model model) {
        model.addAttribute("author", new AuthorDTO());
        return "authors/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("author", authorsCommunication.getAuthor(id));
        return "authors/edit";
    }

    @PostMapping
    public String create(@ModelAttribute("author") AuthorDTO author) {
        authorsCommunication.save(author);
        return "redirect:/authors";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("author") AuthorDTO author) {
        authorsCommunication.save(author);
        return "redirect:/authors";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        authorsCommunication.delete(id);
        return "redirect:/authors";
    }
}
