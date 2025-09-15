package com.alertcare.server.summary.service;

import com.alertcare.server.activity.repository.ActivityRepository;
import com.alertcare.server.summary.domain.Summary;
import com.alertcare.server.summary.dto.SummaryResponseDto;
import com.alertcare.server.summary.repository.SummaryRepository;
import com.alertcare.server.user.domain.User;
import com.alertcare.server.user.exception.UserErrorCode;
import com.alertcare.server.user.exception.UserException;
import com.alertcare.server.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final SummaryRepository summaryRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void saveDailySummary(){
        LocalDate date = LocalDate.now().minusDays(1);
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        List<User> users = userRepository.findAll();

        for(User user : users) {
            Object[] result = (Object[]) activityRepository.sumByDateAndUser(user.getId(), startOfDay, endOfDay);

            int activeSum = ((Number) result[0]).intValue();
            int sittingSum = ((Number) result[1]).intValue();
            int lyingSum = ((Number) result[2]).intValue();

            Summary summary = Summary.builder()
                    .user(user)
                    .date(date)
                    .activeTime(activeSum)
                    .sittingTime(sittingSum)
                    .lyingTime(lyingSum)
                    .build();

            summaryRepository.save(summary);
        }

    }
    public SummaryResponseDto getDailySummary(String phoneNumber, LocalDate date){
        User user = userRepository.findByCareReceiverPhoneNumber(phoneNumber)
                .orElseThrow(() -> {
                    System.out.println("User not found for phoneNumber: " + phoneNumber);
                    return new UserException(UserErrorCode.MEMBER_NOT_FOUND);
                });

        Summary summary = summaryRepository.findByUserIdAndDate(user.getId(), date)
                .orElseThrow(() -> {
                    System.out.println("Summary not found for userId: " + user.getId() + ", date: " + date);
                    return new IllegalArgumentException("해당 날짜의 Summary가 존재하지 않습니다.");
                });

        return SummaryResponseDto.builder()
                .id(summary.getId())
                .userId(summary.getUser().getId())
                .activeTime(summary.getActiveTime())
                .sittingTime(summary.getSittingTime())
                .lyingTime(summary.getLyingTime())
                .date(summary.getDate())
                .build();
    }
}
