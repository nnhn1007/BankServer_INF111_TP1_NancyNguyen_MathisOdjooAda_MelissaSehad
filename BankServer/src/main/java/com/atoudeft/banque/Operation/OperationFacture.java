package com.atoudeft.banque.Operation;

import com.atoudeft.banque.TypeOperation;

/**
* OperationFacture représente les operations de paiement de facture
 * Elle est une sous-classe de la classe Operation et stocke les détails du paiement de facture;
 *       - sa date de transaction, le type de transaction, et le montant de transaction
 * @author Mathis Odjo'o Ada
 */
public class OperationFacture extends Operation {
    private final double montantFacture;
    private final String numeroFacture;
    private final String descriptionFacture;

    /**
     * Fait par Mathis Odjo'o Ada
     * Constructeur de l'opération de paiement de facture
     * @param montantFacture     le montant de la facture
     * @param numeroFacture      le numero de la facture
     * @param descriptionFacture la description de la facture
     */
    public OperationFacture(double montantFacture, String numeroFacture, String descriptionFacture) {
        super(TypeOperation.FACTURE);
        this.montantFacture = montantFacture;
        this.numeroFacture = numeroFacture;
        this.descriptionFacture = descriptionFacture;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne le montant de la facture
     * @return le montant de la facture
     */
    public double getMontantFacture() {
        return montantFacture;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne la numero de la facture
     * @return le numero de la facture
     */
    public String getNumeroFacture() {
        return numeroFacture;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne la description de la facture
     * @return la description de la facture
     */
    public String getDescriptionFacture() {
        return descriptionFacture;
    }

    /**
     * Q7.3 - Fait par Nancy Nguyen et Melissa Sehad
     * Décrit les détails du paiement de facture: la date, le type, et le montant
     * @return chaîne de format: DATE: [date]    TYPE: [type]    MONTANT: [montant]
     */
    public String toString() {
        return    "DATE: " + getDate()
                + "    TYPE: " + this.getType()
                + "    MONTANT: " + this.montantFacture;
    }
}