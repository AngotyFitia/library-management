package project.library.restapi.service;

import project.library.restapi.dto.books.CategoryDTO;
import project.library.restapi.model.Book;
import project.library.restapi.model.Category;
import project.library.restapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        return repository.findAll().stream().map(category -> {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(category.getId());
            dto.setName(category.getName());
            dto.setDescription(category.getDescription());
            dto.setBookTitles(
                category.getBooks().stream()
                        .map(Book::getTitle)
                        .toList()
            );
            return dto;
        }).toList();
    }

    public Category save(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return repository.save(category);
    }
}
