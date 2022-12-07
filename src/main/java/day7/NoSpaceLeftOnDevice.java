package day7;

import common.Puzzle;

import java.util.*;

public class NoSpaceLeftOnDevice extends Puzzle {
    private static final String CD = "cd";
    private static final String COMMAND_PROMPT = "$";
    private static final String DIR = "dir";
    private static final String OUT_ONE_LEVEL = "..";

    private record Node(Map<String, Node> children, long size) {}

    public NoSpaceLeftOnDevice(List<String> puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public Object solvePart1() {
        Node root = getChildren();

        return getTotalSizeOfNode(root, 100000);
    }

    @Override
    public Object solvePart2() {
        Node root = getChildren();

        return getSizeOfDirectoryToDelete(root, root.size())
                .stream()
                .min(Comparator.naturalOrder())
                .orElse(0L);
    }

    @Override
    public int getDay() {
        return 7;
    }

    int i = 0; // This needs a refactor, there should not be global variables being used as loop controllers
    private Node getChildren() {
        Map<String, Node> children = new HashMap<>();

        for (; i < getPuzzleInput().size(); i++) {
            String line = getPuzzleInput().get(i);
            String[] parts = line.split(" ");

            if (parts[0].equals(COMMAND_PROMPT)) {
                if (parts[1].equals(CD)) {
                    if (parts[2].equals(OUT_ONE_LEVEL)) {
                        break;
                    } else {
                        i++;
                        children.put(parts[2], getChildren());
                    }
                }
            } else {
                if (parts[0].equals(DIR)) {
                    children.put(parts[1], new Node(new HashMap<>(), 0));
                } else {
                    long fileSize = Long.parseLong(parts[0]);
                    children.put(parts[1], new Node(new HashMap<>(), fileSize));
                }
            }
        }

        long nodeSize = children.values().stream().mapToLong(Node::size).sum();
        return new Node(children, nodeSize);
    }

    private long getTotalSizeOfNode(Node node, long maxChildrenSize) {
        long totalSize = 0;

        for (Node children : node.children().values()) {
            if (!children.children.isEmpty() && children.size <= maxChildrenSize) {
                totalSize += children.size;
            }
            totalSize += getTotalSizeOfNode(children, maxChildrenSize);
        }

        return totalSize;
    }

    private List<Long> getSizeOfDirectoryToDelete(Node node, long rootSize) {
        List<Long> totalSize = new ArrayList<>();

        for (Node child : node.children().values()) {
            if (!child.children().isEmpty() && child.size() >= rootSize - (70000000 - 30000000)) {
                totalSize.add(child.size());
            }
            totalSize.addAll(getSizeOfDirectoryToDelete(child, rootSize));
        }

        return totalSize;
    }
}
