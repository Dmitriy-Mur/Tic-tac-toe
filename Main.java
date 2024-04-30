import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter height of game board\n>> ");
        int height = scanner.nextInt();
        System.out.print("Enter width of game board\n>> ");
        int width = scanner.nextInt();
        System.out.print("Enter a number in a row to win\n>> ");
        int inRowToWin = scanner.nextInt();
        GameGrid gameGrid = new GameGrid(height, width, inRowToWin);

        gameGrid.print();
        int stepCount = 0;
        boolean isGameOver = false;
        int maxStepCount = height * width;
        while (!isGameOver && stepCount != maxStepCount) {
            //System.out.print("Step " + stepCount + " / " + maxStepCount + '\n');
            if (stepCount % 2 == 0) {
                System.out.print("First player step\n>> ");
            } else {
                System.out.print("Second player step\n>> ");
            }
            height = scanner.nextInt() - 1;
            width = scanner.nextInt() - 1;
            gameGrid.tryPut(height, width, stepCount % 2);
            isGameOver = gameGrid.isGameOver(height, width);
            gameGrid.print();
            stepCount++;
        }
        System.out.print("Game over\n");
        if (isGameOver) {
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


//1 A
//menu
//09
//width < 27, > 0;
//height < 100, > 0
