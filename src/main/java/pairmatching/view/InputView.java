package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String newline = System.getProperty("line.separator");

    public String displayStartMessage() {
        System.out.println("기능을 선택하세요." + newline
                + "1. 페어 매칭" + newline
                + "2. 페어 조회" + newline
                + "3. 페어 초기화" + newline
                + "Q. 종료");
        return getInput();
    }

    public String enteredChoices() {
        System.out.println("#############################################" + newline
                + "과정: 백엔드 | 프론트엔드" + newline
                + "미션:" + newline
                + "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임" + newline
                + "  - 레벨2: 장바구니 | 결제 | 지하철노선도" + newline
                + "  - 레벨3: " + newline
                + "  - 레벨4: 성능개선 | 배포" + newline
                + "  - 레벨5: " + newline
                + "############################################" + newline
                + "과정, 레벨, 미션을 선택하세요." + newline
                + "ex) 백엔드, 레벨1, 자동차경주");
        return getInput();
    }

    public String enteredRematching() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?" + newline
                + "네 | 아니오");
        return getInput();
    }


    private String getInput() {
        return Console.readLine();
    }
}
