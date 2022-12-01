package common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class Puzzle {

    @Getter
    private final List<String> puzzleInput;

    public abstract String solvePart1();
    public abstract String solvePart2();

    public abstract int getDay();

}
