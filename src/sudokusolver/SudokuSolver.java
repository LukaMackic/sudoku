package sudokusolver;

/*
Aplikacija kreira rjesivu sudoku puzlu i rjesava je
 */

public class SudokuSolver {

    public static int[][] rjesen = new int[9][9];
    public static int[][] sud = new int[9][9];

    public static void main(String[] args) {

        create(sud);
        System.out.println("nerjesen:\n");
        print(sud);
        System.out.println("rijesen:\n");
        print(rjesen);

        create(sud);
        System.out.println("nerjesen:\n");
        print(sud);
        System.out.println("rijesen:\n");
        print(rjesen);

    }

    public static boolean control = false;
    // ova metoda rjesava sudoku
    public static void solve(int[][] niz) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (niz[i][j] == 0) {
                    for (int b = 1; b <= 10; b++) {

                        if (control) {
                            return;
                        }
                        if (b == 10) {
                            niz[i][j] = 0;
                            return;
                        }

                        if (check(b, niz, i, j)) {

                            niz[i][j] = b;
                            solve(niz);

                        }

                    }
                    if (niz[i][j] == 0) {
                        return;
                    }
                }

            }
        }
        control = true;

    }
    //ova metoda provjerava da li se odredjeni broj moze staviti na odredjenu poziciju
    public static boolean check(int n, int[][] niz, int x, int y) {
        for (int i = 0; i < 9; i++) {
            if (niz[x][i] == n) {
                return false;
            }
            if (niz[i][y] == n) {
                return false;
            }

        }

        int xk = x / 3;
        int yk = y / 3;

        for (int i = xk * 3; i < (xk + 1) * 3; i++) {
            for (int j = yk * 3; j < (yk + 1) * 3; j++) {
                if (niz[i][j] == n) {
                    return false;
                }
            }
        }

        return true;
    }
    // ovde se printa matrica koja predstavlja sudoku 
    public static void print(int[][] niz) {
        for (int i = 0; i < niz.length; i++) {
            if (i % 3 == 0) {
                System.out.print("_____________________________");
                System.out.println("");
            }
            for (int j = 0; j < niz.length; j++) {
                if (j % 3 == 0) {
                    System.out.print("|");
                }
                System.out.print(" " + niz[i][j] + " ");
            }

            System.out.println("");

        }

    }

    public static int counter = 0;
// ovde se prazna matrica popunjava random brojevima na random mjestima
    public static void fill(int[][] niz) {

        int min = 1;
        int max = 9;
        int number = (int) (Math.random() * (max - min + 1) + min);
        min = 0;
        max = 8;
        int posx = (int) (Math.random() * (max - min + 1) + min);
        int posy = (int) (Math.random() * (max - min + 1) + min);
        if (niz[posx][posy] == 0) {
            if (check(number, niz, posx, posy)) {
                niz[posx][posy] = number;
                counter++;
            }
        }

    }
//ovde se kreira matrica koja prestavlja sudoku koristeci metodu fill i provjerava da li je rjesiva i rjesava je
    public static void create(int[][] nizz) {
        int prazan[][]
                = {
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0}
                };

        boolean rjeseno = true;

        while (rjeseno) {
            copy(prazan, nizz);
            counter = 0;

            while (counter < 32) {
                fill(nizz);
            }
            copy(nizz, rjesen);
            control = false;
            solve(rjesen);
            rjeseno = false;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (rjesen[i][j] == 0) {
                        rjeseno = true;
                    }
                }
            }

        }
    }
  // ova metoda kopira matricu u drugu matricu
    public static void copy(int[][] niz, int[][] niz2) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int pom = niz[i][j];
                niz2[i][j] = pom;
            }
        }
    }
}
