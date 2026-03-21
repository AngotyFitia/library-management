package project.library.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.library.restapi.dto.stats.LoanByCategoryDTO;
import project.library.restapi.dto.stats.TopAuthorDTO;
import project.library.restapi.repository.LoanRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final LoanRepository loanRepository;

    @Transactional(readOnly = true)
    public List<LoanByCategoryDTO> getLoansByCategory() {
        return loanRepository.countLoansByCategory();
    }

    @Transactional(readOnly = true)
    public List<TopAuthorDTO> getTopAuthors(int limit) {
        return loanRepository.findTopAuthors(PageRequest.of(0, limit));
    }
}
