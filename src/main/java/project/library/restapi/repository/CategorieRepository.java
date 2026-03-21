package project.library.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.library.restapi.model.Categorie;

import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    Optional<Categorie> findByNom(String nom);

    boolean existsByNom(String nom);
}
