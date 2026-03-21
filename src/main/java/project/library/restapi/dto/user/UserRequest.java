package project.library.restapi.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.library.restapi.model.Role;

/**
 * Payload pour créer ou mettre à jour un utilisateur (réservé à l'admin).
 * Pour une mise à jour, le champ password peut être omis (null = pas de changement).
 */
@Getter
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    private String password;

    private Role role;
}
