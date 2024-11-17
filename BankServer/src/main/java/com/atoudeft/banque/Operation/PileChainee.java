package com.atoudeft.banque.Operation;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe PileChainee est une classe qui represente une pile chainee d'operation banacaire
 * Elle permet d'empiler et de depiler les operations bancaires (LIFO)
 */
public class PileChainee implements Serializable {
    private Noeud elementSommet;

    /**
     * Constructeur par defaut de la classe PileChainee
     * Initialise une pile chainee vide
     */
    public PileChainee() {
        this.elementSommet = null;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui empile une nouvelle operation au sommet de la pile chainee
     * @param operation L'operation banncaire a empiler
     */
    public void empiler(Operation operation) {
        Noeud suivant = new Noeud(operation); //PS : Valeur temporaire (TEMP)
        suivant.setNoeudSuivant(elementSommet);
        elementSommet = suivant;
    }

    /**
     * Fait par Melissa Sehad
     * Trouve l'operation situee au dessus de la pile chainee, et la retourne
     * @return  l'operation au dessus de la pile,
     *          null, si la pile est vide
     */
    public Operation getSommetDePile(){
        if(elementSommet!=null){
           return elementSommet.getOperation();
        }
        return null;
    }

    /**
     * Fait par Melissa Sehad et Mathis Odjo'o Ada
     * Recupere l'historique des operations de l'utilisateur, et la retourne
     * @return liste contenant les operations du client
     *         liste vide si la pile est vide
     */
    public ArrayList<Operation> getHistoriqueOperation(){
        ArrayList<Operation> historiqueOperation = new ArrayList<>();
        while(elementSommet!=null){
            historiqueOperation.add(elementSommet.getOperation());
            elementSommet= elementSommet.getNoeudSuivant();
        }
        return historiqueOperation;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui verifie et retourne si la pile est vide
     * @return true si la pile chainee est vide, sinon false
     */
    public boolean estVide() {
        return elementSommet == null;
    }
}
