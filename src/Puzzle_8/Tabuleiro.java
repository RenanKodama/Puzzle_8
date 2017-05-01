package Puzzle_8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tabuleiro {

    private int[][] tabuleiro;

    private int width;

    private int height;

    private int a;
    private int b;

    public Tabuleiro(String nomeArquivo) {
        loadTab(nomeArquivo);
    }

    public Tabuleiro(int l, int h) {
        width = l;
        height = h;

        tabuleiro = new int[l][h];
        int count = 0;

        for (int j = 0; j < h; j++) {
            for (int i = 0; i < l; i++) {
                tabuleiro[i][j] = count;
                if (count == 0) {
                    a = i;
                    b = j;
                }
                count++;
            }

        }
    }

    public Tabuleiro(Tabuleiro pg) {
        width = pg.getWidth();
        height = pg.getHeight();
        tabuleiro = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tabuleiro[x][y] = pg.getTabuleiro()[x][y];
            }
        }

        a = pg.getX();
        b = pg.getY();
    }

    public void loadTab(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String[] data = new String[width];
            String delimiter = ",", line = null;
            int i = 0, j = 0;

            try {
                line = br.readLine();

                width = line.length() / 2 + 1;
                height = line.length() / 2 + 1;
                tabuleiro = new int[width][height];

                while (line != null) {
                    data = line.split(delimiter);

                    for (i = 0; i < width; i++) {
                        tabuleiro[i][j] = Integer.parseInt(data[i]);
                        if (tabuleiro[i][j] == 0) {
                            a = i;
                            b = j;
                        }
                    }

                    j++;
                    line = br.readLine();
                }
            } catch (IOException e) {
                System.out.println("Unable to read from file " + fileName + ". Closing...");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File with name " + fileName + " not found. Closing...");
            System.exit(0);
        }
    }

    public boolean moveEspaco(int x, int y) {
        boolean sucesso = false;

        if (x != 0) {
            if (x > 0 && a < 2) {
                tabuleiro[a][b] = tabuleiro[a + 1][b];
                tabuleiro[a + 1][b] = 0;

                a = a + 1;
                sucesso = true;
            } else if (x < 0 && a > 0) {
                tabuleiro[a][b] = tabuleiro[a - 1][b];
                tabuleiro[a - 1][b] = 0;

                a = a - 1;
                sucesso = true;
            }
        } else if (y != 0) {
            if (y > 0 && b < 2) {
                tabuleiro[a][b] = tabuleiro[a][b + 1];
                tabuleiro[a][b + 1] = 0;

                b = b + 1;
                sucesso = true;
            } else if (y < 0 && b > 0) {
                tabuleiro[a][b] = tabuleiro[a][b - 1];
                tabuleiro[a][b - 1] = 0;

                b = b - 1;
                sucesso = true;
            }
        }

        return sucesso;
    }

    public void printTabChild() {
        for (int y = 0; y < height; y++) {
            System.out.print("\t");
            for (int x = 0; x < width; x++) {
                if (x == a && y == b) {
                    System.out.print("  ");
                } else {
                    System.out.print(tabuleiro[x][y] + " ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
    
    public void printTab() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x == a && y == b) {
                    System.out.print("  ");
                } else {
                    System.out.print(tabuleiro[x][y] + " ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }



    public int[] getPositionTab(int tile) {
        int[] posicao = new int[2];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (tabuleiro[x][y] == tile) {
                    posicao[0] = x;
                    posicao[1] = y;

                }
            }
        }

        return posicao;
    }
    
    public boolean equalTo(Tabuleiro pg) {
        boolean equal = true;

        for (int y = 0; y < height && equal == true; y++) {
            for (int x = 0; x < width && equal == true; x++) {
                if (tabuleiro[x][y] != pg.getTabuleiro()[x][y]) {
                    equal = false;
                }
            }
        }

        return equal;
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return a;
    }

    public int getY() {
        return b;
    }

}
