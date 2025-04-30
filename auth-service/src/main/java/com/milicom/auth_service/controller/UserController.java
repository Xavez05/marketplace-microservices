package com.milicom.auth_service.controller;

import com.milicom.auth_service.dto.UserDTO;
import com.milicom.auth_service.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Value("${auth.jwt.system-token}")
    private String systemToken;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id, HttpServletRequest request) {
        System.out.println("[DEBUG] systemToken = " + systemToken);
        String authHeader = request.getHeader("Authorization");
        System.out.println("[DEBUG] Authorization header enviado: Bearer " + authHeader);
        if (authHeader == null || !authHeader.equals("Bearer " + systemToken)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized access");
        }

        return ResponseEntity.ok(userService.getUserDTOById(id));
    }
}

