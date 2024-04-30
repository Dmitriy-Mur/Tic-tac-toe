import java.util.ArrayList;
import java.util.HashMap;

public class GameGrid {
    private final int height;
    private final int width;
    private final int inRowToWin;
    private final char[][] grid;
    public HashMap<Pair, Integer> gameMap = new HashMap<>();

    public static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public GameGrid(int height, int width, int inRowToWin) {
        this.width = width;
        this.height = height;
        this.inRowToWin = inRowToWin;
        this.grid = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public void print() {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.print(' ');
        if (height > 9) {
            System.out.print(' ');
        }
        for (int i = 0; i < width; i++) {
            System.out.print("  " + str.charAt(i));
        }
        System.out.print('\n');
        for (int i = 1; i <= height; i++) {
            if (i < 10) {
                System.out.print(' ');
            }
            System.out.print(i + " ");
            for (int j = 0; j < width; j++) {
                System.out.print("[" + grid[i - 1][j] + "]");
            }
            System.out.print('\n');
        }
    }

    public boolean tryPut(int height, int width, int isFirstPlayerStep) {
        if (gameMap.containsKey(new Pair(height, width))) {
            return false;
        }
        gameMap.put(new Pair(height, width), isFirstPlayerStep);
        return true;
    }


//    public boolean tryPut(int height, int width, boolean isFirstPlayerStep) {
//        if (height > this.height || width > this.width) {
//            return false;
//        }
//        if (grid[height][width] != ' ') {
//            return false;
//        }
//        if (isFirstPlayerStep) {
//            grid[height][width] = 'X';
//        } else {
//            grid[height][width] = '0';
//        }
//        return true;
//    }
//
//    public boolean isGameOver() {
//        int n = height;
//        int m = width;
//        int x = inRowToWin;
//        // Check for horizontal wins
//        for (int i = 0; i < n; i++) {
//            char first = grid[i][0];
//            if (first != ' ') {
//                int count = 1;
//                for (int j = 1; j < m; j++) {
//                    if (grid[i][j] == first) {
//                        count++;
//                        if (count == x) {
//                            return true;
//                        }
//                    } else {
//                        break;
//                    }
//                }
//            }
//        }
//
//        // Check for vertical wins
//        for (int j = 0; j < m; j++) {
//            char first = grid[0][j];
//            if (first != ' ') {
//                int count = 1;
//                for (int i = 1; i < n; i++) {
//                    if (grid[i][j] == first) {
//                        count++;
//                        if (count == x) {
//                            return true;
//                        }
//                    } else {
//                        break;
//                    }
//                }
//            }
//        }
//
//        // Check for diagonal wins
//        if (n == m) {
//            char first = grid[0][0];
//            if (first != ' ') {
//                int count = 1;
//                for (int i = 1; i < n; i++) {
//                    if (grid[i][i] == first) {
//                        count++;
//                        if (count == x) {
//                            return true;
//                        }
//                    } else {
//                        break;
//                    }
//                }
//            }
//
//            first = grid[0][m - 1];
//            if (first != ' ') {
//                int count = 1;
//                for (int i = 1; i < n; i++) {
//                    if (grid[i][m - 1 - i] == first) {
//                        count++;
//                        if (count == x) {
//                            return true;
//                        }
//                    } else {
//                        break;
//                    }
//                }
//            }
//        }
//        return false;
//    }

    private boolean isEnoughInRow(boolean[] row) {
        int numberInRow = 0;
        for (boolean condition : row) {
            numberInRow += condition ? 1 : 0;
            numberInRow *= condition ? 1 : 0;
        }
        return numberInRow == inRowToWin;
    }

    public boolean isGameOver(int xCoord, int yCoord) {
        boolean[] horizontalRow = new boolean[inRowToWin * 2];
        boolean[] verticalRow = new boolean[inRowToWin * 2];
        boolean[] firstDiagonalRow = new boolean[inRowToWin * 2];
        boolean[] secondDiagonalRow = new boolean[inRowToWin * 2];
        int value = gameMap. get(new Pair(xCoord, yCoord));
        for (int i = -inRowToWin; i <= inRowToWin; i++) {
            verticalRow[i + inRowToWin] = gameMap.containsKey(new Pair(xCoord + i, yCoord)) && gameMap.get(new Pair(xCoord + i, yCoord)) == value;
            horizontalRow[i + inRowToWin] = gameMap.containsKey(new Pair(xCoord, yCoord + i)) && gameMap.get(new Pair(xCoord, yCoord + i)) == value;
            firstDiagonalRow[i + inRowToWin] = gameMap.containsKey(new Pair(xCoord - i, yCoord + i )) && gameMap.get(new Pair(xCoord - i, yCoord + i)) == value;
            secondDiagonalRow[i + inRowToWin] = gameMap.containsKey(new Pair(xCoord + i, yCoord + i )) && gameMap.get(new Pair(xCoord + i, yCoord + i)) == value;
        }
        return isEnoughInRow(horizontalRow) || isEnoughInRow(verticalRow) || isEnoughInRow(firstDiagonalRow) || isEnoughInRow(secondDiagonalRow);
    }
}
