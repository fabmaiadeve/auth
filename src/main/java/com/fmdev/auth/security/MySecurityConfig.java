package com.fmdev.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()   // desabilito o csrf (porque eu que vou tratar a autenticacao do usuario)
                .authorizeHttpRequests()    // agora as requisicoes http sao passiveis de autorizacao
                .requestMatchers(HttpMethod.GET, "/free").permitAll()   // estou especificando quem é liberado
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated().and().cors();     // todas as outras URLs terao necessidade de autenticacao (e sofrerao as restrincoes do CORS)

        http.addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



}
