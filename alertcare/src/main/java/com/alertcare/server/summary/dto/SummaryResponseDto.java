package com.alertcare.server.summary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class SummaryResponseDto {
    private Long id;
    private Long userId;
    private int activeTime;
    private int lyingTime;
    private int sittingTime;
    private LocalDate date;
}
