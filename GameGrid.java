import java.util.Arrays;
import java.util.HashMap;

public class GameGrid {
    private final int height;
    private final int width;
    private final int inRowToWin;
    private final char[][] grid;
    private final HashMap<Pos, Integer> gameMap ;
    private record Pos(int height, int width) {};
    private final boolean[] horizontalRow;
    private final boolean[] verticalRow;
    private final boolean[] firstDiagonalRow;
    private final boolean[] secondDiagonalRow;

    public GameGrid(int height, int width, int inRowToWin) {
        this.width = width;
        this.height = height;
        this.inRowToWin = inRowToWin;
        gameMap = new HashMap<>();
        grid = new char[height][width];
        for (char[] arr : grid) {
            Arrays.fill(arr, ' ');
        }
        int size = inRowToWin * 2 - 1;
        horizontalRow = new boolean[size];
        verticalRow = new boolean[size];
        firstDiagonalRow = new boolean[size];
        secondDiagonalRow = new boolean[size];
    }

    public void print() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.print("  ");
        for (int i = 0; i < width; i++) {
            System.out.print("  " + alphabet.charAt(i));
        }
        System.out.println();
        for (int i = 1; i <= height; i++) {
            if (i < 10) {
                System.out.print(' ');
            }
            System.out.print(i + " ");
            for (int j = 0; j < width; j++) {
                System.out.print("[" + grid[i - 1][j] + "]");
            }
            System.out.println();
        }
    }

    public boolean tryPut(int height, int width, boolean isFirstPlayerStep) {
        if (height >= this.height || width >= this.width) {
            return false;
        }
        if (gameMap.containsKey(new Pos(height, width))) {
            return false;
        }
        gameMap.put(new Pos(height, width), isFirstPlayerStep ? 1 : 0);
        grid[height][width] = isFirstPlayerStep ? 'X' : 'O';
        return true;
    }

    public boolean isGameOver(int height, int width) {
        int index = 0;
        int ReferenceFieldCondition = gameMap.get(new Pos(height, width));
        for (int i = -inRowToWin + 1; i < inRowToWin; i++) {
            if (gameMap.containsKey(new Pos(height + i, width))) {
                verticalRow[index] = gameMap.get(new Pos(height + i, width)) == ReferenceFieldCondition;
            }
            if (gameMap.containsKey(new Pos(height, width + i))) {
                horizontalRow[index] = gameMap.get(new Pos(height, width + i)) == ReferenceFieldCondition;
            }
            if (gameMap.containsKey(new Pos(height - i, width + i))) {
                firstDiagonalRow[index] = gameMap.get(new Pos(height - i, width + i)) == ReferenceFieldCondition;
            }
            if (gameMap.containsKey(new Pos(height + i, width + i))) {
                secondDiagonalRow[index] = gameMap.get(new Pos(height + i, width + i)) == ReferenceFieldCondition;
            }
            index++;
        }
        return isEnoughInRow(horizontalRow) || isEnoughInRow(verticalRow) || isEnoughInRow(firstDiagonalRow) || isEnoughInRow(secondDiagonalRow);
    }

    private boolean isEnoughInRow(boolean[] row) {
        int numberInRow = 0;
        for (boolean condition : row) {
            numberInRow += (condition ? 1 : 0);
            numberInRow *= (condition ? 1 : 0);
            if (numberInRow >= inRowToWin) {
                return true;
            }
        }
        return false;
    }
}
