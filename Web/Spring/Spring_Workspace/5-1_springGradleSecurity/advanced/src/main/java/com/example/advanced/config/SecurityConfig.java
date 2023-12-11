package com.example.advanced.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration          // 스프링 설정 객체
@EnableWebSecurity      // 스프링 설정 중 web security 설정
// Controller에서 인증과 인가를 접근(확인)할 수 있는 도구 제공
// @Controller의 Method(Servlet)에서 해당 어노테이션 활성화 : @Secured, @PreAuthorize
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {
    
    // 암호화
    // 복호화가 안된다 이건
    @Bean   // 해당 메서드의 리턴되는 object(암호화 객체)를 IoC로 등록
    public BCryptPasswordEncoder encoderpassword() {
        return new BCryptPasswordEncoder();
    }

    // 인증 & 인가 HttpSecurity
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        // 개발용
        // CSRF : Cross Site Request Forgery : 사이트 간 요청 위로
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity
        .authorizeHttpRequests(authorize -> authorize
        // 인증(로그인)이 성공했을 때, (인가(권한)은 확인하지 않음)
        .requestMatchers("/user/**").authenticated()
        // 인증(로그인) & 인가(권한) 모두 확인
        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
        // 누구나 접근 가능 (인증 & 인가 확인 안함)
        .anyRequest().permitAll()
      )
      .formLogin(formLogin -> formLogin
        // 로그인 접속 url path
        .loginPage("/loginPage")
        // login 주소가 호출이 되면, PrincipalDetailsService.loadUserByUsername() 실행 후,
        // Controller의 Method(/login)을 호출
        .loginProcessingUrl("/login")
        // 시큐리티 로그인 성공시 해당 url 주소로 이동
        .defaultSuccessUrl("/user")
        .permitAll()
      );

        return httpSecurity.build();
    }
}
