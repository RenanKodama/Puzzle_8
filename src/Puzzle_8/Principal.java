package Puzzle_8;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    private Solucao search;
    private static Scanner ler;
    public static String operacao;

    public static void main(String[] args) {
        Principal solucao = new Principal();
        ler = new Scanner(System.in);

        String arquivo_entrada;
        String arquivo_saida;
        
        System.out.println("Opt: teste1.txt,teste2.txt,teste3.txt");
        System.out.println("Arquivo de Entrada: ");
        arquivo_entrada = ler.nextLine();

        arquivo_saida = "objetivo.txt";

        boolean op = true;
        while (op) {
            System.out.println("ManHattan[M-m]|Camberra[C-c]");
            operacao = ler.nextLine();
        
            switch (operacao) {
                case "M":
                case "m":
                    operacao = "m";
                    op = false;
                    break;

                case "C":
                case "c":
                    operacao = "m";
                    op = false;
                    break;

                default:
                    System.out.println("Operação Inválida, deve ser: ");
                    break;

            }
        }
        solucao.Start(arquivo_entrada, arquivo_saida);
    }

    public Principal() {
    }

    public void Start(String nomeArquivoInicio, String nomeArquivoObjetivo) {
        ArrayList<Tabuleiro> resposta = new ArrayList<Tabuleiro>();

        Tabuleiro inicioTab = new Tabuleiro(nomeArquivoInicio);

        Tabuleiro objetivoTab = new Tabuleiro(nomeArquivoObjetivo);

        System.out.println("Configuração Inicial");
        inicioTab.printTab();

        System.out.println("Configuração Final");
        objetivoTab.printTab();

        search = new AStarSearch();

        if (search != null) {
            resposta = search.encontrarSolucao(inicioTab, objetivoTab, operacao);

            if (resposta != null) {
                System.out.println("Caminho Tomado");
                for (int i = 0; i < resposta.size(); i++) {
                    resposta.get(i).printTab();
                }
            } else {
                System.out.println("Sem solução!");
            }

            search.printDados();
            System.out.println("     Inicio: " + nomeArquivoInicio);
            System.out.println("       Final: " + nomeArquivoObjetivo);
        }
    }

}
