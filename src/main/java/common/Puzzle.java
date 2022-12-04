package common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class Puzzle {

    @Getter
    private final List<String> puzzleInput;

    public abstract Object solvePart1();
    public abstract Object solvePart2();

    public abstract int getDay();

}
