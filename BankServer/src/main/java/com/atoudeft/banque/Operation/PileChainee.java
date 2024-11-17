package com.atoudeft.banque.Operation;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe PileChainee represente une pile chainee d'operation banacaire
 * Elle permet d'empiler et de depiler les operations bancaire (LIFO)
 * @author Melissa Sehad
 */
public class PileChainee implements Serializable {
    private Noeud elementSommet;

    /**
     * Fait par Melissa Sehad
     * Constructeur par défaut de la classe PileChainee
     */
    public PileChainee() {
        this.elementSommet = null;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui empile une nouvelle operation au dessus de la pile chainee
     * @param operation operation bancaire a empiler
     */
    public void empiler(Operation operation) {
        Noeud suivant = new Noeud(operation);
        suivant.setNoeudSuivant(elementSommet);
        elementSommet = suivant;
    }

    /**
     * Fait par Melissa Sehad et Mathis Odjo'o Ada
     * Retourne sous forme de liste l'historique des operations du client
     * @return liste des opérations de la pile
     *         liste vide, si la pile est vide
     */
    public ArrayList<Operation> getHistoriqueOperation(){
        ArrayList<Operation> historiqueOperation = new ArrayList<>();
        while(elementSommet!=null){
            historiqueOperation.add(elementSommet.getOperation());
            elementSommet= elementSommet.getNoeudSuivant();
        }
        return historiqueOperation;
    }
}