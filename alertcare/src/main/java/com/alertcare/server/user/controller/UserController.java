package com.alertcare.server.user.controller;

import com.alertcare.server.user.domain.User;
import com.alertcare.server.user.dto.LoginDTO;
import com.alertcare.server.user.dto.SignUpDTO;
import com.alertcare.server.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.login(loginDTO);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            return ResponseEntity.ok(user);
        }
    }
}
