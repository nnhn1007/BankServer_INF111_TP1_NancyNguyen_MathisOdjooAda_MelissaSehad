package com.atoudeft.banque.Operation;

import com.atoudeft.banque.TypeOperation;

import java.io.Serializable;

/**
 * La classe OperationRetrait est une classe qui represente les operation de retrait bancaire
 */
public class OperationRetrait extends Operation implements Serializable {
    private final double montantRetrait;

    /**
     * Fait par Mathis Odjo'o Ada
     * Constructeur de l;operation de retrait bancaire
     *
     * @param montantRetrait Le type de l'Opération que le client veut exécuter
     */
    public OperationRetrait(double montantRetrait) {
        super(TypeOperation.RETRAIT); // Je pense pcq on veut pas de TypeOperation dans le constructeur.
        this.montantRetrait = montantRetrait;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne le montant de retrait
     *
     * @return le montant du retrait bancaire
     */
    public double getMontantRetrait() {
        return montantRetrait;
    }

    //Q7.3 - Fait par Nancy Nguyen
    public String toString() {
        return "Retrait bancaire ( " + "DATE: " + getDate()
                + "    TYPE: " + this.getType()
                + "    MONTANT: " + this.montantRetrait + " )";
    }
}
