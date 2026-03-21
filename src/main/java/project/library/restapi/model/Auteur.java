package project.library.restapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "auteurs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(columnDefinition = "TEXT")
    private String biographie;

    @OneToMany(mappedBy = "auteur", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Livre> livres = new ArrayList<>();
}
