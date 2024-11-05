package com.atoudeft.banque.Operation;

import com.atoudeft.banque.TypeOperation;

public class OperationRetrait extends Operation{
    private double montantRetrait;
    /**
     * Fait par Mathis Odjo'o Ada
     *
     * @param montantRetrait Le type de l'Opération que le client veut exécuter
     */
    public OperationRetrait(double montantRetrait) {
        super(TypeOperation.RETRAIT); // Je pense pcq on veut pas de TypeOperation dans le constructeur.
        this.montantRetrait=montantRetrait;
    }

    //Q7.3 - Fait par Nancy Nguyen
    public String toString() {
        return "DATE: " + getDate() + "     TYPE: " +  this.getType() + "     MONTANT: " +  this.montantRetrait;
    }
}
