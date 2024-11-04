package com.atoudeft.banque.Operation;

import com.atoudeft.banque.TypeOperation;

public class OperationDepot extends Operation{
    private double montantDepot;
    /**
     * Fait par Mathis Odjo'o Ada
     *
     * @param montantDepot
     */
    public OperationDepot(Double montantDepot) {
        super(TypeOperation.DEPOT); // Je pense pcq on veut pas de TypeOperation dans le constructeur.
        this.montantDepot=montantDepot;
    }

}
