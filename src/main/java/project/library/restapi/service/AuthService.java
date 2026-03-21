package project.library.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.library.restapi.dto.auth.AuthResponse;
import project.library.restapi.dto.auth.LoginRequest;
import project.library.restapi.dto.auth.RegisterRequest;
import project.library.restapi.exception.BusinessException;
import project.library.restapi.model.Role;
import project.library.restapi.model.User;
import project.library.restapi.repository.UserRepository;
import project.library.restapi.security.CustomUserDetailsService;
import project.library.restapi.security.JwtService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;

    /**
     * Authentifie un utilisateur et retourne un JWT.
     * Lance BadCredentialsException si les identifiants sont incorrects (géré par GlobalExceptionHandler).
     */
    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        // Délègue la vérification email + mot de passe à Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtService.generateToken(userDetails);

        return buildAuthResponse(token, user);
    }

    /**
     * Inscrit un nouvel utilisateur avec le rôle USER par défaut.
     */
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Un compte existe déjà avec cet email : " + request.getEmail());
        }

        User user = User.builder()
                .nom(request.getNom())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .actif(true)
                .dateCreation(LocalDateTime.now())
                .build();

        userRepository.save(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtService.generateToken(userDetails);

        return buildAuthResponse(token, user);
    }

    private AuthResponse buildAuthResponse(String token, User user) {
        return AuthResponse.builder()
                .token(token)
                .type("Bearer")
                .nom(user.getNom())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}
