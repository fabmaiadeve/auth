package com.fmdev.auth.controller;

import com.fmdev.auth.model.Usuario;
import com.fmdev.auth.security.AuthToken;
import com.fmdev.auth.security.TokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/free")
    public String sayFreeHello() {
        return "Este é um end-point liberado pela nossa API!";
    }

    @GetMapping("/auth")
    public String sayAuthHello() {
        return "Este é um end-point que precisa de autenticação!";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthToken> realizarLogin(@RequestBody Usuario usuario) {
        if(usuario.getLogin().equals("fab") && usuario.getSenha().equals("123")) {
            return ResponseEntity.ok(TokenUtil.encodeToken(usuario));
        }
        return ResponseEntity.status(403).build();
    }
}
