package pairmatching;

import java.rmi.dgc.Lease;
import pairmatching.controller.PairMatchingController;
import pairmatching.enums.Course;
import pairmatching.enums.Level;
import pairmatching.enums.Missions;
import pairmatching.model.BackendCrew;
import pairmatching.model.FrontendCrew;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        FrontendCrew frontendCrew = new FrontendCrew();
        BackendCrew backendCrew = new BackendCrew();

        PairMatchingController pairMatchingController = new PairMatchingController(inputView, outputView,
                backendCrew,frontendCrew);


        String input = inputView.displayStartMessage();

        //페어 매칭
        if(input.equals("1")){
            String matchingCriteria = inputView.enteredChoices();
            String[] criteria = matchingCriteria.split("\\s*,\\s*");

            String course = Course.isCourse(criteria[0]);
            String level = Level.isLevel(criteria[1]);
            try{
                String mission = Missions.isMission(level, criteria[2]);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
            /*
            //String mission = Missions.isMission(level, criteria[2]);
            if(!Missions.hasMission(level, criteria[2])){
                throw new IllegalArgumentException("[ERROR] 없는 미션 입니다.");
            }

             */

            pairMatchingController.matching(course);


        }
        if(input.equals("Q")){
            return;
        }

    }
}
