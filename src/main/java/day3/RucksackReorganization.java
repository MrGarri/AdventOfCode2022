package day3;

import common.Puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class RucksackReorganization extends Puzzle {

    public RucksackReorganization(List<String> puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public String solvePart1() {
        final int priority = getPuzzleInput().stream()
                .map(this::splitInTwoHalves)
                .map(this::getRepeatedItem)
                .map(this::calculatePriority)
                .reduce(Integer::sum)
                .orElse(0);

        return String.valueOf(priority);
    }

    @Override
    public String solvePart2() {
        final List<List<String>> elvesGroups = getElvesGroups();

        final int priority = elvesGroups.stream()
                .map(this::getRepeatedItem)
                .map(this::calculatePriority)
                .reduce(Integer::sum)
                .orElse(0);

        return String.valueOf(priority);
    }

    @Override
    public int getDay() {
        return 3;
    }

    private String[] splitInTwoHalves(final String s) {
        return new String[] {s.substring(0, s.length() / 2), s.substring(s.length() / 2)};
    }

    private char getRepeatedItem(final String[] halves) {
        final List<Character> firstHalf = stringToListOfChars(halves[0]);
        final List<Character> secondHalf = stringToListOfChars(halves[1]);
        firstHalf.retainAll(secondHalf);
        return firstHalf.get(0);
    }

    private char getRepeatedItem(final List<String> listOfItems) {
        AtomicReference<List<Character>> commonItems = new AtomicReference<>(stringToListOfChars(listOfItems.get(0)));

        listOfItems.subList(1, listOfItems.size())
                .stream()
                .map(this::stringToListOfChars)
                .forEach(items -> {
                    commonItems.set(getCommonItems(commonItems.get(), items));
                });

        return commonItems.get().get(0);
    }

    private List<Character> stringToListOfChars(final String s) {
        return s.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
    }

    private List<Character> getCommonItems(final List<Character> l1, final List<Character> l2) {
        ArrayList<Character> l1Copy = new ArrayList<>(l1);
        l1Copy.retainAll(l2);
        return l1Copy;
    }

    private int calculatePriority(final char c) {
        return Character.isLowerCase(c) ? c - 96 : c - 38;
    }

    private List<List<String>> getElvesGroups() {
        final List<List<String>> elvesGroups = new ArrayList<>();
        List<String> puzzleInput = getPuzzleInput();

        for (int i = 0; i < puzzleInput.size(); i += 3) {
            elvesGroups.add(puzzleInput.subList(i, Math.min(i + 3, puzzleInput.size())));
        }

        return elvesGroups;
    }

}
