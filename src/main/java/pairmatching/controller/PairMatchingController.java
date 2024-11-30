package pairmatching.controller;

import java.util.function.Function;
import org.mockito.internal.util.Supplier;
import pairmatching.enums.Course;
import pairmatching.enums.Crew;
import pairmatching.enums.Level;
import pairmatching.enums.Missions;
import pairmatching.model.MatchingRecord;
import pairmatching.model.Pairs;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {

    public static final String COMMA = "\\s*,\\s*";
    private InputView inputView;
    private OutputView outputView;
    private Crew backend;
    private Crew frontend;
    private MatchingRecord backendRecord;
    private MatchingRecord frontendRecord;


    public PairMatchingController(InputView inputView, OutputView outputView){
        this.inputView = inputView;
        this.outputView = outputView;
        this.backend = new Crew(Course.BACKEND);
        this.frontend = new Crew(Course.FRONTEND);
        this.backendRecord = new MatchingRecord();
        this.frontendRecord = new MatchingRecord();
    }

    public void matchingSystemRun(){
        while(true){
            try{
                String input = getValidInput(inputView::displayStartMessage, this::inputValidator);
                executeWithInput(input);
            }catch (IllegalArgumentException e){
                if(e.getMessage().equals("exit")){
                    return;
                }
                System.out.println(e.getMessage());
            }
        }
    }

    private String inputValidator(String input){
        if(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("Q")){
            return input;
        }
        throw new IllegalArgumentException("[ERROR] 올바른 입력이 아닙니다.");
    }

    private void executeWithInput(String input) throws IllegalArgumentException{
        if(!input.equals("Q")){
            performFunctions(input, inputView);
        }
        if(input.equals("Q")){
            throw new IllegalArgumentException("exit");
        }
    }

    private void performFunctions(String input, InputView inputView) throws IllegalArgumentException{
        executePairMatching(input, inputView);
        executePairInquiry(input, inputView);
        executePairInitialization(input);
    }

    private void executePairMatching(String input, InputView inputView) {
        if(input.equals("1")){
            String[] matchingCriteria = parseInput(inputView);
            String course = Course.isCourse(matchingCriteria[0]);
            Level level = Level.getLevel(matchingCriteria[1]);
            Missions mission = Missions.getValidMission(level, matchingCriteria[2]);
            
            matchDistinctPairs(course, mission);
        }
    }

    private void executePairInquiry(String input, InputView inputView) {
        if(input.equals("2")){
            String[] matchingCriteria = parseInput(inputView);

            String course = Course.isCourse(matchingCriteria[0]);
            Level level = Level.getLevel(matchingCriteria[1]);
            Missions mission = Missions.getValidMission(level, matchingCriteria[2]);
            MatchingRecord record = getRecordThroughCourse(course);
            Pairs matchedPairs = record.getRecordThroughMission(mission);
            matchedPairs.display();
        }
    }

    private void executePairInitialization(String input) {
        if(input.equals("3")){
            backendRecord.clear();
            frontendRecord.clear();
            outputView.displayReset();
        }
    }

    private String[] parseInput(InputView inputView){
        String matchingCriteria = inputView.enteredChoices();
        return matchingCriteria.split(COMMA);
    }

    private void matchDistinctPairs(String course, Missions mission) {
        int count = 0;
        Pairs matchedPairs;

        MatchingRecord record = getRecordThroughCourse(course);


        while (true) {
            if (record.containPreviousMatching(mission)) {
                String input = getValidInput(inputView::enteredRematching, this::enteredRematchingValidator);
                if ("네".equals(input)) {
                    matchedPairs = matchingPairs(course);
                    record.replace(mission, matchedPairs);
                    break;
                }
                break;
            }

            matchedPairs = matchingPairs(course);

            if (record.containTheSamePairInTheSameLevel(mission, matchedPairs)) {
                count++;
                overTryCount(count);
                continue;
            }
            record.add(mission, matchedPairs);
            break;
        }
    }

    private String enteredRematchingValidator(String input){
        if(input.equals("네") || input.equals("아니오")){
            return input;
        }
        throw new IllegalArgumentException("[ERROR] 네 또는 아니오만 입력 가능합니다.");
    }

    private void overTryCount(int count) {
        if (count >= 3) {
            throw new IllegalStateException("[ERROR] 매칭 시도 횟수가 3회를 초과했습니다.");
        }
    }

    private Pairs matchingPairs(String course){
        Crew crew = getCrewThroughCourse(course);
        Pairs matchedPairs = crew.matchPairs();
        displayMatchedPairs(matchedPairs);
        return matchedPairs;
    }

    private Crew getCrewThroughCourse(String course) {
        if (Course.isBackend(course)) {
            return backend;
        }
        return frontend;
    }

    private MatchingRecord getRecordThroughCourse(String course) {
        if (Course.isBackend(course)) {
            return backendRecord;
        }
        return frontendRecord;
    }

    private void displayMatchedPairs(Pairs matchedPairs) {
        outputView.displayPairMatchingResult();
        matchedPairs.display();
    }



    private <T> T getValidInput(Supplier<String> inputSupplier, Function<String, T> converter) {
        while (true) {
            String input = inputSupplier.get();
            try {
                return converter.apply(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
