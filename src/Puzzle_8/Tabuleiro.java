package Puzzle_8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tabuleiro {

    private int[][] tab;

    private int largura;

    private int altura;

    private int x0;
    private int y0;

    public Tabuleiro(String nomeArquivo) {
        carregarTab(nomeArquivo);
    }

    public Tabuleiro(int l, int h) {
        largura = l;
        altura = h;

        tab = new int[l][h];
        int count = 0;

        for (int j = 0; j < h; j++) {
            for (int i = 0; i < l; i++) {
                tab[i][j] = count;
                if (count == 0) {
                    x0 = i;
                    y0 = j;
                }
                count++;
            }

        }
    }

    public Tabuleiro(Tabuleiro pg) {
        largura = pg.getLargura();
        altura = pg.getAltura();
        tab = new int[largura][altura];

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                tab[x][y] = pg.getTab()[x][y];
            }
        }

        x0 = pg.getX();
        y0 = pg.getY();
    }

    public void carregarTab(String nomeArquivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
            String[] data = new String[largura];
            String delimiter = ",", line = null;
            int i = 0, j = 0;

            try {
                line = br.readLine();

                largura = line.length() / 2 + 1;
                altura = line.length() / 2 + 1;
                tab = new int[largura][altura];

                while (line != null) {
                    data = line.split(delimiter);

                    for (i = 0; i < largura; i++) {
                        tab[i][j] = Integer.parseInt(data[i]);
                        if (tab[i][j] == 0) {
                            x0 = i;
                            y0 = j;
                        }
                    }

                    j++;
                    line = br.readLine();
                }
            } catch (IOException e) {
                System.out.println("Unable to read from file " + nomeArquivo + ". Closing...");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File with name " + nomeArquivo + " not found. Closing...");
            System.exit(0);
        }
    }

    public boolean moveEspaco(int x, int y) {
        boolean sucesso = false;

        if (x != 0) {
            if (x > 0 && x0 < 2) {
                tab[x0][y0] = tab[x0 + 1][y0];
                tab[x0 + 1][y0] = 0;

                x0 = x0 + 1;
                sucesso = true;
            } else if (x < 0 && x0 > 0) {
                tab[x0][y0] = tab[x0 - 1][y0];
                tab[x0 - 1][y0] = 0;

                x0 = x0 - 1;
                sucesso = true;
            }
        } else if (y != 0) {
            if (y > 0 && y0 < 2) {
                tab[x0][y0] = tab[x0][y0 + 1];
                tab[x0][y0 + 1] = 0;

                y0 = y0 + 1;
                sucesso = true;
            } else if (y < 0 && y0 > 0) {
                tab[x0][y0] = tab[x0][y0 - 1];
                tab[x0][y0 - 1] = 0;

                y0 = y0 - 1;
                sucesso = true;
            }
        }

        return sucesso;
    }

    public void printTab() {
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                if (x == x0 && y == y0) {
                    System.out.print("  ");
                } else {
                    System.out.print(tab[x][y] + " ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public void printTabFilho() {
        for (int y = 0; y < altura; y++) {
            System.out.print("\t");
            for (int x = 0; x < largura; x++) {
                if (x == x0 && y == y0) {
                    System.out.print("  ");
                } else {
                    System.out.print(tab[x][y] + " ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public boolean equalTo(Tabuleiro pg) {
        boolean equal = true;

        for (int y = 0; y < altura && equal == true; y++) {
            for (int x = 0; x < largura && equal == true; x++) {
                if (tab[x][y] != pg.getTab()[x][y]) {
                    equal = false;
                }
            }
        }

        return equal;
    }

    public int[] getPosicaoTab(int tile) {
        int[] posicao = new int[2];

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                if (tab[x][y] == tile) {
                    posicao[0] = x;
                    posicao[1] = y;

                }
            }
        }

        return posicao;
    }

    public int[][] getTab() {
        return tab;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public int getX() {
        return x0;
    }

    public int getY() {
        return y0;
    }

}
