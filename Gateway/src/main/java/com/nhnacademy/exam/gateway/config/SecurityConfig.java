package com.nhnacademy.exam.gateway.config;

import com.nhnacademy.exam.gateway.auth.LoginSuccessHandler;
import com.nhnacademy.exam.gateway.common.FieldRepository;
import com.nhnacademy.exam.gateway.service.login.CustomUserDetailsService;
import com.nhnacademy.exam.gateway.vo.member.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .anyRequest()
            .permitAll()
            .and()
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginAction")
                .usernameParameter("id")
                .passwordParameter("pw")
                .successHandler(loginSuccessHandler(null, null))
                .failureUrl("/login")
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    @Override
//                    public void onLogoutSuccess(HttpServletRequest request,
//                                                HttpServletResponse response,
//                                                Authentication authentication)
//                        throws IOException {
////                        HttpSession session = request.getSession(false);
////                        redisTemplate.opsForHash().delete(session.getId()); // 실패 세션이미 만료된후라서 접근불가
//                        response.sendRedirect("/login");
//                    }
//                })
                .and()
            .csrf()
                .disable();
//            .exceptionHandling()
//            .accessDeniedPage("/login"); // 무한루프
        // TODO 0-1 : 6/10/1549 : exceptionHandling, accessDeniedPage
        // TODO 0-2 : advice, validation, 예외처리페이지, 예외처리 어떻게 할것인가 이미있는회원인경우 에외를 어드바이스에서 잡아서 어딘가로 보내는가
        // 그럼 그걸 어딘가로 본낸걸 front에서 받아서 자스로 alert로 바로 보내줄수 있는가?
     }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider(null));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FieldRepository staticField() {
        return new FieldRepository();
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler(
        RedisTemplate<String, MemberVo> redisTemplate, FieldRepository staticFiled) {
        return new LoginSuccessHandler(redisTemplate, staticFiled);
    }
}
