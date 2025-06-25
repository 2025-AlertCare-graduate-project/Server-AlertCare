package com.alertcare.server.user.controller;

import com.alertcare.server.common.response.BasicResponse;
import com.alertcare.server.user.domain.User;
import com.alertcare.server.user.dto.LoginDTO;
import com.alertcare.server.user.dto.ProfileRequestDTO;
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
    @ResponseStatus(HttpStatus.CREATED)
    public BasicResponse<User> signUp(@RequestBody SignUpDTO signUpDTO) {
        User createdUser = userService.signUp(signUpDTO);
        return BasicResponse.success(201, "회원가입 성공", createdUser);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public BasicResponse<User> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.login(loginDTO);

        return BasicResponse.success(200, "로그인 성공", user);
    }

    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public BasicResponse<User> getProfile(@RequestBody ProfileRequestDTO profileRequestDTO) {
        User user = userService.getProfile(profileRequestDTO);

        return BasicResponse.success(200, "유저 조회 성공", user);
    }
}
