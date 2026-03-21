package project.library.restapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.library.restapi.common.ApiResponse;
import project.library.restapi.dto.stats.EmpruntParCategorieDTO;
import project.library.restapi.dto.stats.TopAuteurDTO;
import project.library.restapi.service.StatsService;

import java.util.List;

/**
 * Endpoints de statistiques — réservés aux administrateurs.
 * Exemples de retours complexes avec agrégation JPQL.
 */
@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
@Tag(name = "Statistiques", description = "Statistiques d'emprunts (réservé à l'admin)")
@SecurityRequirement(name = "bearerAuth")
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/emprunts-par-categorie")
    @Operation(
            summary = "Emprunts par catégorie",
            description = "Retourne le nombre d'emprunts groupé par catégorie, trié par volume décroissant"
    )
    public ResponseEntity<ApiResponse<List<EmpruntParCategorieDTO>>> getEmpruntsParCategorie() {
        List<EmpruntParCategorieDTO> stats = statsService.getEmpruntsParCategorie();
        return ResponseEntity.ok(ApiResponse.success(stats));
    }

    @GetMapping("/top-auteurs")
    @Operation(
            summary = "Top auteurs",
            description = "Retourne les auteurs dont les livres sont les plus empruntés"
    )
    public ResponseEntity<ApiResponse<List<TopAuteurDTO>>> getTopAuteurs(
            @RequestParam(defaultValue = "10") int limit
    ) {
        List<TopAuteurDTO> top = statsService.getTopAuteurs(limit);
        return ResponseEntity.ok(ApiResponse.success(top));
    }
}
