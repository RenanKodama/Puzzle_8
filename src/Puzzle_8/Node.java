package Puzzle_8;

import java.util.ArrayList;

public class Node {

    private Tabuleiro tab;

    private Heuristic heuri;

    private ArrayList<Node> child = new ArrayList<Node>();

    private Node father;

    public Node(Tabuleiro tab, Heuristic h) {
        this.tab = tab;
        this.heuri = h;
    }

    public ArrayList<Node> createChild() throws InstantiationException, IllegalAccessException {
        Node aux1;
        Tabuleiro aux2;

        aux2 = new Tabuleiro(tab);
        if (aux2.moveEspaco(0, -1)) {
            try {
                aux1 = new Node(aux2, heuri.getClass().newInstance());
                aux1.setFather(this);
                child.add(aux1);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        aux2 = new Tabuleiro(tab);
        if (aux2.moveEspaco(-1, 0)) {
            aux1 = new Node(aux2, heuri.getClass().newInstance());
            aux1.setFather(this);
            child.add(aux1);
        }

        aux2 = new Tabuleiro(tab);
        if (aux2.moveEspaco(1, 0)) {
            aux1 = new Node(aux2, heuri.getClass().newInstance());
            aux1.setFather(this);
            child.add(aux1);
        }

        aux2 = new Tabuleiro(tab);
        if (aux2.moveEspaco(0, 1)) {
            aux1 = new Node(aux2, heuri.getClass().newInstance());
            aux1.setFather(this);
            child.add(aux1);
        }

        return child;
    }


    public float getValorHeuristica() {
        if (heuri != null) {
            return heuri.getValorHeuristica();
        } else {
            return 9999.f;
        }
    }
    
    public void determineHeuristica(Tabuleiro goalState, String operation) {
        if (heuri != null) {
            heuri.calculoHeuristica(this, goalState, operation);
        }
    }

    public boolean equalTo(Node pn) {
        return tab.equalTo(pn.getTab());
    }

    public ArrayList<Node> getChild() {
        return child;
    }

    public void setFather(Node pn) {
        this.father = pn;
    }

    public Node getFather() {
        return father;
    }

    public Tabuleiro getTab() {
        return tab;
    }

    public void setTab(Tabuleiro tab) {
        this.tab = tab;
    }

}
