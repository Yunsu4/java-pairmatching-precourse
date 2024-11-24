package pairmatching.enums;

public enum Missions {

    LEVEL1("레벨1","자동차경주,로또,숫자야구게임"),
    LEVEL2("레벨2","장바구니,결제,지하철노선도"),
    LEVEL4("레벨4","성능개선,배포");

    private String level;
    private String missions;

    Missions(String level, String missions) {
        this.level = level;
        this.missions = missions;
    }

    public static String isMission(String enteredLevel, String enteredMission) {
        if(enteredLevel.equals(LEVEL1.level)){
            String[] mission1 = LEVEL1.missions.split(",");
            for (String level1Mission : mission1) {
                if (enteredMission.equals(level1Mission)) {
                    return level1Mission;
                }
            }
        }
        if(enteredLevel.equals(LEVEL2.level)){
            String[] mission2 = LEVEL2.missions.split(",");
            for (String level2Mission : mission2) {
                if (enteredMission.equals(level2Mission)) {
                    return level2Mission;
                }
            }
        }
        if(enteredLevel.equals(LEVEL4.level)){
            String[] mission4 = LEVEL4.missions.split(",");
            for (String level4Mission : mission4) {
                if (enteredMission.equals(level4Mission)) {
                    return level4Mission;
                }
            }
        }
        throw new IllegalArgumentException("[ERROR] 없는 미션 입니다.");
    }

    public static boolean hasMission(String enteredLevel, String enteredMission) {
        if(enteredLevel.equals(LEVEL1.level)){
            String[] mission1 = LEVEL1.missions.split(",");
            for (String level1Mission : mission1) {
                if (enteredMission.equals(level1Mission)) {
                    return true;
                }
            }
        }
        if(enteredLevel.equals(LEVEL2.level)){
            String[] mission2 = LEVEL2.missions.split(",");
            for (String level2Mission : mission2) {
                if (enteredMission.equals(level2Mission)) {
                    return true;
                }
            }
        }
        if(enteredLevel.equals(LEVEL4.level)){
            String[] mission4 = LEVEL4.missions.split(",");
            for (String level4Mission : mission4) {
                if (enteredMission.equals(level4Mission)) {
                    return true;
                }
            }
        }
        System.out.println("[ERROR] 없는 미션 입니다.");
        //throw new IllegalArgumentException("[ERROR] 없는 미션 입니다.");
        return false;

    }
}
