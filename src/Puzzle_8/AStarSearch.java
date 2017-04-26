package Puzzle_8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AStarSearch implements Solucao {

    private ArrayList<Node> statusVisitado = new ArrayList<Node>();
    private LinkedList<Node> openStates = new LinkedList<Node>();
    private ArrayList<Tabuleiro> solucao = new ArrayList<Tabuleiro>();
    private long runTime = 0;
    private long startTime, endTime;

    public AStarSearch() {
    }

    public ArrayList<Tabuleiro> encontrarSolucao(Tabuleiro startState, Tabuleiro goalState, String operacao) {
        ArrayList<Node> filho = new ArrayList<Node>();

        solucao = new ArrayList<Tabuleiro>();
        Node currentState = null;

        boolean solucaoEncontrada = false, posicaoVisitada = false;
        int nodeCounter = 0;

        openStates.offer(new Node(startState, new AStarHeuristic()));

        startTime = System.nanoTime();

        System.out.println("Caminhos Possíveis");

        while (!openStates.isEmpty() && solucaoEncontrada == false) {
            currentState = openStates.poll();
            nodeCounter++;

            System.out.println("Estado = " + nodeCounter);
            currentState.getTab().printTab();

            if (currentState.getTab().equalTo(goalState)) {
                solucaoEncontrada = true;
                break;
            }

            try {
                filho = currentState.createFilho();
            } catch (InstantiationException ex) {
                Logger.getLogger(AStarSearch.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(AStarSearch.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int i = 0; i < filho.size(); i++) {
                Node newFilho = filho.get(i);
                posicaoVisitada = false;

                for (Node newNode : statusVisitado) {
                    if (newFilho.equalTo(newNode)) {
                        System.out.println("\t Closed \n");
                        posicaoVisitada = true;
                        break;
                    }
                }

                for (Node newNode : openStates) {
                    if (newFilho.equalTo(newNode)) {
                        System.out.println("\t Opened \n");
                        posicaoVisitada = true;
                        break;
                    }
                }

                if (posicaoVisitada != true) {
                    boolean inserted = false;

                    newFilho.determineHeuristica(goalState, operacao);

                    for (int j = 0; j < openStates.size(); j++) {
                        if (newFilho.getValorHeuristica() < openStates.get(j).getValorHeuristica()) {
                            openStates.add(j, newFilho);
                            inserted = true;
                            break;
                        }
                    }

                    if (inserted == false) {
                        openStates.offer(newFilho);
                    }

                    System.out.println("\tcustoFilho = " + newFilho.getValorHeuristica());
                    newFilho.getTab().printTabFilho();

                }
            }

            statusVisitado.add(currentState);
            openStates.remove(currentState);
        }

        endTime = System.nanoTime();
        runTime = (endTime - startTime) / 1000000;

        if (solucaoEncontrada == true) {
            boolean caminhoEncontrado = false;

            solucao.add(currentState.getTab());
            while (caminhoEncontrado == false) {
                currentState = currentState.getPai();
                solucao.add(0, currentState.getTab());
                if (currentState.getTab().equalTo(startState)) {
                    caminhoEncontrado = true;
                }
            }
        } else {
            return null;
        }

        return solucao;
    }

    public void printDados() {
        System.out.println(" Análise");
        System.out.println(" Tempo: " + runTime + " ms");
        System.out.println(" Nodes Searched: " + statusVisitado.size());
        System.out.println(" Solution Length: " + solucao.size());
        System.out.println(" ======================= ");
    }

}
