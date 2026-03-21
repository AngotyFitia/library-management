package project.library.restapi.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.library.restapi.model.User;

import java.time.LocalDateTime;

/**
 * Représentation publique d'un utilisateur (sans mot de passe).
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String nom;
    private String email;
    private String role;
    private boolean actif;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreation;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .nom(user.getNom())
                .email(user.getEmail())
                .role(user.getRole().name())
                .actif(user.isActif())
                .dateCreation(user.getDateCreation())
                .build();
    }
}
