package com.atoudeft.banque;

import com.atoudeft.banque.Operation.Noeud;
import com.atoudeft.banque.Operation.Operation;
import com.atoudeft.banque.Operation.PileChainee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class CompteBancaire implements Serializable {
    private String numero;
    private TypeCompte type;
    private double solde;
    private TypeCompte typeDeCompte;
    private PileChainee historique;

    /**
     * Génère un numéro de compte bancaire aléatoirement avec le format CCC00C, où C est un caractère alphabétique
     * majuscule et 0 est un chiffre entre 0 et 9.
     * @return  un numéro de compte bancaire généré sous forme d'une chaîne de caractères
     */
    public static String genereNouveauNumero() {
        char[] t = new char[6];
        for (int i=0;i<3;i++) {
            t[i] = (char)((int)(Math.random()*26)+'A');
        }
        for (int i=3;i<5;i++) {
            t[i] = (char)((int)(Math.random()*10)+'0');
        }
        t[5] = (char)((int)(Math.random()*26)+'A');
        return new String(t);
    }

    /**
     * Crée un compte bancaire.
     * @param numero numéro du compte
     * @param type type du compte
     */
    public CompteBancaire(String numero, TypeCompte type) {
        this.numero = numero;
        this.type = type;
        this.solde = 0;
        this.historique=new PileChainee();
    }

    /**
     * Accède au numéro du compte bancaire
     * @return le numéro du compte bancaire
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Accède au type du compte bancaire
     * @return le type du compte bancaire
     */
    public TypeCompte getType() {
        return type;
    }

    /**
     * Accède au solde du compte bancaire
     * @return le solde du compte bancaire
     */
    public double getSolde() {
        return solde;
    }

    /**
     * Fait par Nancy Nguyen et Mathis Odjo'o Ada
     * Ajoute une opération à l'historique des opérations effectuées dans un compte bancaire
     * @param operation l'opération à ajouter dans l'historique
     */
    public void ajouterOperation(Operation operation){
        historique.empiler(operation);
    }

    /** Fait par Mathis Odjo'o Ada
     * Affiche l'historique des opérations effectuées dans un compte bancaire
     * @return l'historique des opérations sous forme de liste
     */
    public ArrayList<Operation> afficherHistoriqueOperation(){
        return historique.getHistoriqueOperation();
    }

    /**
     * Fait par Nancy Nguyen et Mathis Odjo'o Ada
     *  Met à jour le solde du compte bancaire
     * @param solde solde à mettre à jour
     * @return le solde, après avoir été  mis-à-jour
     */
    public double setSolde(double solde) {
        return this.solde = solde;
    }

    /**
     * Méthode abstraite qui dépose un montant dans un compte bancaire
     * @param montant montant à créditer
     * @return true si le crédit est réussi, false sinon.
     */
    public abstract boolean crediter(double montant);

    /**
     * Méthode abstraite qui débite un montant à un compte bancaire
     * @param montant montant à débiter
     * @return true si le débit est réussi, false sinon.
     */
    public abstract boolean debiter(double montant);

    /**
     * Méthode abstraite permettant de payer une facture à partir d'un compte bancaire
     * @param numeroFacture numéro de la facture à payer
     * @param montant montant de la facture
     * @param description description de la facture
     * @return true si le paiement a été effectué avec succès, false sinon.
     */
    public abstract boolean payerFacture(String numeroFacture, double montant, String description);

    /**
     * Méthode abstraite trasférant un montant vers un autre compte bancaire
     * @param montant                  montant à tranférer
     * @param numeroCompteDestinataire numéro du compte bancaire qui reçoit le transfert
     * @return true si le transfert est réussi, false sinon
     */
    public abstract boolean transferer(double montant, String numeroCompteDestinataire);
}