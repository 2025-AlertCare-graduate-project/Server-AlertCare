package com.alertcare.server.user.service;

import com.alertcare.server.user.domain.User;
import com.alertcare.server.user.dto.LoginDTO;
import com.alertcare.server.user.dto.ProfileRequestDTO;
import com.alertcare.server.user.dto.SignUpDTO;
import com.alertcare.server.user.exception.UserErrorCode;
import com.alertcare.server.user.exception.UserException;
import com.alertcare.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User signUp(SignUpDTO signUpDTO) {

        userRepository.findByCareReceiverPhoneNumber(signUpDTO.getCareReceiverPhoneNumber()).ifPresent(e -> {
            throw new UserException(UserErrorCode.PHONE_NUMBER_DUPLICATED);
        });

        User user = User.builder()
                .careGiverName(signUpDTO.getCareGiverName())
                .careReceiverName(signUpDTO.getCareReceiverName())
                .careReceiverPhoneNumber(signUpDTO.getCareReceiverPhoneNumber())
                .careReceiverAge(signUpDTO.getCareReceiverAge())
                .build();

        return userRepository.save(user);
    }

    public User login(LoginDTO loginDTO) {
        User user = userRepository.findByCareReceiverPhoneNumber(loginDTO.getCareReceiverPhoneNumber())
                .orElseThrow(() -> new UserException(UserErrorCode.MEMBER_NOT_FOUND));

        // 정보가 모두 일치 하면 user 반환
        if (
                user.getCareGiverName().equals(loginDTO.getCareGiverName()) &&
                user.getCareReceiverName().equals(loginDTO.getCareReceiverName()) &&
                user.getCareReceiverAge() == loginDTO.getCareReceiverAge()
        ) {
            return user;
        } else {
            throw new UserException(UserErrorCode.MEMBER_NOT_FOUND);
        }
    }

    public User getProfile(ProfileRequestDTO profileRequestDTO) {
        return userRepository.findByCareReceiverPhoneNumber(profileRequestDTO.getCareReceiverPhoneNumber())
                .orElseThrow(() -> new UserException(UserErrorCode.MEMBER_NOT_FOUND));
    }
}
