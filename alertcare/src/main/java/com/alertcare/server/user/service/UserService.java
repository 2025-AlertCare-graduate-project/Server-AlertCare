package com.alertcare.server.user.service;

import com.alertcare.server.user.domain.User;
import com.alertcare.server.user.dto.SignUpDTO;
import com.alertcare.server.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User signUp(SignUpDTO signUpDTO) {
        User user = User.builder()
                .careGiverName(signUpDTO.getCareGiverName())
                .careReceiverName(signUpDTO.getCareReceiverName())
                .careReceiverPhoneNumber(signUpDTO.getCareReceiverPhoneNumber())
                .careReceiverAge(signUpDTO.getCareReceiverAge())
                .build();

        return userRepository.save(user);
    }
}
