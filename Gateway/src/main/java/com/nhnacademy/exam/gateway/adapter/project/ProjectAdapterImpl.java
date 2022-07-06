package com.nhnacademy.exam.gateway.adapter.project;

import com.nhnacademy.exam.gateway.domain.project.Project;
import com.nhnacademy.exam.gateway.dto.RestResponsePage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class ProjectAdapterImpl
    implements ProjectAdapter {
    private final RestTemplate restTemplate;

    @Override
    public String createProject(Project project) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("localhost")
            .port("9091")
            .path("/project/create")
            .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity requestEntity = new HttpEntity(project, headers);
        ResponseEntity<String>
            responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.POST, requestEntity, String.class);

        return responseEntity.getBody();
    }

    @Override
    public Project findProjectByProjectNo(Long projectNo) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("localhost")
            .port("9091")
            .path("/projects/" + projectNo)
            .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Project>
            responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, requestEntity, Project.class);

        return responseEntity.getBody();
    }

    @Override
    public Page<Project> findByAdminMemberNo(Pageable pageable, Long memberNo) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("localhost")
            .port("9091")
            .path("/projects/findProjectDtoClassByAdminMemberNo/" + memberNo)
            .queryParam("size", pageable.getPageSize())
            .queryParam("page", pageable.getPageNumber())
            .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity requestEntity = new HttpEntity(headers);

        ResponseEntity<RestResponsePage<Project>>
            responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,
            requestEntity, new ParameterizedTypeReference<RestResponsePage<Project>>() {});

        return responseEntity.getBody();
    }

    @Override
    public Page<Project> findByCompositionMemberNo(Pageable pageable, Long memberNo) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("localhost")
            .port("9091")
            .path("/projects/findProjectDtoClassByComposition/" + memberNo)
            .queryParam("size", pageable.getPageSize())
            .queryParam("page", pageable.getPageNumber())
            .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<RestResponsePage<Project>>
            responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,
            requestEntity, new ParameterizedTypeReference<RestResponsePage<Project>>() {});

        return responseEntity.getBody();
    }

}
