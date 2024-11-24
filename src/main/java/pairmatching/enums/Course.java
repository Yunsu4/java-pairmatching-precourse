package pairmatching.enums;

import pairmatching.model.FrontendCrew;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }
    public String getMessage() {
        return name;
    }

    public static String isCourse(String enteredCourse){
        if(enteredCourse.equals(BACKEND.name) || enteredCourse.equals(FRONTEND.name)){
            return enteredCourse;
        }
        throw new IllegalArgumentException("[ERROR] 없는 코스 입니다.");
    }
}
