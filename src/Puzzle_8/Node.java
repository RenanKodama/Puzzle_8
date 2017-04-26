package Puzzle_8;

import java.util.ArrayList;

public class Node {

    private Tabuleiro tab;

    private Heuristic heuristica;

    private ArrayList<Node> filho = new ArrayList<Node>();

    private Node pai;

    public Node(Tabuleiro tab, Heuristic h) {
        this.tab = tab;
        this.heuristica = h;
    }

    public ArrayList<Node> createFilho() throws InstantiationException, IllegalAccessException {
        Node pn;
        Tabuleiro pg;

        pg = new Tabuleiro(tab);
        if (pg.moveEspaco(0, -1)) {
            try {
                pn = new Node(pg, heuristica.getClass().newInstance());
                pn.setPai(this);
                filho.add(pn);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        pg = new Tabuleiro(tab);
        if (pg.moveEspaco(-1, 0)) {
            pn = new Node(pg, heuristica.getClass().newInstance());
            pn.setPai(this);
            filho.add(pn);
        }

        pg = new Tabuleiro(tab);
        if (pg.moveEspaco(1, 0)) {
            pn = new Node(pg, heuristica.getClass().newInstance());
            pn.setPai(this);
            filho.add(pn);
        }

        pg = new Tabuleiro(tab);
        if (pg.moveEspaco(0, 1)) {
            pn = new Node(pg, heuristica.getClass().newInstance());
            pn.setPai(this);
            filho.add(pn);
        }

        return filho;
    }

    public void determineHeuristica(Tabuleiro goalState, String operacao) {
        if (heuristica != null) {
            heuristica.calculoHeuristica(this, goalState, operacao);
        }
    }

    public float getValorHeuristica() {
        if (heuristica != null) {
            return heuristica.getValorHeuristica();
        } else {
            return 9999.f;
        }
    }

    public boolean equalTo(Node pn) {
        return tab.equalTo(pn.getTab());
    }

    public ArrayList<Node> getFilho() {
        return filho;
    }

    public void setPai(Node pn) {
        this.pai = pn;
    }

    public Node getPai() {
        return pai;
    }

    public Tabuleiro getTab() {
        return tab;
    }

    public void setTab(Tabuleiro tab) {
        this.tab = tab;
    }

}
