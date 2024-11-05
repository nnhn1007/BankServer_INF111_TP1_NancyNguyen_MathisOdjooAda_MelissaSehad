package com.atoudeft.banque.Operation;

import com.atoudeft.banque.TypeOperation;

import java.io.Serializable;

/**
 * La classe OperationDepot est une classe qui represente une operation de depot bancaire
 */
public class OperationDepot extends Operation implements Serializable {
    private final double montantDepot;

    /**
     * Fait par Mathis Odjo'o Ada
     * Constructeur de l'operation de depot
     *
     * @param montantDepot Le montant  de depot
     */
    public OperationDepot(double montantDepot) {
        super(TypeOperation.DEPOT); // Je pense pcq on veut pas de TypeOperation dans le constructeur.
        this.montantDepot = montantDepot;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne le montant du depot
     *
     * @return le montant du depot
     */
    public double getMontantDepot() {
        return montantDepot;
    }

    //Q7.3 - Fait par Nancy Nguyen
    public String toString() {
        return " Montant de depot ( " + "DATE: " + getDate()
                + "    TYPE: " + this.getType()
                + "    MONTANT: " + this.montantDepot + " )";
    }
}
