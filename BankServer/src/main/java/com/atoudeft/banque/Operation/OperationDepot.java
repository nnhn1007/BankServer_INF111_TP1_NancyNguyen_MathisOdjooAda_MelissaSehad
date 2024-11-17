package com.atoudeft.banque.Operation;

import com.atoudeft.banque.TypeOperation;
import java.io.Serializable;

/**
 * La classe OperationDepot represente une operation de depot bancaire
 * Elle est une sous-classe de la classe Operation et stocke les détails du dépôt bancaire;
 *      - sa date de transaction, le type de transaction, et le montant de transaction
 * @author Mathis Odjo'o Ada
 */
public class OperationDepot extends Operation implements Serializable {
    private final double montantDepot;

    /**
     * Fait par Mathis Odjo'o Ada
     * Constructeur de l'operation de depot
     * @param montantDepot Le montant  de depot
     */
    public OperationDepot(double montantDepot) {
        super(TypeOperation.DEPOT);
        this.montantDepot = montantDepot;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne le montant du depot
     * @return le montant du depot
     */
    public double getMontantDepot() {
        return montantDepot;
    }

    /**
     * Q7.3 - Fait par Nancy Nguyen et Melissa Sehad
     * Décrit les détails du dépôt:la date, le type, et le montant
     * @return chaîne de format: DATE: [date]    TYPE: [type]    MONTANT: [montant]
     */
    public String toString() {
        return    "DATE: " + getDate()
                + "    TYPE: " + this.getType()
                + "    MONTANT: " + this.montantDepot;
    }
}
