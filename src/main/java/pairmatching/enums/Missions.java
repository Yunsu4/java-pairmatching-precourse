package pairmatching.enums;

import java.util.Arrays;
import java.util.Objects;
import pairmatching.view.error.ErrorException;

public enum Missions {

    CAR_RACE(Level.LEVEL1, "자동차경주"),
    LOTTO(Level.LEVEL1, "로또"),
    NUMBER_BASEBALL(Level.LEVEL1, "숫자야구게임"),
    SHOPPING_BASKET(Level.LEVEL2, "장바구니"),
    PAYMENT(Level.LEVEL2, "결제"),
    SUBWAY_MAP(Level.LEVEL2, "지하철노선도"),
    IMPROVED_PERFORMANCE(Level.LEVEL2, "성능개선"),
    DISTRIBUTION(Level.LEVEL4, "배포");

    private static final String INVALID_MISSION = "없는 미션 입니다.";
    final Level level;
    final String mission;

    Missions(Level level, String missions) {
        this.level = level;
        this.mission = missions;
    }


    public static Missions getValidMission(Level enteredLevel, String enteredMission) {
        return Arrays.stream(Missions.values())
                .filter(mission -> mission.level == enteredLevel)
                .filter(mission -> Objects.equals(mission.mission, enteredMission))
                .findAny()
                .orElseThrow(() -> new ErrorException(INVALID_MISSION));
    }

    public Level getLevel() {
        return level;
    }
}
