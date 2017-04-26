package Puzzle_8;

import java.util.ArrayList;

public interface Solucao {

    public ArrayList<Tabuleiro> encontrarSolucao(Tabuleiro startState, Tabuleiro goalState, String operacao);

    public void printDados();
}
