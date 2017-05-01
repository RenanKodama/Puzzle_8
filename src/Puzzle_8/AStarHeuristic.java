package Puzzle_8;

public class AStarHeuristic implements Heuristic {

    private float heuristic = 0.0f;

    public AStarHeuristic() {
    }

    public void calculoHeuristica(Node currentState, Tabuleiro goalState, String operation) {
        Tabuleiro currentTab = currentState.getTab();
        int tam = goalState.getWidth() * goalState.getHeight();
        int[] currentPosition = new int[2];
        int[] destiny = new int[2];
        int dist;
        int distTotal = 0;

        for (int i = 0; i < tam; i++) {
            currentPosition = currentTab.getPositionTab(i);
            destiny = goalState.getPositionTab(i);
            dist = Math.abs(currentPosition[0] - destiny[0])
                    + Math.abs(currentPosition[1] - destiny[1]);
            distTotal += dist;
        }

        switch (operation) {
            case ("M"):
            case ("m"):
                /*Distancia de Manhattan*/
                for (int i = 0; i < tam; i++) {
                    currentPosition = currentTab.getPositionTab(i);
                    destiny = goalState.getPositionTab(i);
                    dist = Math.abs(currentPosition[0] - destiny[0]) + Math.abs(currentPosition[1] - destiny[1]);
                    distTotal += dist;
                }
                break;

            case ("C"):
            case ("c"):
                /*Distancia de Camberra*/
                for (int i = 0; i < tam; i++) {
                    currentPosition = currentTab.getPositionTab(i);
                    destiny = goalState.getPositionTab(i);
                    dist = Math.abs(currentPosition[0] - destiny[0]) / (Math.abs(currentPosition[1]) + Math.abs(destiny[1]) + 1);
                    distTotal += dist;
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
            pai = pai.getFather();
            custoTotal++;
        }

        heuristic = distTotal + custoTotal;
    }

    public float getValorHeuristica() {
        return heuristic;
    }
}