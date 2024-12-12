package com.fmdev.auth.security;

import com.fmdev.auth.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {

    private static final String EMISSOR = "fabdev";
    private static final String TOKEN_HEADER = "Bearer ";
    private static final String TOKEN_KEY = "01234567890123456789012345678901";
    private static final long UM_SEGUNDO = 1000;
    private static final long UM_MINUTO = 60 * UM_SEGUNDO;

    public static AuthToken encodeToken(Usuario usuario) {

        Key secretKey = Keys.hmacShaKeyFor(TOKEN_KEY.getBytes());
        String tokenJWT = Jwts.builder()
                .setSubject(usuario.getLogin())
                .setIssuer(EMISSOR)
                .setExpiration(new Date(System.currentTimeMillis() + UM_MINUTO))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        return new AuthToken(TOKEN_HEADER + tokenJWT);
    }

    public static Authentication decodeToken(HttpServletRequest request) {

        try {
            String jwtToken = request.getHeader("Authorization");
            jwtToken = jwtToken.replace(TOKEN_HEADER, "");

            // fazendo a leitura do token
            Jws<Claims> jwsClaims = Jwts.parserBuilder()
                    .setSigningKey(TOKEN_KEY.getBytes())
                    .build().parseClaimsJws(jwtToken);

            // começando a extrair as informacoes
            String user = jwsClaims.getBody().getSubject();
            String emissor = jwsClaims.getBody().getIssuer();
            Date validade = jwsClaims.getBody().getExpiration();

            if(user.length() > 0 && emissor.equals(EMISSOR) && validade.after(new Date(System.currentTimeMillis()))) {
                System.out.println("DEBUG** - Token decodificado");
                return new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
            }
        } catch (Exception ex) {
            System.out.println("DEBUG** - Token não decodificado");
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
