package com.nhnacademy.exam.taskapi.repository.project;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.exam.taskapi.dto.project.ProjectDto;
import com.nhnacademy.exam.taskapi.entity.Project;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectRepositoryTest {
    @Autowired
    private TestEntityManager em;

    @Autowired
    private ProjectRepository projectRepository;
    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project(1000L, "noname", "활성");
    }

    @Test
    void existsByName() {
        boolean flag = projectRepository.existsByName("noname");
        assertThat(flag)
            .isFalse();

        em.persist(project);
        em.clear();

        flag = projectRepository.existsByName("noname");
        assertThat(flag)
            .isTrue();
    }

    @Test
    void existsByProjectNo() {
        boolean flag = projectRepository.existsByProjectNo(project.getProjectNo());
        assertThat(flag)
            .isFalse();

        em.persist(project);

        flag = projectRepository.existsByProjectNo(project.getProjectNo());
        assertThat(flag)
            .isTrue();
    }

    @Test
    void findProjectDtoByProjectNo() {
        ProjectDto projectDto = projectRepository.findProjectDtoByProjectNo(project.getProjectNo());
        assertThat(projectDto)
            .isNull();

        projectRepository.save(project);
        projectDto = projectRepository.findProjectDtoByProjectNo(project.getProjectNo());
        assertThat(projectDto)
            .isNotNull();
    }

    @Test
    void findAllBy() {
        Pageable pageable = PageRequest.of(0, 3);
        List<ProjectDto> list = projectRepository.findAllBy(pageable);

        assertThat(list.size() <= 3)
            .isTrue();

        pageable = PageRequest.of(0, 2);
        list = projectRepository.findAllBy(pageable);

        assertThat(list.size() <= 2)
            .isTrue();

        pageable = PageRequest.of(0, 1);
        list = projectRepository.findAllBy(pageable);

        assertThat(list.size() <= 1)
            .isTrue();
    }

    @Test
    void findPageByAdminMemberNo() {
        Pageable pageable = PageRequest.of(0, 3);
        Page<ProjectDto> page = projectRepository.findPageByAdminMemberNo(1000L, pageable);

        assertThat(page.getSize())
            .isEqualTo(3);

        assertThat(page.getNumber())
            .isEqualTo(0);
    }
}