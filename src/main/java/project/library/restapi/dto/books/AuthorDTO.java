package project.library.restapi.dto.books;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class AuthorDTO {
    private Long id;

    @NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    @NotBlank
    private String biography;

    private List<String> bookTitles;

    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getBiography() {
        return biography;
    }
    public List<String> getBookTitles(){
        return bookTitles;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }  
    public void setBiography(String biography) {
        this.biography = biography;
    }
    public void setBookTitles(List<String> bookTitles){
        this.bookTitles=bookTitles;
    }

}