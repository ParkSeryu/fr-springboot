package com.springboot.frspringboot.config.auth;

import com.springboot.frspringboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정을 활성화시켜준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disabled.
                .and()
                .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점이다. 이게 있어야 antMatchers 옵션을 사용할 수 있다.
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                // 권한 관리 대상을 지정하는 옵션이다.
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // api/v1/** 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록.
                .anyRequest().authenticated() // 설정 값 이외 나머지 URL을 나타낸다.
                .and()
                .logout()
                .logoutSuccessUrl("/") // 로그아웃 기능에 대한 여러 설정의 진입점이다. 성공시 루트로 이동한다.
                .and()
                .oauth2Login() // 로그인 기능에 대한 여러 설정의 진입점이다.
                .userInfoEndpoint() // 로그인 성공 이후 사용자 정보를 가져올 때 설정들을 담당한다.
                .userService(customOAuth2UserService); // 소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록한다.
    }

}
