package project.library.restapi.controller;

import project.library.restapi.dto.books.AuthorDTO;
import project.library.restapi.model.Author;
import project.library.restapi.service.AuthorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService service;

    @GetMapping
    public List<Author> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Object create(@Valid @RequestBody AuthorDTO dto) {
        return service.save(dto);
    }
}
