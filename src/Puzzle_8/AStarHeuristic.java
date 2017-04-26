package Puzzle_8;

public class AStarHeuristic implements Heuristic {

    private float heuristic = 0.0f;

    public AStarHeuristic() {
    }

    public void calculoHeuristica(Node currentState, Tabuleiro goalState, String operacao) {
        Tabuleiro tabAtual = currentState.getTab();
        int tam = goalState.getLargura() * goalState.getAltura();
        int[] posicaoAtual = new int[2];
        int[] destino = new int[2];
        int distancia;
        int distanciaTotal = 0;

        for (int i = 0; i < tam; i++) {
            posicaoAtual = tabAtual.getPosicaoTab(i);
            destino = goalState.getPosicaoTab(i);
            distancia = Math.abs(posicaoAtual[0] - destino[0])
                    + Math.abs(posicaoAtual[1] - destino[1]);
            distanciaTotal += distancia;
        }

        switch (operacao) {
            case ("M"):
            case ("m"):
                /*Distancia de Manhattan*/
                for (int i = 0; i < tam; i++) {
                    posicaoAtual = tabAtual.getPosicaoTab(i);
                    destino = goalState.getPosicaoTab(i);
                    distancia = Math.abs(posicaoAtual[0] - destino[0]) + Math.abs(posicaoAtual[1] - destino[1]);
                    distanciaTotal += distancia;
                }
                break;

            case ("C"):
            case ("c"):
                /*Distancia de Camberra*/
                for (int i = 0; i < tam; i++) {
                    posicaoAtual = tabAtual.getPosicaoTab(i);
                    destino = goalState.getPosicaoTab(i);
                    distancia = Math.abs(posicaoAtual[0] - destino[0]) / (Math.abs(posicaoAtual[1]) + Math.abs(destino[1]) + 1);
                    distanciaTotal += distancia;
                }
                break;

            default:
                /*ERRO*/
                System.out.println("ERRO!");
                break;

        }

        int custoTotal = 0;
        Node pai = currentState;

        while (pai != null) {
            pai = pai.getPai();
            custoTotal++;
        }

        heuristic = distanciaTotal + custoTotal;
    }

    public float getValorHeuristica() {
        return heuristic;
    }
}