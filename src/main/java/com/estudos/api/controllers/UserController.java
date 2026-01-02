package com.estudos.api.controllers;

import com.estudos.api.domain.entitiesDTO.TokenDTO;
import com.estudos.api.domain.entitiesDTO.UserLoginDTO;
import com.estudos.api.domain.entitiesDTO.UserRegisterDTO;
import com.estudos.api.domain.statusDTO.okDTO;
import com.estudos.api.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthService service;

    @PostMapping("/auth/register")
    public ResponseEntity registrar(@RequestBody UserRegisterDTO user){
        return ResponseEntity.ofNullable(new okDTO("200", "Usúario criado com sucesso", LocalDateTime.now()));
    }

    @GetMapping("/auth/login")
    public ResponseEntity login(@RequestBody UserLoginDTO user){
        String token = "123";
        TokenDTO tokenDTO =  new TokenDTO(token, LocalDateTime.now());
        return ResponseEntity.ofNullable(tokenDTO);
    }
}
