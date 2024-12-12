package com.fmdev.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class MyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("DEBUG - Requisicao passou pelo filtro");

        if(request.getHeader("Authorization") != null) {
            // recupero meu cabecalho
            System.out.println("DEBUG* - " + request.getHeader("Authorization"));
            Authentication auth = TokenUtil.decodeToken(request);

            // cabecalho de autorizacao existe, preciso ver se eh valido
            if(auth != null) {
                // se o meu token for válido eu faco a requisicao pra frente indicando que esta autenticada
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("DEBUG*** - Requisicao autenticada");
            } else {
                System.out.println("DEBUG*** - Erro no token");
                ErroTokenDto erroTokenDto = new ErroTokenDto(401, "Usuario nao autenticado para esse sistema");
                response.setStatus(erroTokenDto.getStatus());
                response.setContentType("application/json");

                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().print(mapper.writeValueAsString(erroTokenDto));
                response.getWriter().flush();
                return;
            }
        }
        // passa a requisição para frente
        filterChain.doFilter(request, response);
    }
}
