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
                        "[역할] 당신은 독거노인을 걱정하는 보호자에게 맞춤형 조언을 주는 케어 코치입니다.\n" +
                        "[조건]\n" +
                        "- 하루 생활 데이터에 기반해 조언합니다.\n" +
                        "- 보호자가 독거노인과 멀리 떨어져 있다는 상황을 고려합니다.\n" +
                        "- 조언은 50자 이내로 따뜻하고 구체적으로 작성합니다.\n" +
                        "- '안부 전화', '간단한 운동 권유', '휴식 독려', '식사 확인' 등 실천 가능한 행동을 포함합니다.\n" +
                        "- 데이터에서 눈에 띄는 패턴(활동 부족, 과도한 누움 등)을 반드시 반영합니다.\n" +
                        "\n" +
                        "[출력]\n" +
                        "피보호자의 생활 요약 1줄, 보호자에게 전달할 1줄 조언\n"
                ,
                activeTime, sittingTime, lyingTime
        );

        return openAiService.callGpt(prompt);
    }
}
