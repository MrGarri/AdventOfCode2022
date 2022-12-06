package day6;

import common.Puzzle;

import java.util.List;
import java.util.stream.IntStream;

public class TuningTrouble extends Puzzle {
    public TuningTrouble(List<String> puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public Integer solvePart1() {
        return findMarker(4);
    }

    @Override
    public Object solvePart2() {
        return findMarker(14);
    }

    @Override
    public int getDay() {
        return 6;
    }

    private int findMarker(final int distinctChars) {
        final String buffer = getPuzzleInput().get(0);

        int firstMarker = IntStream.range(0, buffer.length() - distinctChars - 1)
                .filter(i -> buffer.substring(i, i + distinctChars).chars().distinct().count() == distinctChars)
                .findFirst()
                .orElse(-1);

        return firstMarker + distinctChars;
    }
}
