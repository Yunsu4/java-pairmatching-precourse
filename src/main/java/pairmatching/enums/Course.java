package pairmatching.enums;

import pairmatching.view.error.ErrorException;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String value;

    Course(String name) {
        this.value = name;
    }

    public static String isCourse(String enteredCourse) {
        if (isEquals(enteredCourse, Course.BACKEND) || isEquals(enteredCourse, Course.FRONTEND)) {
            return enteredCourse;
        }
        throw new ErrorException("없는 코스 입니다.");
    }

    public static boolean isEquals(String input, Course targetCourse) {
        return input.equals(targetCourse.value);
    }
}
