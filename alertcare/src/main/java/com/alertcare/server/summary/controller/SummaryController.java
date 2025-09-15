package com.alertcare.server.summary.controller;


import com.alertcare.server.common.response.BasicResponse;
import com.alertcare.server.summary.dto.SummaryResponseDto;
import com.alertcare.server.summary.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/summary")
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryService summaryService;

    @GetMapping("/daily/{phoneNumber}")
    public BasicResponse<SummaryResponseDto> getDailySummary(
            @PathVariable("phoneNumber") String phoneNumber,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        return BasicResponse.success(
                200,
                "하루 요약 전송 완료",
                summaryService.getDailySummary(phoneNumber, date)
        );
    }
}

