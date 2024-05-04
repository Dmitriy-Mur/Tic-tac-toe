import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int height;
        int width;
        int inRowToWin;
        do {
            System.out.print("Enter height of game board\n>> ");
            height = scanner.nextInt();
        } while (height < 3 || height > 99);
        do {
            System.out.print("Enter width of game board\n>> ");
            width = scanner.nextInt();
        } while (width < 3 || width > 26);
        do {
            System.out.print("Enter a number in a row to win\n>> ");
            inRowToWin = scanner.nextInt();
        } while (inRowToWin  < 3 || inRowToWin  >  Math.min(height, width));
        GameGrid gameGrid = new GameGrid(height, width, inRowToWin);
        gameGrid.print();
        int stepCount = 0;
        boolean isGameOver = false;
        int maxStepCount = height * width;
        do {
            do {
                if (stepCount % 2 == 0) {
                    System.out.print("First player step\n>> ");
                } else {
                    System.out.print("Second player step\n>> ");
                }

                height = scanner.nextInt() - 1;
                width = scanner.nextInt() - 1;
            } while (!gameGrid.tryPut(height, width, (stepCount % 2) == 0));

            gameGrid.print();
            stepCount++;
        } while (!gameGrid.isGameOver(height, width) && stepCount != maxStepCount);
        System.out.print("Game over\n");
        if (gameGrid.isGameOver(height, width)) {
            if (stepCount % 2 != 0) {
                System.out.print("First player won");
            } else {
                System.out.print("Second player won");
            }
        } else {
            System.out.print("It is tie");
        }
    }
}
