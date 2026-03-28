package project.library.restapi.service;

import project.library.restapi.dto.books.BookDTO;
import project.library.restapi.model.Author;
import project.library.restapi.model.Book;
import project.library.restapi.model.Category;
import project.library.restapi.repository.AuthorRepository;
import project.library.restapi.repository.BookRepository;
import project.library.restapi.repository.CategoryRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public BookDTO findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return toDTO(book);
    }

    public BookDTO save(BookDTO dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow();

        Set<Category> categories = new HashSet<>();
        if (dto.getCategoryIds() != null) {
            categories = new HashSet<>(categoryRepository.findAllById(dto.getCategoryIds()));
        }

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPublicationDate(dto.getPublicationDate());
        book.setAvailable(dto.isAvailable());
        book.setAuthor(author);
        book.setCategories(categories);

        Book saved = bookRepository.save(book);
        return toDTO(saved);
    }

    private BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setPublicationDate(book.getPublicationDate());
        dto.setAvailable(book.isAvailable());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setCategoryIds(book.getCategories().stream().map(Category::getId).collect(Collectors.toSet()));
        return dto;
    }
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}

