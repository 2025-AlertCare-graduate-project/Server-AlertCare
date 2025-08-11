package com.alertcare.server.user.service;

import com.alertcare.server.user.domain.User;
import com.alertcare.server.user.dto.LoginRequestDTO;
import com.alertcare.server.user.dto.ProfileRequestDTO;
import com.alertcare.server.user.dto.SignOutReqestDTO;
import com.alertcare.server.user.dto.SignUpRequestDTO;
import com.alertcare.server.user.exception.UserErrorCode;
import com.alertcare.server.user.exception.UserException;
import com.alertcare.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User signUp(SignUpRequestDTO signUpRequestDTO) {

        userRepository.findByCareReceiverPhoneNumber(signUpRequestDTO.getCareReceiverPhoneNumber()).ifPresent(e -> {
            throw new UserException(UserErrorCode.PHONE_NUMBER_DUPLICATED);
        });

        User user = User.builder()
                .careGiverName(signUpRequestDTO.getCareGiverName())
                .careReceiverName(signUpRequestDTO.getCareReceiverName())
                .careReceiverPhoneNumber(signUpRequestDTO.getCareReceiverPhoneNumber())
                .build();

        return userRepository.save(user);
    }

    public User login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByCareReceiverPhoneNumber(loginRequestDTO.getCareReceiverPhoneNumber())
                .orElseThrow(() -> new UserException(UserErrorCode.MEMBER_NOT_FOUND));

        // 정보가 모두 일치 하면 user 반환
        if (
                user.getCareGiverName().equals(loginRequestDTO.getCareGiverName()) &&
                user.getCareReceiverName().equals(loginRequestDTO.getCareReceiverName())
        ) {
            return user;
        } else {
            throw new UserException(UserErrorCode.MEMBER_NOT_FOUND);
        }
    }

    public User getProfile(String phoneNumber) {
        return userRepository.findByCareReceiverPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UserException(UserErrorCode.MEMBER_NOT_FOUND));
    }

    public void deleteUser(SignOutReqestDTO signOutReqestDTO) {
        User user = userRepository.findByCareReceiverPhoneNumber(signOutReqestDTO.getCareReceiverPhoneNumber())
                .orElseThrow(() -> new UserException(UserErrorCode.MEMBER_NOT_FOUND));
        userRepository.delete(user);
    }
}
