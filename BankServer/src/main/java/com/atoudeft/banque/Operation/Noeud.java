package com.atoudeft.banque.Operation;

import java.io.Serializable;

/**
 * La classe Noeud est une classe qui represente un noeud dans une pileChainee
 */
public class Noeud implements Serializable {
    private final Operation operation;
    private Noeud noeudSuivant;

    /**
     * Fait par Melissa Sehad
     * Constructeur de la classe Noeud
     *
     * @param operation    L'operation a stocker dans le noeud
     * @param noeudSuivant Le noeud suivant dans la pile
     */
    public Noeud(Operation operation, Noeud noeudSuivant) {
        this.operation = operation;
        this.noeudSuivant = noeudSuivant;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne l'operation dans le noeud
     *
     * @return l'operation
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne le noeud suivant dans la pile
     *
     * @return le noeud suivant
     */
    public Noeud getNoeudSuivant() {
        return noeudSuivant;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui definit le noeud suivant
     *
     * @param noeudSuivant
     */
    public void setNoeudSuivant(Noeud noeudSuivant) {
        this.noeudSuivant = noeudSuivant;
    }

    public String toString() {
        return "Noeud : " + operation.toString();
    }
}