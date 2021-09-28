package com.fikirtepe.app.repository;

import com.fikirtepe.app.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findSectionsByClassroomId(long id);
}
