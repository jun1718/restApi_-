package com.nhnacademy.exam.gateway.config;

//import com.nhnacademy.exam.gateway.interceptor.LoginCheckInterceptor;
import com.nhnacademy.exam.gateway.interceptor.LoginCheckInterceptor;
import com.nhnacademy.exam.gateway.vo.member.MemberVo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor(null));
        ProviderManager providerManager;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/showHome");
        registry.addViewController("/member/create").setViewName("member/showMemberCreateForm");
        registry.addViewController("/project/create").setViewName("project/showProjectCreateForm");
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    LoginCheckInterceptor loginCheckInterceptor(RedisTemplate<String, MemberVo> redisTemplate) {
        return new LoginCheckInterceptor(redisTemplate);
    }
}
