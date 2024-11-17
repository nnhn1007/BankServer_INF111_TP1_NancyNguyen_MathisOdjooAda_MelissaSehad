package com.atoudeft.banque.Operation;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe PileChainee est une classe qui represente une pile chainee d'operation banacaire
 * Elle permet d'empiler et de depiler les operations bancaire (LIFO)
 */
public class PileChainee implements Serializable {
    private Noeud elementSommet;

    public PileChainee() {
        this.elementSommet = null;
    }
    /**
     * Fait par Melissa Sehad
     * Methode qui empile une nouvelle operation au sommet de la pile chainee
     *
     * @param operation L'operation banncaire a empiler
     */
    public void empiler(Operation operation) {
        Noeud suivant = new Noeud(operation); //PS : Valeur temporaire (TEMP)
        suivant.setNoeudSuivant(elementSommet);
        elementSommet = suivant;
    }
    public ArrayList<Operation> getHistoriqueOperation(){
        ArrayList<Operation> historiqueOperation = new ArrayList<>();
        while(elementSommet!=null){
            historiqueOperation.add(elementSommet.getOperation());
            elementSommet= elementSommet.getNoeudSuivant();
        }
        return historiqueOperation;
    }
}