package day5;

import common.Puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class SupplyStacks extends Puzzle {
    private record Step(int numberOfCrates, int from, int to) {}

    public SupplyStacks(List<String> puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public String solvePart1() {
        List<Stack<String>> stacks = InitialStacks.getMainStacks();

        for (Step step : getSteps()) {
            for (int i = 0; i < step.numberOfCrates(); i++) {
                String top = stacks.get(step.from() - 1).pop();
                stacks.get(step.to() - 1).push(top);
            }
        }

        return stacks.stream()
                .map(Stack::peek)
                .collect(Collectors.joining());
    }

    @Override
    public String solvePart2() {
        List<Stack<String>> stacks = InitialStacks.getMainStacks();

        for (Step step : getSteps()) {
            List<String> toBeMoved = new ArrayList<>();
            for (int i = 0; i < step.numberOfCrates(); i++) {
                toBeMoved.add(0, stacks.get(step.from() - 1).pop());
            }
            toBeMoved.forEach(crate -> stacks.get(step.to() - 1).push(crate));
        }

        return stacks.stream()
                .map(Stack::peek)
                .collect(Collectors.joining());
    }

    @Override
    public int getDay() {
        return 5;
    }

    private List<Step> getSteps() {
        return getPuzzleInput().stream()
                .map(this::getStep)
                .collect(Collectors.toList());
    }

    private Step getStep(String move) {
        String[] parts = move.split(" ");
        return new Step(
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[3]),
                Integer.parseInt(parts[5])
        );
    }
}
