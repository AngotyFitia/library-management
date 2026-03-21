package project.library.restapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.library.restapi.model.Role;
import project.library.restapi.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Page<User> findByNomContainingIgnoreCase(String nom, Pageable pageable);

    Page<User> findByRole(Role role, Pageable pageable);

    Page<User> findByNomContainingIgnoreCaseAndRole(String nom, Role role, Pageable pageable);
}
