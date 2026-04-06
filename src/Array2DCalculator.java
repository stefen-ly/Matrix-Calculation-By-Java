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
                case 'D' -> operations3D();
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

    static int[][] readMatrix(String label, int row, int col) {
        int[][] m = new int[row][col];
        System.out.println("\n---[Matrix " + label + " (" + row + "x" + col + ")] ---");
        int count = 1;
        for (int i = 0; i < row; i++) {
            count = i + 1;
            System.out.println("=> Row: " + count );
            for (int j = 0; j < col; j++) {
                System.out.print(label + "[" + i + "][" + j + "]: ");
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
        System.out.println("---------[ Create Matrix ]---------");
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
                ==========[ MATRIX CALCULATOR ]==========
                [O]. Operation Matrix 2D (+, -, x, /)
                [T]. Transpose Matrix 2D
                [D]. Operation Matrix 3D (+, -)
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

    static void algebraMenu3D() {
        System.out.println();
        System.out.print("""
                ------[ Arithmetic ]------
                [+]. Add Matrix
                [-]. Sub Matrix
                [B]. Back
                --------------------------Œ-
                """);
        System.out.print("=> Option: ");
    }

    static int[][][] read3DMatrix(String label, int d, int r, int c) {
        int[][][] m = new int[d][r][c];

        System.out.printf("\n-----[ Matrix %s ]-----", label);
        for (int i = 0; i < d; i++) {
            System.out.println("\nPage " + (i + 1));
            for (int j = 0; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    System.out.printf("%s[%d][%d][%d]: ", label, i, j, k);
                    m[i][j][k] = input.nextInt();
                }
            }
        }
        return m;
    }

    static void operations3D() {
        System.out.println("\n---[ Create 3D Matrix ]---");
        System.out.print("Enter first Dms (Layers): ");
        int f = input.nextInt();
        System.out.print("Enter second Dms (Rows): ");
        int s = input.nextInt();
        System.out.print("Enter third Dms (Cols): ");
        int th = input.nextInt();

        int[][][] a = read3DMatrix("A", f, s, th);
        int[][][] b = read3DMatrix("B", f, s, th);

        display3D("Matrix A", a);
        display3D("Matrix B", b);

        labelOp:
        while (true) {
            algebraMenu3D();
            char op = input.next().toUpperCase().charAt(0);

            switch (op) {
                case '+' -> {
                    int[][][] sum = add3D(a, b);
                    display3D("Result (A + B)", sum);
                }
                case '-' -> {
                    int[][][] sub = subtract3D(a, b);
                    display3D("Result (A - B)", sub);
                }
                case 'B' -> {
                    System.out.println("Returning to Main Menu...");
                    break labelOp;
                }
                default -> System.out.println("Invalid Operation!");
            }
        }
    }

    static void display3D(String label, int[][][] m) {
        System.out.println("\n--- " + label + " ---");
        for (int i = 0; i < m.length; i++) {
            System.out.println("Layer " + (i + 1) + ":");
            for (int[] row : m[i]) {
                System.out.print("| ");
                for (int val : row) System.out.print(val + " ");
                System.out.println("|");
            }
        }
    }

   
    static int[][][] add3D(int[][][] a, int[][][] b) {
        int first = a.length;
        int second = a[0].length;
        int third = a[0][0].length;
        int[][][] result = new int[first][second][third];

        for (int i = 0; i < first; i++) {
            for (int j = 0; j < second; j++) {
                for (int k = 0; k < third; k++) {
                    result[i][j][k] = a[i][j][k] + b[i][j][k];
                }
            }
        }
        return result;
    }

   
    static int[][][] subtract3D(int[][][] a, int[][][] b) {
        int first = a.length;
        int second = a[0].length;
        int third = a[0][0].length;
        int[][][] result = new int[first][second][third];

        for (int i = 0; i < first; i++) {
            for (int j = 0; j < second; j++) {
                for (int k = 0; k < third; k++) {
                    result[i][j][k] = a[i][j][k] - b[i][j][k];
                }
            }
        }
        return result;
    }
}