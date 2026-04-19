import java.util.Scanner;

public class SudokuGame {

    private final int[][] board = new int[9][9];

    public static void main(String[] args) {
        SudokuGame game = new SudokuGame();
        game.start();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        int[][] initial = {
                {5,3,0,0,7,0,0,0,0},
                {6,0,0,1,9,5,0,0,0},
                {0,9,8,0,0,0,0,6,0},
                {8,0,0,0,6,0,0,0,3},
                {4,0,0,8,0,3,0,0,1},
                {7,0,0,0,2,0,0,0,6},
                {0,6,0,0,0,0,2,8,0},
                {0,0,0,4,1,9,0,0,5},
                {0,0,0,0,8,0,0,7,9}
        };

        copyBoard(initial);

        while (true) {
            printBoard();

            System.out.println("\nDigite linha, coluna e valor (ex: 1 3 9) ou -1 para sair:");
            int row = sc.nextInt();
            if (row == -1) break;

            int col = sc.nextInt();
            int value = sc.nextInt();

            row--;
            col--;

            if (isValidMove(row, col, value)) {
                board[row][col] = value;
            } else {
                System.out.println("Jogada inválida!");
            }

            if (isComplete()) {
                printBoard();
                System.out.println("Você venceu o Sudoku!");
                break;
            }
        }

        sc.close();
    }

    private void copyBoard(int[][] initial) {
        for (int i = 0; i < 9; i++) {
            System.arraycopy(initial[i], 0, board[i], 0, 9);
        }
    }

    private void printBoard() {
        System.out.println("\n   1 2 3   4 5 6   7 8 9");

        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) System.out.println("  -----------------------");

            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) System.out.print("| ");

                System.out.print(board[i][j] == 0 ? ". " : board[i][j] + " ");
            }
            System.out.println("|");
        }

        System.out.println("  -----------------------");
    }

    private boolean isValidMove(int row, int col, int value) {
        if (board[row][col] != 0) return false;

        for (int i = 0; i < 9; i++) {
            if (board[row][i] == value) return false;
            if (board[i][col] == value) return false;
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == value) return false;
            }
        }

        return true;
    }

    private boolean isComplete() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) return false;
            }
        }
        return true;
    }
}
