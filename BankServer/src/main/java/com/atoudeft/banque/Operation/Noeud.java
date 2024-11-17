package com.atoudeft.banque.Operation;

import java.io.Serializable;

/**
 * La classe Noeud represente un noeud dans une pile chainee
 * @author Melissa Sehad
 */
public class Noeud implements Serializable {
    private final Operation operation;
    private Noeud noeudSuivant;

    /**
     * Fait par Melissa Sehad
     * Constructeur de la classe Noeud
     * @param operation L'operation a stocker dans le noeud
     */
    public Noeud(Operation operation) {
        this.operation = operation;
        this.noeudSuivant = null;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne l'operation dans le noeud
     * @return l'operation contenue dans le noeud
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne le noeud suivant dans la pile
     * @return le noeud suivant
     */
    public Noeud getNoeudSuivant() {
        return noeudSuivant;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui definit le noeud suivant
     * @param noeudSuivant
     */
    public void setNoeudSuivant(Noeud noeudSuivant) {
        this.noeudSuivant = noeudSuivant;
    }

    /**
     * Fait par Melissa Sehad
     * Retroune le noeud sous forme de chaîne de caractères
     * @return une chaîne décrivant ce noeud
     */
    public String toString() {
        return "Noeud : " + operation.toString();
    }
}