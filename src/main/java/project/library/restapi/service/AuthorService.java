package project.library.restapi.service;

import project.library.restapi.dto.books.AuthorDTO;
import project.library.restapi.model.Author;
import project.library.restapi.model.Book;
import project.library.restapi.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;

    @Transactional(readOnly = true)
    public List<AuthorDTO> findAll() {
        return repository.findAll().stream().map(author -> {
            AuthorDTO dto = new AuthorDTO();
            dto.setId(author.getId());
            dto.setFirstName(author.getFirstName());
            dto.setLastName(author.getLastName());
            dto.setBiography(author.getBiography());
            dto.setBookTitles(
                author.getBooks().stream()
                      .map(Book::getTitle)
                      .toList()
            );
            return dto;
        }).toList();
    }

    public Author save(AuthorDTO dto) {
        Author author = new Author();
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        author.setBiography(dto.getBiography());
        return repository.save(author);
    }
}
