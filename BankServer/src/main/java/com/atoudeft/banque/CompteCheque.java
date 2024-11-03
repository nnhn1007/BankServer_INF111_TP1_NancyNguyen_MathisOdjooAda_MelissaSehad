package com.atoudeft.banque;

public class CompteCheque extends CompteBancaire {
    private final int ZERO = 0;

    /**
     * Fait par Mathis Odjo'o Ada
     * Crée un compte chèque.
     *
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

    @Override
    public boolean payerFacture(String numeroFacture, double montant, String description) {
        return false;
    }

    @Override
    public boolean transferer(double montant, String numeroCompteDestinataire) {
        return false;
    }
}
