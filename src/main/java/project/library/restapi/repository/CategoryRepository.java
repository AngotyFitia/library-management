package project.library.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.library.restapi.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    boolean existsByName(String name);
}
