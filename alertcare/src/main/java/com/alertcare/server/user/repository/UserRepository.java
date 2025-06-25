package com.alertcare.server.user.repository;

import com.alertcare.server.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCareReceiverPhoneNumber(String careReceiverPhoneNumber);
}
