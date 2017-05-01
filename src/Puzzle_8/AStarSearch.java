package Puzzle_8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AStarSearch implements Solucao {

    private ArrayList<Node> visited = new ArrayList<Node>();
    private LinkedList<Node> openStates = new LinkedList<Node>();
    private ArrayList<Tabuleiro> solution = new ArrayList<Tabuleiro>();
    private long runTime = 0;
    private long startTime, endTime;

    public AStarSearch() {
    }

    public ArrayList<Tabuleiro> encontrarSolucao(Tabuleiro startState, Tabuleiro goalState, String operacao) {
        ArrayList<Node> child = new ArrayList<Node>();

        solution = new ArrayList<Tabuleiro>();
        Node currentState = null;

        boolean solutionFound = false, visitedPosition = false;
        int nodeCounter = 0;

        openStates.offer(new Node(startState, new AStarHeuristic()));

        startTime = System.nanoTime();

        System.out.println("Caminhos Possíveis");

        while (!openStates.isEmpty() && solutionFound == false) {
            currentState = openStates.poll();
            nodeCounter++;

            System.out.println("Estado = " + nodeCounter);
            currentState.getTab().printTab();

            if (currentState.getTab().equalTo(goalState)) {
                solutionFound = true;
                break;
            }

            try {
                child = currentState.createChild();
            } catch (InstantiationException ex) {
                Logger.getLogger(AStarSearch.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(AStarSearch.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int i = 0; i < child.size(); i++) {
                Node newChild = child.get(i);
                visitedPosition = false;

                for (Node newNode : visited) {
                    if (newChild.equalTo(newNode)) {
                        System.out.println("\t Closed \n");
                        visitedPosition = true;
                        break;
                    }
                }

                for (Node newNode : openStates) {
                    if (newChild.equalTo(newNode)) {
                        System.out.println("\t Opened \n");
                        visitedPosition = true;
                        break;
                    }
                }

                if (visitedPosition != true) {
                    boolean inserted = false;

                    newChild.determineHeuristica(goalState, operacao);

                    for (int j = 0; j < openStates.size(); j++) {
                        if (newChild.getValorHeuristica() < openStates.get(j).getValorHeuristica()) {
                            openStates.add(j, newChild);
                            inserted = true;
                            break;
                        }
                    }

                    if (inserted == false) {
                        openStates.offer(newChild);
                    }

                    System.out.println("\tcustoFilho = " + newChild.getValorHeuristica());
                    newChild.getTab().printTabChild();

                }
            }

            visited.add(currentState);
            openStates.remove(currentState);
        }

        endTime = System.nanoTime();
        runTime = (endTime - startTime) / 1000000;

        if (solutionFound == true) {
            boolean caminhoEncontrado = false;

            solution.add(currentState.getTab());
            while (caminhoEncontrado == false) {
                currentState = currentState.getFather();
                solution.add(0, currentState.getTab());
                if (currentState.getTab().equalTo(startState)) {
                    caminhoEncontrado = true;
                }
            }
        } else {
            return null;
        }

        return solution;
    }

    public void printDados() {
        System.out.println(" Análise");
        System.out.println(" Tempo: " + runTime + " ms");
        System.out.println(" Nodes Searched: " + visited.size());
        System.out.println(" Solution Length: " + solution.size());
        System.out.println(" ======================= ");
    }

}
