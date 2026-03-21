package project.library.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.library.restapi.dto.stats.EmpruntParCategorieDTO;
import project.library.restapi.dto.stats.TopAuteurDTO;
import project.library.restapi.repository.EmpruntRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final EmpruntRepository empruntRepository;

    /**
     * Retourne le nombre d'emprunts par catégorie, trié par volume décroissant.
     */
    @Transactional(readOnly = true)
    public List<EmpruntParCategorieDTO> getEmpruntsParCategorie() {
        return empruntRepository.countEmpruntsParCategorie();
    }

    /**
     * Retourne les auteurs les plus populaires (livres les plus empruntés).
     *
     * @param limit nombre maximal de résultats à retourner
     */
    @Transactional(readOnly = true)
    public List<TopAuteurDTO> getTopAuteurs(int limit) {
        return empruntRepository.findTopAuteurs(PageRequest.of(0, limit));
    }
}
