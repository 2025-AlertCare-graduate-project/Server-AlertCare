package com.alertcare.server.user.controller;

import com.alertcare.server.common.response.BasicResponse;
import com.alertcare.server.user.domain.User;
import com.alertcare.server.user.dto.LoginRequestDTO;
import com.alertcare.server.user.dto.ProfileRequestDTO;
import com.alertcare.server.user.dto.SignOutReqestDTO;
import com.alertcare.server.user.dto.SignUpRequestDTO;
import com.alertcare.server.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public BasicResponse<User> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        User createdUser = userService.signUp(signUpRequestDTO);
        return BasicResponse.success(201, "회원가입 성공", createdUser);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public BasicResponse<User> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        User user = userService.login(loginRequestDTO);

        return BasicResponse.success(200, "로그인 성공", user);
    }

    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public BasicResponse<User> getProfile(@RequestBody ProfileRequestDTO profileRequestDTO) {
        User user = userService.getProfile(profileRequestDTO);

        return BasicResponse.success(200, "유저 조회 성공", user);
    }

    @DeleteMapping("/signout")
    @ResponseStatus(HttpStatus.OK)
    public BasicResponse<Void> signOut(@RequestBody SignOutReqestDTO signOutReqestDTO) {
        userService.deleteUser(signOutReqestDTO);

        return BasicResponse.success(200, "탈퇴 성공", null);
    }
}
