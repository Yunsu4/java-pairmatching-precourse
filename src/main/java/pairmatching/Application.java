package pairmatching;

import pairmatching.controller.PairMatchingController;

import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        PairMatchingController pairMatchingController = new PairMatchingController(inputView, outputView);


        pairMatchingController.matchingSystemRun();
    }
}
