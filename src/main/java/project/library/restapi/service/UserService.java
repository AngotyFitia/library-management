package project.library.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.library.restapi.dto.user.UserRequest;
import project.library.restapi.dto.user.UserResponse;
import project.library.restapi.exception.BusinessException;
import project.library.restapi.exception.ResourceNotFoundException;
import project.library.restapi.model.Role;
import project.library.restapi.model.User;
import project.library.restapi.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Liste paginée avec filtres optionnels (nom et/ou rôle).
     */
    @Transactional(readOnly = true)
    public Page<UserResponse> findAll(String nom, Role role, Pageable pageable) {
        if (nom != null && !nom.isBlank() && role != null) {
            return userRepository.findByNomContainingIgnoreCaseAndRole(nom, role, pageable)
                    .map(UserResponse::from);
        }
        if (nom != null && !nom.isBlank()) {
            return userRepository.findByNomContainingIgnoreCase(nom, pageable)
                    .map(UserResponse::from);
        }
        if (role != null) {
            return userRepository.findByRole(role, pageable)
                    .map(UserResponse::from);
        }
        return userRepository.findAll(pageable).map(UserResponse::from);
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        return UserResponse.from(getOrThrow(id));
    }

    /**
     * Récupère l'utilisateur actuellement connecté via son email (extrait du JWT).
     */
    @Transactional(readOnly = true)
    public UserResponse findByEmail(String email) {
        return UserResponse.from(
                userRepository.findByEmail(email)
                        .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable : " + email))
        );
    }

    /**
     * Création d'un utilisateur par l'admin (le rôle est spécifiable).
     */
    @Transactional
    public UserResponse create(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Un compte existe déjà avec cet email : " + request.getEmail());
        }
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new BusinessException("Le mot de passe est obligatoire à la création");
        }

        User user = User.builder()
                .nom(request.getNom())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() != null ? request.getRole() : Role.USER)
                .actif(true)
                .dateCreation(LocalDateTime.now())
                .build();

        return UserResponse.from(userRepository.save(user));
    }

    /**
     * Mise à jour d'un utilisateur existant.
     * Le mot de passe n'est re-hashé que s'il est fourni.
     */
    @Transactional
    public UserResponse update(Long id, UserRequest request) {
        User user = getOrThrow(id);

        // Vérification de l'unicité de l'email si changé
        if (!user.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Cet email est déjà utilisé par un autre compte");
        }

        user.setNom(request.getNom());
        user.setEmail(request.getEmail());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }

        return UserResponse.from(userRepository.save(user));
    }

    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Utilisateur", id);
        }
        userRepository.deleteById(id);
    }

    private User getOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", id));
    }
}
