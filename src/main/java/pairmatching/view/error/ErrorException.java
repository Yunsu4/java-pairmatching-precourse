package pairmatching.view.error;

public class ErrorException extends IllegalArgumentException {
    public ErrorException(InputErrorType inputError) {
        super(InputErrorType.ERROR_MESSAGE.getMessage() + inputError.getMessage()
                + InputErrorType.NEED_REENTER_INPUT.getMessage());
    }

    public ErrorException(String errorMessage) {
        super(InputErrorType.ERROR_MESSAGE.getMessage() + errorMessage
                + InputErrorType.NEED_REENTER_INPUT.getMessage());
    }
}
