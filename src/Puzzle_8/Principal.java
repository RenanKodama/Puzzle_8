package Puzzle_8;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    private Solucao search;
    private static Scanner read;
    public static String operation;

    public static void main(String[] args) {
        Principal solution = new Principal();
        read = new Scanner(System.in);

        String file_input;
        String file_output;
        
        System.out.println("Opt: teste1.txt,teste2.txt,teste3.txt");
        System.out.println("Arquivo de Entrada: ");
        file_input = read.nextLine();

        file_output = "objetivo.txt";

        boolean op = true;
        while (op) {
            System.out.println("ManHattan[M-m]|Camberra[C-c]");
            operation = read.nextLine();
        
            switch (operation) {
                case "M":
                case "m":
                    operation = "m";
                    op = false;
                    break;

                case "C":
                case "c":
                    operation = "m";
                    op = false;
                    break;

                default:
                    System.out.println("Operação Inválida, deve ser: ");
                    break;

            }
        }
        solution.Start(file_input, file_output);
    }

    public Principal() {
    }

    public void Start(String fileNameInitial, String fileNameGoal) {
        ArrayList<Tabuleiro> response = new ArrayList<Tabuleiro>();

        Tabuleiro initTab = new Tabuleiro(fileNameInitial);

        Tabuleiro goalTab = new Tabuleiro(fileNameGoal);

        System.out.println("Configuração Inicial");
        initTab.printTab();

        System.out.println("Configuração Final");
        goalTab.printTab();

        search = new AStarSearch();

        if (search != null) {
            response = search.encontrarSolucao(initTab, goalTab, operation);

            if (response != null) {
                System.out.println("Caminho Tomado");
                for (int i = 0; i < response.size(); i++) {
                    response.get(i).printTab();
                }
            } else {
                System.out.println("Sem solução!");
            }

            search.printDados();
            System.out.println("     Inicio: " + fileNameInitial);
            System.out.println("       Final: " + fileNameGoal);
        }
    }

}
