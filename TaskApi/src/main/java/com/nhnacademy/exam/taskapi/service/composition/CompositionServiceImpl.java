package com.nhnacademy.exam.taskapi.service.composition;

import com.nhnacademy.exam.taskapi.dto.project.ProjectDtoClass;
import com.nhnacademy.exam.taskapi.repository.composition.CompositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompositionServiceImpl implements CompositionService {
//    @Qualifier("compositionRepositoryCustommpl")
    private final CompositionRepository compositionRepository;

    @Override
    public Page<ProjectDtoClass> findProjectDtoByComposition(Long memberNo, Pageable pageable) {
        Page<ProjectDtoClass> projectDtoByMemberNo =
            compositionRepository.findProjectDtoByMemberNo(memberNo, pageable);
        return projectDtoByMemberNo;
    }
}
