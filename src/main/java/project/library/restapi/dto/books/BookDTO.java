package project.library.restapi.dto.books;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
public class BookDTO {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String isbn;

    @NotNull
    private LocalDate publicationDate;

    private boolean available;

    @NotNull
    private Long authorId;            

    private Set<Long> categoryIds;
}
