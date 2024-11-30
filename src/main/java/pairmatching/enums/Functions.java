package pairmatching.enums;

import java.util.Arrays;
import pairmatching.view.error.ErrorException;

public enum Functions {
    MATCH_PAIRS("1"),
    PAIR_INQUIRY("2"),
    INITIALIZE_PAIR("3"),
    QUIT("Q");

    final String name;

    Functions(String function) {
        this.name = function;
    }

    public static String getValidFunction(String input) throws ErrorException {
        return Arrays.stream(Functions.values())
                .filter(function -> function.name.equals(input))
                .map(function -> function.name)
                .findAny()
                .orElseThrow(() -> new ErrorException("없는 기능 입니다."));
    }

    public static boolean isEquals(String input, Functions targetFunction) {
        return input.equals(targetFunction.name);
    }

}
