package pairmatching.view;

import java.util.List;
import pairmatching.model.Pair;

public class OutputView {
    private static final String newline = System.getProperty("line.separator");

    public void displayReset() {
        System.out.println("초기화 되었습니다.");
    }

    public void displayPairMatchingResult() {
        System.out.println("페어 매칭 결과입니다.");
    }

    public void printSeparator() {
        System.out.print(" : ");
    }



    public void printWinningDetails(int sameNumberCount, String winningPrize,
                                    int matchedLottoCount, boolean matchBonus) {
        StringBuilder winningDetail = new StringBuilder();

        winningDetail.append(sameNumberCount);
        winningDetail.append("개 일치");
        if (matchBonus) {
            winningDetail.append(", 보너스 볼 일치");
        }
        winningDetail.append(" (");
        winningDetail.append(winningPrize);
        winningDetail.append("원) - ");
        winningDetail.append(matchedLottoCount);
        winningDetail.append("개");

        String totalWinningDetails = winningDetail.toString();
        System.out.println(totalWinningDetails);
    }

}
