package com.atoudeft.banque.Operation;

import com.atoudeft.banque.TypeOperation;

import java.io.Serializable;

/**
 * La classe OperationTransfer est une classe qui represente une operation de transfer bancaire
 */
public class OperationTransfer extends Operation implements Serializable {
    private final double montantTransfer;
    private final String numeroCompteDestinataire;

    /**
     * Constructeur de l'opération de transfer bancaire
     *
     * @param montantTransfer          Le montant du transfer
     * @param numeroCompteDestinataire Le numero de compte du destinataire
     */
    public OperationTransfer(double montantTransfer, String numeroCompteDestinataire) {
        super(TypeOperation.TRANSFER); // Je pense pcq on veut pas de TypeOperation dans le constructeur.
        this.montantTransfer = montantTransfer;
        this.numeroCompteDestinataire = numeroCompteDestinataire;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne le montant de transfer
     *
     * @return le montant du transfer
     */
    public double getMontantTransfer() {
        return montantTransfer;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne le numero de compte du destinataire
     *
     * @return le numero de compte du destinataire
     */
    public String getNumeroCompteDestinataire() {
        return numeroCompteDestinataire;
    }

    //Q7.3 - Fait par Nancy Nguyen
    public String toString() {
        return " Operation de transfert ( "
                + "DATE: " + getDate()
                + "    TYPE: " + this.getType()
                + "    MONTANT: " + this.montantTransfer + " )";
    }
}