package day2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Puzzle;

public class RockPaperScissors extends Puzzle {

    private static final Map<String, String> ENCRYPTED_CHOICES_TO_PLAIN_CHOICES = new HashMap<>() {{
        put("A", "ROCK");
        put("B", "PAPER");
        put("C", "SCISSORS");
        put("X", "ROCK");
        put("Y", "PAPER");
        put("Z", "SCISSORS");
    }};

    private static final Map<String, String> WINNING_COMBINATIONS = new HashMap<>() {{
        put("ROCK", "SCISSORS");
        put("PAPER", "ROCK");
        put("SCISSORS", "PAPER");
    }};

    private static final Map<String, String> DEFEATING_COMBINATIONS = new HashMap<>() {{
        put("ROCK", "PAPER");
        put("PAPER", "SCISSORS");
        put("SCISSORS", "ROCK");
    }};

    private static final Map<String, Integer> CHOICE_TO_SCORE = new HashMap<>() {{
        put("ROCK", 1);
        put("PAPER", 2);
        put("SCISSORS", 3);
    }};

    private static final int WIN_SOCRE = 6;
    private static final int DRAW_SCORE = 3;
    private static final int DEFEAT_SCORE = 0;

    private static final String EXPECTED_DEFEAT = "X";
    private static final String EXPECTED_DRAW = "Y";
    private static final String EXPECTED_WIN = "Z";

    public RockPaperScissors(List<String> puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public String solvePart1() {
        return calculateStrategyGuideScore();
    }

    @Override
    public String solvePart2() {
        return calculateRealStrategyGuideScore();
    }

    @Override
    public int getDay() {
        return 2;
    }

    private String calculateStrategyGuideScore() {
        int strategyGuideScore = getPuzzleInput().stream().map(game -> {
            final String[] choices = game.split(" ");
            final String opponentChoice = ENCRYPTED_CHOICES_TO_PLAIN_CHOICES.get(choices[0]);
            final String myChoice = ENCRYPTED_CHOICES_TO_PLAIN_CHOICES.get(choices[1]);

            return calculateGameScore(opponentChoice, myChoice);
        })
        .reduce((a, b) -> a + b)
        .orElse(0);

        return String.valueOf(strategyGuideScore);
    }

    private String calculateRealStrategyGuideScore() {
        int strategyGuideScore = getPuzzleInput().stream().map(game -> {
            final String[] choices = game.split(" ");
            final String opponentChoice = ENCRYPTED_CHOICES_TO_PLAIN_CHOICES.get(choices[0]);
            final String expectedResult = choices[1];

            final String myChoice = getMyChoice(opponentChoice, expectedResult);

            return calculateGameScore(opponentChoice, myChoice);
        })
        .reduce((a, b) -> a + b)
        .orElse(0);

        return String.valueOf(strategyGuideScore);
    }

    private int calculateGameScore(final String opponentChoice, final String myChoice) {
        if (WINNING_COMBINATIONS.get(opponentChoice).equals(myChoice)) {
            return CHOICE_TO_SCORE.get(myChoice) + DEFEAT_SCORE;
        } else if (WINNING_COMBINATIONS.get(myChoice).equals(opponentChoice)) {
            return CHOICE_TO_SCORE.get(myChoice) + WIN_SOCRE;
        } else {
            return CHOICE_TO_SCORE.get(myChoice) + DRAW_SCORE;
        }
    }

    private String getMyChoice(String opponentChoice, String expectedResult) {
        String myChoice = "";

        switch (expectedResult) {
            case EXPECTED_DEFEAT:
                myChoice = WINNING_COMBINATIONS.get(opponentChoice);
                break;
            case EXPECTED_DRAW: 
                myChoice = opponentChoice;
                break;
            case EXPECTED_WIN:
                myChoice = DEFEATING_COMBINATIONS.get(opponentChoice);
        }

        return myChoice;
    }
    
}
