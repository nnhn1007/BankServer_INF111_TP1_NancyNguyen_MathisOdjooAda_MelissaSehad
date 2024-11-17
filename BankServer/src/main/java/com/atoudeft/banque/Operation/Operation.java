package com.atoudeft.banque.Operation;

import com.atoudeft.banque.TypeOperation;

import java.io.Serializable;
import java.util.Date;

/**
 * La classe Operation est une classe qui represente une operation bancaire
 * Elle est la classe parent des classe OperationDepot, OperationFacture, OperationRetrait et OperationTranfer
 * @author Mathis Odjo'o Ada
 */
public abstract class Operation implements Serializable {
    private final TypeOperation type;
    private final Date date;

    /**
     * Fait par Mathis Odjo'o Ada
     * Constructeur qui initialise le type de l'opération
     * et la date de l'heure actuelle.
     * @param type Le type de l'Opération que le client veut exécuter
     */
    public Operation(TypeOperation type) {
        this.type = type;
        this.date = new Date(System.currentTimeMillis());
    }

    /**
     * Fait par Mathis Odjo'o Ada et Nancy Nguyen
     * Methode qui retourne le type de l'operation
     * @return type
     */
    public TypeOperation getType() {
        return type;
    }

    /**
     * Fait par Mathis Odjo'o Ada et Nancy Nguyen
     * Methode qui retourne la date de l'operation et l'heure de l'operation
     * @return date
     */
    public Date getDate() {
        return date;
    }
}
