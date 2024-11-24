package pairmatching.enums;

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

    public static String isLevel(String enteredLevel) {
        if (enteredLevel.equals(LEVEL1.name) || enteredLevel.equals(LEVEL2.name) ||
                enteredLevel.equals(LEVEL4.name)) {
            return enteredLevel;
        }
        if(enteredLevel.equals(LEVEL3.name) || enteredLevel.equals(LEVEL5.name)){
            throw new IllegalArgumentException("[ERROR] 미션이 없는 레벨입니다.");
        }
        throw new IllegalArgumentException("[ERROR] 없는 레벨 입니다.");
    }
}
