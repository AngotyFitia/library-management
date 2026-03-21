package project.library.restapi.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.library.restapi.model.Role;
import project.library.restapi.model.User;
import project.library.restapi.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Insère des données de démo au démarrage si la base est vide.
 * Fournit un compte admin et un compte utilisateur prêts à l'emploi.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) {
            return;
        }

        User admin = User.builder()
                .nom("Administrateur")
                .email("admin@library.com")
                .password(passwordEncoder.encode("admin123"))
                .role(Role.ADMIN)
                .actif(true)
                .dateCreation(LocalDateTime.now())
                .build();

        User user = User.builder()
                .nom("Jean Dupont")
                .email("user@library.com")
                .password(passwordEncoder.encode("user123"))
                .role(Role.USER)
                .actif(true)
                .dateCreation(LocalDateTime.now())
                .build();

        userRepository.saveAll(List.of(admin, user));

        log.info("=== Données initiales chargées ===");
        log.info("Admin  → email: admin@library.com | password: admin123");
        log.info("User   → email: user@library.com  | password: user123");
        log.info("==================================");
    }
}
