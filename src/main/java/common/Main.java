package common;

import day1.CaloriesCounting;

import java.util.List;

public class Main {

    private static final List<Puzzle> PUZZLES = List.of(
            new CaloriesCounting(InputFetcher.fetchMainInput("day1"))
    );

    public static void main(String[] args) {
        PUZZLES.forEach(puzzle -> {
            System.out.format("##### DAY %d #####\n", puzzle.getDay());
            System.out.format("Part 1: %s\n", puzzle.solvePart1());
            System.out.format("Part 2: %s\n\n", puzzle.solvePart2());
        });
    }

}
