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

        while(true){
            try{
                String input = inputView.displayStartMessage();
                if(!input.equals("Q")){
                    run(input, inputView, pairMatchingController);
                }
                if(input.equals("Q")){
                    throw new IllegalArgumentException("exit");
                }
            }catch (IllegalArgumentException e){
                if(e.getMessage().equals("exit")){
                    return;
                }
                System.out.println(e.getMessage());
            }
        }


    }

    private static void run(String input, InputView inputView, PairMatchingController pairMatchingController) {
        System.out.println("입력된 값: "+ input);

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


            pairMatchingController.matching(course);
        }
    }
}
