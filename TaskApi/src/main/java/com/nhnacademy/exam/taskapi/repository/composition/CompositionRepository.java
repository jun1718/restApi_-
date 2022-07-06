package com.nhnacademy.exam.taskapi.repository.composition;

import com.nhnacademy.exam.taskapi.entity.Composition;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

public interface CompositionRepository extends JpaRepository<Composition, Long>, CompositionRepositoryCustom {
    Page<Composition> findPageByMemberNo(Long memberNo, Pageable pageable);
}
