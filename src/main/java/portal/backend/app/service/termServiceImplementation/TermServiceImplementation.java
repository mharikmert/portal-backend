package portal.backend.app.service.termServiceImplementation;

import portal.backend.app.model.Term;
import portal.backend.app.repository.TermRepository;
import portal.backend.app.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermServiceImplementation implements TermService {

    private final TermRepository termRepository;

    @Autowired
    public TermServiceImplementation(TermRepository termRepository) {
        this.termRepository = termRepository;
    }

    @Override
    public Term getCurrentTerm() {
        return termRepository.findCurrentTerm();
    }

    @Override
    public void activateTerm(int termId) {
        List<Term> terms = getTerms();
        for (Term term : terms) {
            term.setActive(term.getId() == termId);
        }
        termRepository.saveAll(terms);
    }

    @Override
    public List<Term> getTerms() {
        return termRepository.findAll();
    }

    @Override
    public Term getTermByName(String name) {
        return termRepository.findTermByName(name);
    }

    @Override
    public Term createTerm(Term term) {
        return termRepository.save(term);
    }

    @Override
    public List<Term> updateTerms(List<Term> terms) {
        return termRepository.saveAll(terms);
    }

    @Override
    public Term updateTerm(Term term) {
        return termRepository.save(term);
    }

    @Override
    public void deleteTerm(Term term) {
        termRepository.delete(term);
    }
}