package com.nhnacademy.exam.gateway.service.login;


import com.nhnacademy.exam.gateway.adapter.GithubAdapter;
import com.nhnacademy.exam.gateway.adapter.MemberAdapter;
import com.nhnacademy.exam.gateway.domain.git.AccessTokenBody;
import com.nhnacademy.exam.gateway.domain.git.GitUserData;
import com.nhnacademy.exam.gateway.domain.member.Member;
import com.nhnacademy.exam.gateway.vo.member.MemberVo;
import com.nhnacademy.exam.gateway.vo.security.UserDetailsVo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("githubLoginService")
@RequiredArgsConstructor
public class GithubLoginServiceImpl
    implements GithubLoginService {
    private final GithubAdapter githubAdapter;
    private final MemberAdapter memberAdapter;
    private final RedisTemplate<String, MemberVo> redisTemplate;

    @Override
    public String getUriForLoginPageRequest() {
        return githubAdapter.getUriForLoginPageRequest();
    }

    @Override
    public AccessTokenBody getAccessToken(String code, String state) {
        return githubAdapter.getAccessToken(code, state);
    }

    @Override
    public GitUserData getGitUserData(String accessToken) {
        return githubAdapter.getGitUserData(accessToken);
    }

    @Override
    @Transactional
    public Boolean checkEmail(String email, HttpSession session) {
        Member member = memberAdapter.findByEmail(email);
        
        if (Objects.isNull(member)) return false;

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(member.getAuthority());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        UserDetails userDetails = new UserDetailsVo(member);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, member.getPw(), authorities);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        redisTemplate.opsForHash().put(session.getId(), "member", new MemberVo(member.getMemberNo(), member.getId(), member.getEmail(), member.getMemberStatus(), member.getAuthority()));
        return true;
    }
}

/*
        accessToken = responseEntityOfAccessToken.getBody();
        ObjectMapper mapper = new ObjectMapper();
        AccessTokenBody accessTokenBody = mapper.readValue(responseEntityOfAccessToken.getBody(), AccessTokenBody.class);
        headers.add("accept", "application/json");
        ResponseEntity<String> responseEntityOfAccessToken =
            restTemplate.exchange(uriComponents.toUri(), HttpMethod.POST, new HttpEntity(headers), String.class);
    */
