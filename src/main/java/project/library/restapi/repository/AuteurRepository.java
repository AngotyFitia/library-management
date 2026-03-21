package project.library.restapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.library.restapi.model.Auteur;

import java.util.Optional;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {

    Optional<Auteur> findByNomAndPrenom(String nom, String prenom);

    Page<Auteur> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(
            String nom, String prenom, Pageable pageable
    );
}
