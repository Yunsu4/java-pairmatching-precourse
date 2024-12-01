package pairmatching.view.error;

public enum InputErrorType {
    ERROR_MESSAGE("[ERROR] "),
    NEED_AVAILABLE_INPUT("잘못된 입력입니다."),
    NEED_REENTER_INPUT(" 다시 입력해 주세요.");

    final String message;

    InputErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
