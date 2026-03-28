package project.library.restapi.controller;

import project.library.restapi.dto.books.CategoryDTO;
import project.library.restapi.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/categories")
@Tag(name="Categories", description="Categories management - admin only")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping
    public List<CategoryDTO> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Object create(@Valid @RequestBody CategoryDTO dto) {
        return service.save(dto);
    }
}
