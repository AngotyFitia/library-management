package project.library.restapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "livres")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false, unique = true)
    private String isbn;

    private LocalDate datePublication;

    /**
     * Indique si le livre est disponible à l'emprunt.
     * Mis à false lors d'un emprunt, remis à true lors du retour.
     */
    @Column(nullable = false)
    @Builder.Default
    private boolean disponible = true;

    // === Relations ===

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auteur_id", nullable = false)
    private Auteur auteur;

    /**
     * Relation n-n : un livre peut appartenir à plusieurs catégories.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "livre_categorie",
            joinColumns = @JoinColumn(name = "livre_id"),
            inverseJoinColumns = @JoinColumn(name = "categorie_id")
    )
    @Builder.Default
    private Set<Categorie> categories = new HashSet<>();

    @OneToMany(mappedBy = "livre", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Emprunt> emprunts = new ArrayList<>();
}
