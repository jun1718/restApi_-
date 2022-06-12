package com.nhnacademy.exam.gateway.service.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.gateway.adapter.project.ProjectAdapter;
import com.nhnacademy.exam.gateway.common.FieldRepository;
import com.nhnacademy.exam.gateway.domain.project.Project;
import com.nhnacademy.exam.gateway.exception.CreateFailException;
import com.nhnacademy.exam.gateway.gateEnum.common.StatusEnum;
import com.nhnacademy.exam.gateway.service.common.CreateDeserializer;
import com.nhnacademy.exam.gateway.vo.member.MemberVo;
import java.util.Map;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl
    implements ProjectService {
    private final ProjectAdapter projectAdapter;
    private final RedisTemplate<String, MemberVo> redisTemplate;
    private final FieldRepository fieldRepository;

    public ProjectServiceImpl(
        ProjectAdapter projectAdapter,
        RedisTemplate<String, MemberVo> redisTemplate,
        FieldRepository fieldRepository) {
        this.projectAdapter = projectAdapter;
        this.redisTemplate = redisTemplate;
        this.fieldRepository = fieldRepository;
    }

    @Override
    public Long createProject(Project project) throws JsonProcessingException {
        settingProject(project);
        String json = projectAdapter.createProject(project);
        Map<String, Long> map = CreateDeserializer.getJsonDeserializedMapAndFailCheck(json);
        return map.get("projectNo");
    }

    private void settingProject(Project project) {
        MemberVo memberVo =
            (MemberVo) redisTemplate.opsForHash().get(fieldRepository.getSessionId(), "member");
        project.setAdminMemberNo(memberVo.getMemberNo());
        project.setProjectStatus(StatusEnum.ACTIVATE.getValue());
    }

    @Override
    public Project findProjectByProjectNo(Long projectNo) {
        return null;
    }


}
