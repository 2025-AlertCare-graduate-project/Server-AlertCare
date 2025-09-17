package com.alertcare.server.openai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAiSummaryService {
    private final OpenAiService openAiService;

    public String generateDailySummary(int activeTime, int sittingTime, int lyingTime) {
        String prompt = String.format(
                "오늘 하루 활동 요약:\n" +
                        "- 활동 시간: %d분\n" +
                        "- 앉아있는 시간: %d분\n" +
                        "- 누워있는 시간: %d분\n" +
                        "\n" +
                        "이 서비스는 독거노인의 생활을 모니터링하고 보호자에게 알림과 조언을 제공하는 케어 앱입니다.  \n" +
                        "보호자가 이 정보를 바탕으로 독거노인을 더 잘 돌볼 수 있도록, 하루 생활을 짧게 정리하고 구체적으로 도움이 될 수 있는 코멘트를 2줄 이내로 작성해줘.\n",
                activeTime, sittingTime, lyingTime
        );

        return openAiService.callGpt(prompt);
    }
}
