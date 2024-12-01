package pairmatching.enums;

import java.util.Arrays;
import pairmatching.view.error.ErrorException;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private String name;

    Level(String name) {
        this.name = name;
    }

    public static Level getLevel(String enteredLevel) throws ErrorException{
        return Arrays.stream(Level.values())
                .filter(level -> level.name.equals(enteredLevel))
                .findAny()
                .map(level -> {
                    if (level == Level.LEVEL3 || level == Level.LEVEL5) {
                        throw new ErrorException("미션이 없는 레벨입니다.");
                    }
                    return level;
                })
                .orElseThrow(() -> new ErrorException("없는 레벨 입니다."));
    }
}
