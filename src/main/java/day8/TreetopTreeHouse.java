package day8;

import common.Puzzle;

import java.util.List;

public class TreetopTreeHouse extends Puzzle {
    public TreetopTreeHouse(List<String> puzzleInput) {
        super(puzzleInput);
    }

    private record GridDimensions(int width, int height) {
        public int calculatePerimeterSize() {
            return this.width + (this.width - 2) + 2 * (this.height - 1);
        }
    }

    @Override
    public Integer solvePart1() {
        GridDimensions gridDimensions = getGridDimensions();
        int visibleTrees = gridDimensions.calculatePerimeterSize();
        int[][] treesGrid = getTreesGrid();

        for (int i = 1; i < gridDimensions.width() - 1; i++) {
            for (int j = 1; j < gridDimensions.height() - 1; j++) {
                if (isVisible(treesGrid, i, j)) {
                    visibleTrees++;
                }
            }
        }

        return visibleTrees;
    }

    @Override
    public Integer solvePart2() {
        GridDimensions gridDimensions = getGridDimensions();
        int[][] treesGrid = getTreesGrid();
        int maxScenicScore = 0;

        for (int i = 1; i < gridDimensions.width() - 1; i++) {
            for (int j = 1; j < gridDimensions.height() - 1; j++) {
                maxScenicScore = Math.max(maxScenicScore, getScenicScore(treesGrid, i, j));
            }
        }

        return maxScenicScore;
    }

    @Override
    public int getDay() {
        return 8;
    }

    private GridDimensions getGridDimensions() {
        final int width = getPuzzleInput().get(0).length();
        final int height = getPuzzleInput().size();

        return new GridDimensions(width, height);
    }

    private int[][] getTreesGrid() {
        List<int[]> treesGrid = getPuzzleInput().stream()
                .map(s -> {
                    int[] trees = new int[s.length()];
                    for (int i = 0; i < s.length(); i++) {
                        trees[i] = Character.getNumericValue(s.charAt(i));
                    }
                    return trees;
                })
                .toList();

        return treesGrid.toArray(new int[0][0]);
    }

    private boolean isVisible(final int[][] treesGrid, final int row, final int column) {
        return isVisibleInColumn(treesGrid, row, column) || isVisibleInRow(treesGrid, row, column);
    }

    private boolean isVisibleInColumn(final int[][] treesGrid, final int row, final int column) {
        final int tree = treesGrid[row][column];
        boolean visibleUp = true;
        boolean visibleDown = true;

        for (int i = 0; i < row; i++) {
            if (treesGrid[i][column] >= tree) {
                visibleUp = false;
                break;
            }
        }

        for (int i = row + 1; i < getGridDimensions().height(); i++) {
            if (treesGrid[i][column] >= tree) {
                visibleDown = false;
                break;
            }
        }

        return visibleUp || visibleDown;
    }

    private boolean isVisibleInRow(final int[][] treesGrid, final int row, final int column) {
        final int tree = treesGrid[row][column];
        boolean visibleLeft = true;
        boolean visibleRight = true;

        for (int j = 0; j < column; j++) {
            if (treesGrid[row][j] >= tree) {
                visibleLeft = false;
                break;
            }
        }

        for (int j = column + 1; j < getGridDimensions().width(); j++) {
            if (treesGrid[row][j] >= tree) {
                visibleRight = false;
                break;
            }
        }

        return visibleLeft || visibleRight;
    }

    private int getScenicScore(final int[][] treesGrid, final int row, final int column) {
        final int tree = treesGrid[row][column];

        int leftScore = 0;
        for (int j = column - 1; j >= 0; j--) {
            leftScore++;
            if (treesGrid[row][j] >= tree) break;
        }

        int rightScore = 0;
        for (int j = column + 1; j < getGridDimensions().width(); j++) {
            rightScore++;
            if (treesGrid[row][j] >= tree) break;
        }

        int upScore = 0;
        for (int i = row - 1; i >= 0; i--) {
            upScore++;
            if (treesGrid[i][column] >= tree) break;
        }

        int downScore = 0;
        for (int i = row + 1; i < getGridDimensions().height(); i++) {
            downScore++;
            if (treesGrid[i][column] >= tree) break;
        }

        return leftScore * rightScore * upScore * downScore;
    }
}
