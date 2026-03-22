package project.library.restapi.service;

import project.library.restapi.dto.books.AuthorDTO;
import project.library.restapi.model.Author;
import project.library.restapi.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;

    public List<Author> findAll() {
        return repository.findAll();
    }

    public Author save(AuthorDTO dto) {
        Author author = new Author();
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        author.setBiography(dto.getBiography());
        return repository.save(author);
    }
}
