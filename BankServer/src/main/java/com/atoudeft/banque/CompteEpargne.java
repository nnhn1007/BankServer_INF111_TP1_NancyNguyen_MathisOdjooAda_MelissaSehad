package com.atoudeft.banque;

/**
 * CompteEpargne illustre un compte-épargne ayant un taux d'intérêt, et des
 * frais appliqués dépendamment du solde du compte du client
 * @author Nancy Nguyen
 */


public class CompteEpargne extends CompteBancaire {
    private final int ZERO = 0;
    private final double TAUX_INTERET;
    private final double LIMITE = 1000.00;
    private final double FRAIS = 2.00;

    /**
     * Fait par Nancy Nguyen
     * Constructeur avec parametres
     * @param numero       numéro du compte
     * @param type         type du compte
     * @param TAUX_INTERET taux d'interet du compte
     */
    public CompteEpargne(String numero, TypeCompte type, double TAUX_INTERET) {
        super(numero, type);
        this.TAUX_INTERET = TAUX_INTERET;
    }

    /**
     * Fait par Nancy Nguyen
     * Crédite un montant au solde, s'il est strictement positif
     * @param montant Montant à créditer au solde
     * @return true, si le montant est positif
     *         false, si le montant est négatif ou nul
     */
    @Override
    public boolean crediter(double montant) {
        if (montant > ZERO) { //Strictement positif
            setSolde(getSolde() + montant);
            return true;
        }
        return false;
    }

    /**
     * Fait par Nancy Nguyen
     * Débite le montant du solde s'il est positif, et s'il y a assez de fonds au solde
     * @param montant Montant à retirer
     * @return True, si le solde initial était strictement positif et qu'il
     *              est plus grand ou égal au montant à retirer,
     *              false sinon.
     */
    @Override
    public boolean debiter(double montant) {
        double soldeInitial = getSolde();
        if (montant > ZERO && getSolde() > (montant + FRAIS)) {
            setSolde(getSolde() - montant); //Débite le montant du solde
            if (soldeInitial < LIMITE) {
                setSolde(getSolde() - FRAIS); //Débite les frais si getSolde()<1000
            }

            return true;
        }
        return false;
    }

    /**
     * Fait par Nancy Nguyen
     * Facture un montant en utilisant le solde disponible en vérifiant d'abord si numeroFacture
     * est nulle, si le solde est suffisant si et la description est non nulle.
     * @param numeroFacture le numéro de la facture à payer
     * @param montant       le montant de la facture à payer
     * @param description   une description de la facture
     * @return true si la facture a été payée avec succès, false sinon.
     */
    @Override
    public boolean payerFacture(String numeroFacture, double montant, String description) {
        if (getSolde() > (montant + FRAIS) && montant > ZERO) {
            setSolde(getSolde() - montant);
            return true;
        }
        return false;
    }

    /**
     * Fait par Nancy Nguyen
     * Fait le transfert d'un montant vers un autre comopte, si le solde est suffisant
     * @param montant                  montant à transférer
     * @param numeroCompteDestinataire le numéro de compte destinataire
     * @return true, si le transfert a été effectué avec succès, false sinon
     */
    @Override
    public boolean transferer(double montant, String numeroCompteDestinataire) {
        if (getSolde() > (montant + FRAIS) && montant > ZERO) {
            setSolde(getSolde() - montant);
            return true;
        }
        return false;
    }
}
