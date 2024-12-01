package pairmatching.enums;

import java.util.Arrays;
import pairmatching.view.error.ErrorException;

public enum ShortAnswer {
    YES("네"),
    NO("아니오");

    final String value;

    ShortAnswer(String answer) {
        this.value = answer;
    }

    public static String getValidAnswer(String input) throws ErrorException {
        return Arrays.stream(ShortAnswer.values())
                .filter(answer -> answer.value.equals(input))
                .map(answer -> answer.value)
                .findAny()
                .orElseThrow(() -> new ErrorException("네, 아니오만 입력 가능합니다."));
    }

    public static boolean isEquals(String input, ShortAnswer targetAnswer) {
        return input.equals(targetAnswer.value);
    }
}
