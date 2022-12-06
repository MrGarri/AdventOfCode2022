package day6;

import common.Puzzle;
import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;

public class TuningTrouble extends Puzzle {
    public TuningTrouble(List<String> puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public Integer solvePart1() {
        final String buffer = getPuzzleInput().get(0);

        for (int i = 0; i < buffer.length() - 3; i++) {
            String marker = buffer.substring(i, i + 4);
            Set<Character> charSet = stringToCharSet(marker);
            if (charSet.size() == marker.length()) {
                return i + 4;
            }
        }

        return -1;
    }

    @Override
    public Object solvePart2() {
        final String buffer = getPuzzleInput().get(0);

        for (int i = 0; i < buffer.length() - 13; i++) {
            String marker = buffer.substring(i, i + 14);
            Set<Character> charSet = stringToCharSet(marker);
            if (charSet.size() == marker.length()) {
                return i + 14;
            }
        }

        return -1;
    }

    @Override
    public int getDay() {
        return 6;
    }

    private Set<Character> stringToCharSet(@NonNull final String s) {
        return s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());
    }
}
