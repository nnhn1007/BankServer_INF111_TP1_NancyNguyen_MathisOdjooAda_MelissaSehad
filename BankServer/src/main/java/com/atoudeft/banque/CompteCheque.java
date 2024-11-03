package com.atoudeft.banque;

public class CompteCheque extends CompteBancaire{
    final static  int ZERO=0;
    /**
     * Crée un compte chèque.
     *
     * @param numero numéro du compte
     * @param type   type du compte
     */
    public CompteCheque(String numero, TypeCompte type) {
        super(numero, type);
    }

    /**
     * Méthode qui réalise un dépot d'argent dans le solde d'un compte-Chèque.
     * @param montant Variable double qui représente le montant à ajouter au solde.
     * @return True si le solde initial était strictement positive, sinon retourne false
     */
    @Override
    public boolean crediter(double montant) {
        double solde= getSolde();
        if(solde>ZERO){ //Strictement positive
            solde+=montant; //Inutile?
            return true;
        }
        return false;
    }

    @Override
    public boolean debiter(double montant) {
        double solde=getSolde();
        if(solde>ZERO && solde>=montant){ //Strictement positive et solde >= montant
            solde-=montant; // Inutile ?
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
