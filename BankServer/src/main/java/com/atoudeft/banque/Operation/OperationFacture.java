package com.atoudeft.banque.Operation;

import com.atoudeft.banque.TypeOperation;

public class OperationFacture extends Operation {
    private double montantFacture;
    private String numeroFacture;
    private String descriptionFacture;

    /**
     * Fait par Mathis Odjo'o Ada
     *
     * @param montantFacture
     * @param numeroFacture
     * @param descriptionFacture
     */
    public OperationFacture(double montantFacture, String numeroFacture, String descriptionFacture) {
        super(TypeOperation.FACTURE);
        this.montantFacture = montantFacture;
        this.numeroFacture = numeroFacture;
        this.descriptionFacture = descriptionFacture;
    }

    //Q7.3 - Fait par Nancy Nguyen
    public String toString() {
        return "DATE: " + getDate() + "     TYPE: " +  this.getType() + "     MONTANT: " +  this.montantFacture;
    }
}
