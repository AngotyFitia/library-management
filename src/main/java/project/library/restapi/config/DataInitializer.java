package project.library.restapi.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.library.restapi.model.*;
import project.library.restapi.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BorrowRepository borrowRepository;

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) {
            return;
        }

        // === Users ===
        User admin = User.builder()
                .name("Administrator")
                .email("admin@library.com")
                .password(passwordEncoder.encode("admin123"))
                .role(Role.ADMIN)
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();

        User user = User.builder()
                .name("John Doe")
                .email("user@library.com")
                .password(passwordEncoder.encode("user123"))
                .role(Role.USER)
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.saveAll(List.of(admin, user));

        // === Categories ===
        Category fiction = Category.builder().name("Fiction").description("Romans et récits fictifs").build();
        Category scifi = Category.builder().name("Science-Fiction").description("Futur, technologie, espace").build();
        Category history = Category.builder().name("Histoire").description("Livres historiques").build();
        categoryRepository.saveAll(List.of(fiction, scifi, history));

        // === Authors ===
        Author orwell = Author.builder()
                .firstName("George").lastName("Orwell")
                .biography("Auteur britannique, célèbre pour 1984 et La Ferme des animaux.")
                .build();

        Author asimov = Author.builder()
                .firstName("Isaac").lastName("Asimov")
                .biography("Auteur américain de science-fiction et de vulgarisation scientifique.")
                .build();

        Author hugo = Author.builder()
                .firstName("Victor").lastName("Hugo")
                .biography("Grand romancier français du XIXe siècle.")
                .build();

        authorRepository.saveAll(List.of(orwell, asimov, hugo));

        // === Books ===
        Book book1984 = Book.builder()
                .title("1984")
                .isbn("978-0-452-28423-4")
                .publicationDate(LocalDate.of(1949, 6, 8))
                .available(true)
                .author(orwell)
                .categories(Set.of(fiction, scifi))
                .build();

        Book animalFarm = Book.builder()
                .title("La Ferme des animaux")
                .isbn("978-2-07-036161-4")
                .publicationDate(LocalDate.of(1945, 8, 17))
                .available(true)
                .author(orwell)
                .categories(Set.of(fiction))
                .build();

        Book foundation = Book.builder()
                .title("Fondation")
                .isbn("978-2-07-040813-1")
                .publicationDate(LocalDate.of(1951, 5, 1))
                .available(true)
                .author(asimov)
                .categories(Set.of(scifi))
                .build();

        Book miserables = Book.builder()
                .title("Les Misérables")
                .isbn("978-2-07-040834-6")
                .publicationDate(LocalDate.of(1862, 4, 3))
                .available(false)
                .author(hugo)
                .categories(Set.of(fiction, history))
                .build();

        bookRepository.saveAll(List.of(book1984, animalFarm, foundation, miserables));

        // === Borrows ===
        Borrow borrow1 = new Borrow();
        borrow1.setUser(user);
        borrow1.setBook(miserables);
        borrow1.setBorrowDate(LocalDate.now().minusDays(10));

        Borrow borrow2 = new Borrow();
        borrow2.setUser(admin);
        borrow2.setBook(book1984);
        borrow2.setBorrowDate(LocalDate.now().minusDays(5));
        borrow2.setReturnDate(LocalDate.now().minusDays(1));

        borrowRepository.saveAll(List.of(borrow1, borrow2));

        log.info("=== Initial data loaded ===");
        log.info("Admin → email: admin@library.com | password: admin123");
        log.info("User  → email: user@library.com  | password: user123");
        log.info("Books: 1984, La Ferme des animaux, Fondation, Les Misérables");
        log.info("===========================");
    }
}
