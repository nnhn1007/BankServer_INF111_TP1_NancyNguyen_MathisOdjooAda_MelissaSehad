package com.atoudeft.banque.Operation;

import java.io.Serializable;

/**
 * La classe PileChainee est une classe qui represente une pile chainee d'operation banacaire
 * Elle permet d'empiler et de depiler les operations bancaire (LIFO)
 */
public class PileChainee implements Serializable {
    private Noeud elementSommet;

    /**
     * Fait par Melissa Sehad
     * Methode qui empile une nouvelle operation au sommet de la pile chainee
     *
     * @param operation L'operation banncaire a empiler
     */
    public void empiler(Operation operation) {
        elementSommet = new Noeud(operation, elementSommet);
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne l'operation depiler au sommet de la pile chainee
     *
     * @return l'operation qui est depile
     */
    public Operation depiler() throws Exception {
        if (pileVide()) {
            return null;
        }
        Operation operation = elementSommet.getOperation();
        elementSommet = elementSommet.getNoeudSuivant();
        return operation;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui verifie et retourne si la pile est vide
     *
     * @return true si la pile chainee est vide, sinon false
     */
    public boolean pileVide() {
        return elementSommet == null;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne l'opération au sommet de la pile sans la dépiler.
     *
     * @return l'opération au sommet de la pile
     */
    public Operation voirElementSommet() {
        if (pileVide()){
            return null;
        }
        return elementSommet.getOperation();
    }

}
