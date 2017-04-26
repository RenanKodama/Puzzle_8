package Puzzle_8;

public interface Heuristic {

    public void calculoHeuristica(Node currentState, Tabuleiro goalState, String operacao);

    public float getValorHeuristica();

}
