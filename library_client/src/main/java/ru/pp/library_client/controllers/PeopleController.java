package ru.pp.library_client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pp.library_client.communication.PeopleCommunication;
import ru.pp.library_client.dto.RichPersonDTO;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleCommunication peopleCommunication;

    @Autowired
    public PeopleController(PeopleCommunication peopleCommunication) {
        this.peopleCommunication = peopleCommunication;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", peopleCommunication.getPeople());
        return "people/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleCommunication.getPerson(id));
        return "people/person";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new RichPersonDTO());
        return "people/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleCommunication.getPerson(id));
        return "people/edit";
    }

    @PostMapping
    public String create(@ModelAttribute("person") RichPersonDTO person) {
        peopleCommunication.save(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleCommunication.delete(id);
        return "redirect:/people";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") RichPersonDTO person) {
        peopleCommunication.save(person);
        return "redirect:/people";
    }
}
