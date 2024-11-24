package pairmatching.controller;

import java.util.ArrayList;
import java.util.List;
import pairmatching.enums.Course;
import pairmatching.model.BackendCrew;
import pairmatching.model.FrontendCrew;
import pairmatching.model.Pair;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {

    private InputView inputView;
    private OutputView outputView;

    private BackendCrew backendCrew;
    private FrontendCrew frontendCrew;

    public PairMatchingController(InputView inputView, OutputView outputView,
                                  BackendCrew backendCrew, FrontendCrew frontendCrew){
        this.inputView = inputView;
        this.outputView = outputView;
        this.backendCrew = backendCrew;
        this.frontendCrew = frontendCrew;
    }
    public void matching(String course){
        if(course.equals(Course.BACKEND.getMessage())){
            Pair matchedPairs = backendCrew.matchPairs();
            outputView.displayPairMatchingResult();
            matchedPairs.display();
        }
        if(course.equals(Course.FRONTEND.getMessage())){
            Pair matchedPairs = frontendCrew.matchPairs();
            outputView.displayPairMatchingResult();
            matchedPairs.display();
        }

    }

}
