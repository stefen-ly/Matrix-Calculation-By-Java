import java.util.Scanner;

public class Array2DCalculator{
    static Scanner input = new Scanner(System.in);

    static void mainOperation(){
        label:
        while (true) {
            mainMenu();
            char choice = input.next().toUpperCase().charAt(0);

            switch (choice) {
                case 'O' -> operations();
                case 'T' -> transpose();
                case 'E' -> {
                    System.out.println("Exiting Program... Goodbye!");
                    break label;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void operations() {
        System.out.println("\n-----------[ Create Matrix ]----------");
        System.out.print("Enter rows: ");
        int row = input.nextInt();
        System.out.print("Enter cols: ");
        int col = input.nextInt();

        int[][] a = readMatrix("A", row, col);
        int[][] b = readMatrix("B", row, col);

        display("Matrix A", a);
        display("Matrix B", b);

        labelOp:
        while (true) {
            algebraMenu();
            char op = input.next().toUpperCase().charAt(0);

            switch (op) {
                case '+' -> display("Result (A + B)", add(a, b));
                case '-' -> display("Result (A - B)", subtract(a, b));
                case 'X' -> {
                    /*
                     * check row in matrix A = column in matrix B
                     *  matrix^2
                     *  r = c
                     *  n * m
                     */
                    if (row == col) {
                        display("Result (A x B)", multiply(a, b));
                    } else {
                        System.out.println("Error: Multiplication is requires Square Matrices");
                    }
                }
                case '/' -> display("Result (A / B)", divide(a, b));
                case 'B' -> {
                    System.out.println("Returning to Main Menu...");
                    break labelOp;
                }
                default -> System.out.println("Invalid Operation!");
            }
        }
    }

    static int[][] readMatrix(String name, int row, int col) {
        int[][] m = new int[row][col];
        System.out.println("\n---[Matrix " + name + " (" + row + "x" + col + ")] ---");
        int count = 1;
        for (int i = 0; i < row; i++) {
            count = i + 1;
            System.out.println("=> Row: " + count );
            for (int j = 0; j < col; j++) {
                System.out.print(name + "[" + i + "][" + j + "]: ");
                m[i][j] = input.nextInt();
            }
        }
        return m;
    }

    static void display(String label, int[][] matrix) {
        System.out.println("\n" + label + ":");
        for (int[] row : matrix) {
            System.out.print("|  ");
            for (int val : row) {
                System.out.print(val + "  ");
            }
            System.out.println("|");
        }
    }

    static int[][] add(int[][] a, int[][] b) {
        int row = a.length, col = b.length;
        int[][] result = new int[row][col];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) result[i][j] = a[i][j] + b[i][j];
        return result;
    }

    static int[][] subtract(int[][] a, int[][] b) {
        int row = a.length, col = b.length;
        int[][] result = new int[row][col];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) result[i][j] = a[i][j] - b[i][j];
        return result;
    }

    static int[][] multiply(int[][] a, int[][] b) {
        int n = a.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++) result[i][j] += a[i][k] * b[k][j];
        return result;
    }

    static int[][] divide(int[][] a, int[][] b) {
        int row = a.length, col = b.length;
        int[][] result = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = (b[i][j] != 0) ? a[i][j] / b[i][j] : 0;
            }
        }
        return result;
    }

    static void transpose() {
        System.out.print("Enter rows: ");
        int row = input.nextInt();
        System.out.print("Enter cols: ");
        int col = input.nextInt();
        int[][] a = readMatrix("A", row, col);

        int[][] result = new int[col][row];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) result[j][i] = a[i][j];

        display("Original", a);
        display("Transposed", result);
    }

    static void mainMenu() {
        System.out.println();
        System.out.print("""
                ==========[ CALCULATOR ]==========
                [O]. Operation (+, -, x, /)
                [T]. Transpose Matrix
                [E]. Exit
                ----------------------------------
                """);
        System.out.print("=> Select: ");
    }

    static void algebraMenu() {
        System.out.println();
        System.out.print("""
                ------[ Arithmetic ]------
                [+]. Add Matrix
                [-]. Sub Matrix
                [X]. Mul Matrix
                [/]. Div Matrix
                [B]. Back
                ---------------------------
                """);
        System.out.print("=> Option: ");
    }
}