package com.alertcare.server.user.controller;

import com.alertcare.server.user.domain.User;
import com.alertcare.server.user.dto.SignUpDTO;
import com.alertcare.server.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpDTO signUpDTO) {
        User createdUser = userService.signUp(signUpDTO);
        return ResponseEntity.ok(createdUser);
    }
}
