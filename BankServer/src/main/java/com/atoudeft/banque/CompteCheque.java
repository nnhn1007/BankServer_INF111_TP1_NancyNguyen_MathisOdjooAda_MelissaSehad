package com.atoudeft.banque;

import com.atoudeft.banque.Operation.OperationDepot;
import com.atoudeft.banque.Operation.OperationRetrait;

public class CompteCheque extends CompteBancaire {
    private final int ZERO = 0;

    /**
     * Fait par Mathis Odjo'o Ada
     * Crée un compte chèque.
     * @param numero numéro du compte
     * @param type   type du compte
     */
    public CompteCheque(String numero, TypeCompte type) {
        super(numero, type);
    }

    /**
     * Fait par Mathis Odjo'o Ada
     * Méthode qui réalise un dépot d'argent dans le solde d'un compte-Chèque.
     *
     * @param montant Variable double qui représente le montant à ajouter au solde.
     * @return True si le solde initial était strictement positive, sinon retourne false
     */
    @Override
    public boolean crediter(double montant) {
        if (montant > ZERO) {
            setSolde(getSolde() + montant);
            return true;
        }
        return false;
    }

    /**
     * Fait par Mathis Odjo'o Ada
     * Méthode qui réalise un retrait d'argent dans le solde d'un compte-chèque
     *
     * @param montant Variable double qui représente le montant à débiter du solde
     * @return True si le solde initial était strictement positif et que le solde
     * intiale est plus grand ou égal au montant à retirer, sinon retourne false.
     */
    @Override
    public boolean debiter(double montant) {
        if (montant > ZERO && getSolde() >= montant) { //Strictement positive et solde >= montant
            setSolde(getSolde() - montant);
            return true;
        }
        return false;
    }

    /**
     * Fait par Mathis
     * Facture un montant en utilisant le solde disponible en vérifiant
     * d'abord si numeroFacture est nulle, si le solde est suffisant si
     * et la description est non nulle.
     *
     * @param numeroFacture le numéro de la facture à payer
     * @param montant       le montant de la facture à payer
     * @param description   une description de la facture
     * @return true si la facture a été payée avec succès, sinon return false
     */
    @Override
    public boolean payerFacture(String numeroFacture, double montant, String description) {
        if (getSolde() > montant && numeroFacture != null && description != null) {
            debiter(montant);
            return true;
        }
        return false;
    }

    /**
     * @param montant
     * @param numeroCompteDestinataire
     * @return
     */
    @Override
    public boolean transferer(double montant, String numeroCompteDestinataire) {
        if (getSolde() > montant && montant > ZERO) {
            setSolde(getSolde() - montant);
            return true;
        }
        return false;
    }
}
