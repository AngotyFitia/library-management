package project.library.restapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.library.restapi.common.ApiResponse;
import project.library.restapi.dto.stats.LoanByCategoryDTO;
import project.library.restapi.dto.stats.TopAuthorDTO;
import project.library.restapi.service.StatsService;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
@Tag(name = "Statistics", description = "Aggregation endpoints — admin only")
@SecurityRequirement(name = "bearerAuth")
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/loans-by-category")
    @Operation(summary = "Loans by category", description = "Returns loan count grouped by category, sorted descending")
    public ResponseEntity<ApiResponse<List<LoanByCategoryDTO>>> getLoansByCategory() {
        return ResponseEntity.ok(ApiResponse.success(statsService.getLoansByCategory()));
    }

    @GetMapping("/top-authors")
    @Operation(summary = "Top authors", description = "Returns authors whose books are borrowed the most")
    public ResponseEntity<ApiResponse<List<TopAuthorDTO>>> getTopAuthors(
            @RequestParam(defaultValue = "10") int limit
    ) {
        return ResponseEntity.ok(ApiResponse.success(statsService.getTopAuthors(limit)));
    }
}
