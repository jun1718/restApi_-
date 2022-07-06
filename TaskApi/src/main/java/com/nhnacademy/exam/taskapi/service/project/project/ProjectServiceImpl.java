package com.nhnacademy.exam.taskapi.service.project.project;

import com.nhnacademy.exam.taskapi.dto.project.ProjectDto;
import com.nhnacademy.exam.taskapi.entity.Project;
import com.nhnacademy.exam.taskapi.repository.project.ProjectRepository;
import com.nhnacademy.exam.taskapi.vo.ProjectVo;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl
    implements ProjectService {
    private final ProjectRepository projectRepository;
//    @PersistenceContext
//    private EntityManager entityManager;

    private static final String FAIL_VALUE_DUPLICATION = "{\"fail\" : \"duplication\"}";
    private static final String FAIL_VALUE_NOT_FOUND = "{\"fail\" : \"notFoundMember\"}";

    @Override
    public String createProject(ProjectVo projectVo) {
        if (projectRepository.existsByName(projectVo.getName())) return FAIL_VALUE_DUPLICATION;

        Project project = new Project(projectVo.getAdminMemberNo(),
            projectVo.getName(), projectVo.getProjectStatus());
        projectRepository.save(project);
//        entityManager.clear();

        if (!projectRepository.existsById(project.getProjectNo())) return FAIL_VALUE_NOT_FOUND;
        return "{\"projectNo\" : \"" + project.getProjectNo() + "\"}";
    }

    @Override
    public ProjectDto findProjectDtoByProjectNo(Long projectNo) {
        return projectRepository.findProjectDtoByProjectNo(projectNo);
    }

    @Override
    public Page<ProjectDto> findPageByAdminMemberNo(Pageable pageable, Long memberNo) {
        return projectRepository.findPageByAdminMemberNo(memberNo, pageable);
    }
}
