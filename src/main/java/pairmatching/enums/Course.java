package pairmatching.enums;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String courseName;

    Course(String name) {
        this.courseName = name;
    }
    public String getCourse() {
        return courseName;
    }

    public static String isCourse(String enteredCourse){
        if(isBackend(enteredCourse) || isFrontend(enteredCourse)){
            return enteredCourse;
        }
        throw new IllegalArgumentException("[ERROR] 없는 코스 입니다.");
    }

    public static boolean isBackend(String enteredCourse){
        return enteredCourse.equals(Course.BACKEND.courseName);
    }

    public static boolean isFrontend(String enteredCourse){
        return enteredCourse.equals(Course.FRONTEND.courseName);
    }
}
