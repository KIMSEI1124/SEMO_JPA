package com.multi.semo.global.security.config;

import com.multi.semo.auth.config.JwtSecurityConfig;
import com.multi.semo.auth.support.JwtAccessDeniedHandler;
import com.multi.semo.auth.support.JwtAuthenticationEntryPoint;
import com.multi.semo.auth.support.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                /* exception handing 할 때 커스텀 클래스로 동작하게 설정 */
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                /* 시큐리티는 기본적으로 세션을 사용하지만 여기서는 사용하지 않기 때문에 설정을 stateless 로 설정 */
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeHttpRequests()
//                .requestMatchers("/api/admin/**").hasRole("ROLE_ADMIN")
                .requestMatchers("/api/admin/**").permitAll()
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated()

                /* JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용 */
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}
