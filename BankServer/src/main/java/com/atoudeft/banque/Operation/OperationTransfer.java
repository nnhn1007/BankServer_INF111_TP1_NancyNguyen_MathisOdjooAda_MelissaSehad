package com.atoudeft.banque.Operation;

import com.atoudeft.banque.TypeOperation;
import java.io.Serializable;

/**
 * La classe OperationTransfer est une classe qui représente une opération de transfert bancaire.
 * Elle est une sous-classe de la classe Operation et stocke les détails du transfert bancaire;
 *      - sa date de transaction, le type de transaction, et le montant de transaction
 * @author Mathis Odjo'o Ada
 */
public class OperationTransfer extends Operation implements Serializable {
    private final double montantTransfer;
    private final String numeroCompteDestinataire;

    /**
     * Fait par Mathis Odjo'o Ada
     * Constructeur de l'opération de transfert bancaire
     * @param montantTransfer          Le montant du transfert
     * @param numeroCompteDestinataire Le numéro de compte du destinataire
     */
    public OperationTransfer(double montantTransfer, String numeroCompteDestinataire) {
        super(TypeOperation.TRANSFER);
        this.montantTransfer = montantTransfer;
        this.numeroCompteDestinataire = numeroCompteDestinataire;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne le montant de transfert
     * @return le montant du transfert
     */
    public double getMontantTransfer() {
        return montantTransfer;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne le numero de compte du destinataire
     * @return le numero de compte du destinataire
     */
    public String getNumeroCompteDestinataire() {
        return numeroCompteDestinataire;
    }

    /**
     * Q7.3 - Fait par Nancy Nguyen et Melissa Sehad
     * Décrit l'opération de transfert, incluant la date, le type, et le montant de transfert
     * @return chaîne de format: DATE: [date] TYPE: [type] MONTANT: [montant]
     */
    public String toString() {
        return    "DATE: " + getDate()
                + "    TYPE: " + this.getType()
                + "    MONTANT: " + this.montantTransfer + " )";
    }
}