package com.youth.moim.config;

import com.youth.moim.application.auth.JwtTokenFilter;
import com.youth.moim.application.auth.JwtTokenProvider;
import com.youth.moim.common.exception.JwtAccessDeniedHandler;
import com.youth.moim.common.exception.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> {
              session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })
            .authorizeHttpRequests(request -> {
              request.requestMatchers("/api/auth/**").permitAll();
            }).authorizeHttpRequests(request -> {
                request.anyRequest().authenticated();
            });

        http.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), ExceptionTranslationFilter.class);

        http.exceptionHandling((exceptionHandling) ->
              exceptionHandling
                      .accessDeniedHandler(jwtAccessDeniedHandler)
                      .authenticationEntryPoint(jwtAuthenticationEntryPoint)
      );

    return http.build();
  }
}
