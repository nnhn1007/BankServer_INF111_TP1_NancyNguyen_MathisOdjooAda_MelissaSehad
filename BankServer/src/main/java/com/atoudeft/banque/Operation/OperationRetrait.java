package com.atoudeft.banque.Operation;

import com.atoudeft.banque.TypeOperation;
import java.io.Serializable;

/**
 * La classe OperationRetrait est une classe qui représente les opérations de retrait bancaire
 * Elle est une sous-classe de la classe Operation et stocke les détails du retrait bancaire ;
 *        - sa date de transaction, le type de transaction, et le montant de transaction
 * @author Mathis Odjo'o Ada
 */
public class OperationRetrait extends Operation implements Serializable {
    private final double montantRetrait;

    /**
     * Fait par Mathis Odjo'o Ada
     * Constructeur de l'opération de retrait bancaire
     * @param montantRetrait Le type de l'Opération que le client veut exécuter
     */
    public OperationRetrait(double montantRetrait) {
        super(TypeOperation.RETRAIT); // Je pense pcq on veut pas de TypeOperation dans le constructeur.
        this.montantRetrait = montantRetrait;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne le montant de retrait
     * @return le montant du retrait bancaire
     */
    public double getMontantRetrait() {
        return montantRetrait;
    }

    /**
     * Q7.3 - Fait par Nancy Nguyen et Melissa Sehad
     * Décrit l'opération de retrait, incluant la date, le type, et le montant du retrait
     * @return chaîne de format:  DATE: [date] TYPE: [type] MONTANT: [montant]
     */
    public String toString() {
        return    "DATE: " + getDate()
                + "    TYPE: " + this.getType()
                + "    MONTANT: " + this.montantRetrait + " )";
    }
}

