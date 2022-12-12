package ru.pp.library_client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pp.library_client.communication.GenresCommunication;
import ru.pp.library_client.dto.GenreDTO;

@Controller
@RequestMapping("/genres")
public class GenresController {
    private final GenresCommunication genresCommunication;

    @Autowired
    public GenresController(GenresCommunication genresCommunication) {
        this.genresCommunication = genresCommunication;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("genres", genresCommunication.getGenres());
        return "genres/genres";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("genre", genresCommunication.getGenre(id));
        return "genres/genre";
    }

    @GetMapping("/new")
    public String newGenre(Model model) {
        model.addAttribute("genre", new GenreDTO());
        return "genres/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("genre", genresCommunication.getGenre(id));
        return "genres/edit";
    }

    @PostMapping
    public String create(@ModelAttribute("genre") GenreDTO genre) {
        genresCommunication.save(genre);
        return "redirect:/genres";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        genresCommunication.delete(id);
        return "redirect:/genres";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("genre") GenreDTO genre) {
        genresCommunication.save(genre);
        return "redirect:/genres";
    }
}
