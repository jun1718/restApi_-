package com.nhnacademy.exam.taskapi.repository.composition;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.exam.taskapi.dto.project.ProjectDtoClass;
import com.nhnacademy.exam.taskapi.entity.Composition;
import com.nhnacademy.exam.taskapi.entity.Project;
import com.nhnacademy.exam.taskapi.repository.project.ProjectRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CompositionRepositoryTest {
    @Autowired
    private TestEntityManager em;
    @Autowired
    private CompositionRepository compositionRepository;
    @Autowired
    private ProjectRepository projectRepository;

    private Composition composition;
    private Project project;
    @BeforeEach
    void setUp() {
         project = new Project(1L, "name", "활성");
         em.persist(project);
    }

    @Test
    void findPageByMemberNo() {
        Pageable pageable = PageRequest.of(0, 3);
        composition = new Composition(1000L, project);
        em.persist(composition);
        Page<Composition> composition2 = compositionRepository.findPageByMemberNo(1000L, pageable);

        List<Composition> content = composition2.getContent();
        assertThat(content.get(content.size() - 1))
            .isEqualTo(composition);

    }
}