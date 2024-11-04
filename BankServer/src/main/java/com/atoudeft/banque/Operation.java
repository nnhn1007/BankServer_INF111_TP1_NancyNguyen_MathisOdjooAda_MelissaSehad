package com.atoudeft.banque;

import java.io.Serializable;
import java.util.Date;

public abstract class Operation implements Serializable {
    private TypeOperation type;
    private Date date;

    /**
     * Fait par Mathis Odjo'o Ada
     * Constructeur qui initialise le type de l'opération
     * et la date de l'heure actuelle.
     * @param type Le type de l'Opération que le client veut exécuter
     */
    public Operation(TypeOperation type) {
        this.type = type;
        this.date = new Date(System.currentTimeMillis()); // Date de l'opération
    }

    /**
     * Fait par Mathis Odjo'o Ada
     * À vérifier puisque ce n'est pas demandé dans l'énoncé.
     * @return
     */
    public TypeOperation getType() {
        return type;
    }

    /**
     * Fait par Mathis Odjo'o Ada
     * À vérifier puisque ce n'est pas demandé dans l'énoncé.
     * @return
     */
    public Date getDate() {
        return date;
    }
}
