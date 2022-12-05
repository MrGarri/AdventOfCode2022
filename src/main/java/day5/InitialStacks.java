package day5;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@UtilityClass
public class InitialStacks {
    public List<Stack<String>> getExampleStacks() {
        List<Stack<String>> stacks = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            switch (i) {
                case 1 -> stacks.add(getStackOfCrates("ZN"));
                case 2 -> stacks.add(getStackOfCrates("MCD"));
                case 3 -> stacks.add(getStackOfCrates("P"));
            }
        }

        return stacks;
    }

    public List<Stack<String>> getMainStacks() {
        List<Stack<String>> stacks = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            Stack<String> stack = new Stack<>();
            switch (i) {
                case 1 -> stacks.add(getStackOfCrates("DBJV"));
                case 2 -> stacks.add(getStackOfCrates("PVBWRDF"));
                case 3 -> stacks.add(getStackOfCrates("RGFLDCWQ"));
                case 4 -> stacks.add(getStackOfCrates("WJPMLNDB"));
                case 5 -> stacks.add(getStackOfCrates("HNBPCSQ"));
                case 6 -> stacks.add(getStackOfCrates("RDBSNG"));
                case 7 -> stacks.add(getStackOfCrates("ZBPMQFSH"));
                case 8 -> stacks.add(getStackOfCrates("WLF"));
                case 9 -> stacks.add(getStackOfCrates("SVFMR"));
            }
        }

        return stacks;
    }

    private Stack<String> getStackOfCrates(String crates) {
        return crates.chars()
                .mapToObj(c -> (char) c)
                .map(String::valueOf)
                .collect(Stack::new, Stack::push, Stack::addAll);
    }
}
