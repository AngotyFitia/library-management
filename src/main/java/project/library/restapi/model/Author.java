package project.library.restapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String biography;

    @OneToMany(mappedBy = "author")
    private List<Book> books;


    public Author() {}
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getBiography() {
        return biography;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }
    
}