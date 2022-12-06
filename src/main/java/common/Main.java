package common;

import day1.CaloriesCounting;
import day2.RockPaperScissors;
import day3.RucksackReorganization;
import day4.CampCleanup;
import day5.SupplyStacks;
import day6.TuningTrouble;

import java.util.List;

public class Main {

    private static final List<Puzzle> PUZZLES = List.of(
            new CaloriesCounting(InputFetcher.fetchMainInput("day1")),
            new RockPaperScissors(InputFetcher.fetchMainInput("day2")),
            new RucksackReorganization(InputFetcher.fetchMainInput("day3")),
            new CampCleanup(InputFetcher.fetchMainInput("day4")),
            new SupplyStacks(InputFetcher.fetchMainInput("day5")),
            new TuningTrouble(InputFetcher.fetchMainInput("day6"))
    );

    public static void main(String[] args) {
        PUZZLES.forEach(puzzle -> {
            System.out.format("##### DAY %s #####\n", puzzle.getDay());
            System.out.format("Part 1: %s\n", puzzle.solvePart1());
            System.out.format("Part 2: %s\n\n", puzzle.solvePart2());
        });
    }

}
