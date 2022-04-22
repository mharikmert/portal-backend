package com.fikirtepe.app.repository;

import com.fikirtepe.app.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TermRepository extends JpaRepository<Term, Long> {
    @Query(value = "select * from term where is_active = true", nativeQuery = true)
    Term findCurrentTerm();
    Term findTermByName(String name);
}
