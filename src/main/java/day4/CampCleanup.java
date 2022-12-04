package day4;

import common.Puzzle;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public class CampCleanup extends Puzzle {
    public CampCleanup(List<String> puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public Long solvePart1() {
        return getPuzzleInput().stream()
                .filter(pair -> isOverlapping(pair, this::isFullyOverlapping))
                .count();
    }

    @Override
    public Long solvePart2() {
        return getPuzzleInput().stream()
                .filter(pair -> isOverlapping(pair, this::isPartialOverlapping))
                .count();
    }

    @Override
    public int getDay() {
        return 4;
    }

    private boolean isOverlapping(final String pair, BiPredicate<int[], int[]> overlappingFunction) {
        final String[] assignments = pair.split(",");

        final int[] rangeOne = Arrays.stream(assignments[0].split("-"))
                .mapToInt(Integer::parseInt)
                .toArray();
        final int[] rangeTwo = Arrays.stream(assignments[1].split("-"))
                .mapToInt(Integer::parseInt)
                .toArray();

        return overlappingFunction.test(rangeOne, rangeTwo);
    }

    private boolean isFullyOverlapping(int[] rangeOne, int[] rangeTwo) {
        boolean rangeOneOverlaps = rangeOne[0] <= rangeTwo[0] && rangeOne[1] >= rangeTwo[1];
        boolean rangeTwoOverlaps = rangeTwo[0] <= rangeOne[0] && rangeTwo[1] >= rangeOne[1];

        return rangeOneOverlaps || rangeTwoOverlaps;
    }

    private boolean isPartialOverlapping(int[] rangeOne, int[] rangeTwo) {
        return rangeOne[0] <= rangeTwo[1] && rangeOne[1] >= rangeTwo[0];
    }
}
