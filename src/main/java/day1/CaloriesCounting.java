package day1;

import common.Puzzle;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CaloriesCounting extends Puzzle {

    public CaloriesCounting(List<String> puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public String solvePart1() {
        return getHighestCalories();
    }

    @Override
    public String solvePart2() {
        return getTopSumOfCalories(3);
    }

    @Override
    public int getDay() {
        return 1;
    }

    private String getHighestCalories() {
        List<Integer> caloriesPerElf = getCaloriesPerElf(getPuzzleInput());

        Integer highestCalories = caloriesPerElf.stream().max(Comparator.naturalOrder()).orElse(0);

        return String.valueOf(highestCalories);
    }

    private String getTopSumOfCalories(final int topToSum) {
        List<Integer> caloriesPerElf = getCaloriesPerElf(getPuzzleInput());

        Integer topSumOfCalories = caloriesPerElf.stream()
                .sorted(Comparator.reverseOrder())
                .limit(topToSum)
                .reduce(Integer::sum)
                .orElse(0);

        return String.valueOf(topSumOfCalories);
    }

    private List<Integer> getCaloriesPerElf(@NonNull final List<String> elvesCaloriesCounted) {
        List<Integer> caloriesPerElf = new ArrayList<>();

        int sumOfCalories = 0;
        for (String calories : elvesCaloriesCounted) {
            if (calories.isBlank()) {
                caloriesPerElf.add(sumOfCalories);
                sumOfCalories = 0;
            } else {
                sumOfCalories += Integer.parseInt(calories);
            }
        }
        caloriesPerElf.add(sumOfCalories);

        return caloriesPerElf;
    }

}
