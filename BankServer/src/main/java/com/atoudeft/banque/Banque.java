package com.atoudeft.banque;

import org.w3c.dom.ls.LSOutput;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Banque implements Serializable {
    private String nom;
    private List<CompteClient> comptes;

    public Banque(String nom) {
        this.nom = nom;
        this.comptes = new ArrayList<>();
    }

    /**
     * Recherche un compte-client à partir de son numéro.
     *
     * @param numeroCompteClient le numéro du compte-client
     * @return le compte-client s'il a été trouvé. Sinon, retourne null
     */
    public CompteClient getCompteClient(String numeroCompteClient) {
        CompteClient cpt = new CompteClient(numeroCompteClient, "");
        int index = this.comptes.indexOf(cpt);
        if (index != -1)
            return this.comptes.get(index);
        else
            return null;
    }

    /**
     * Vérifier qu'un compte-bancaire appartient bien au compte-client.
     *
     * @param numeroCompteBancaire numéro du compte-bancaire
     * @param numeroCompteClient   numéro du compte-client
     * @return true si le compte-bancaire appartient au compte-client
     */
    public boolean appartientA(String numeroCompteBancaire, String numeroCompteClient) {
        throw new NotImplementedException();
    }

    /**
     * Effectue un dépot d'argent dans un compte-bancaire
     *
     * @param montant      montant à déposer
     * @param numeroCompte numéro du compte
     * @return true si le dépot s'est effectué correctement
     */
    public boolean deposer(double montant, String numeroCompte) { // À tester
        CompteClient compteClient= getCompteClient(numeroCompte);
        if(compteClient!=null){
            // On dois la définir
            throw new NotImplementedException(); // À enlever
        }
        return false;
    }

    /**
     * Effectue un retrait d'argent d'un compte-bancaire
     *
     * @param montant      montant retiré
     * @param numeroCompte numéro du compte
     * @return true si le retrait s'est effectué correctement
     */
    public boolean retirer(double montant, String numeroCompte) {
        throw new NotImplementedException();
    }

    /**
     * Effectue un transfert d'argent d'un compte à un autre de la même banque
     *
     * @param montant             montant à transférer
     * @param numeroCompteInitial numéro du compte d'où sera prélevé l'argent
     * @param numeroCompteFinal   numéro du compte où sera déposé l'argent
     * @return true si l'opération s'est déroulée correctement
     */
    public boolean transferer(double montant, String numeroCompteInitial, String numeroCompteFinal) {
        throw new NotImplementedException();
    }

    /**
     * Effectue un paiement de facture.
     *
     * @param montant       montant de la facture
     * @param numeroCompte  numéro du compte bancaire d'où va se faire le paiement
     * @param numeroFacture numéro de la facture
     * @param description   texte descriptif de la facture
     * @return true si le paiement s'est bien effectuée
     */
    public boolean payerFacture(double montant, String numeroCompte, String numeroFacture, String description) {
        throw new NotImplementedException();
    }

    /**
     * Crée un nouveau compte-client avec un numéro et un nip et l'ajoute à la liste des comptes.
     *
     * @param numCompteClient numéro du compte-client à créer
     * @param nip             nip du compte-client à créer
     * @return true si le compte a été créé correctement
     */
    public boolean ajouter(String numCompteClient, String nip) {
        for (int i = 0; i < numCompteClient.length(); i++) {
            char caractere = numCompteClient.charAt(i);
            if (!((caractere >= 'A' && caractere <= 'Z') || ((caractere>='0' && caractere<='9')))  ||  ( // Fonctionne et tester
                    numCompteClient.length() < 6 || numCompteClient.length() > 8)){
                System.out.println("Test NON1"); //TODO ENLEVER LE TEST
                return false;
            }
        }
        if (!nip.matches("[0-9]+") || (nip.length() < 4 || nip.length() > 5)) {  //Fonctionne et tester
            System.out.println("Test NON2"); //TODO ENLEVER LE TEST
            return false;
        }
        for (int i=0; i< comptes.size(); i++) { // Fonctionne et tester
            if (numeroEstValide(numCompteClient)) {
                System.out.println("Test NON3"); //TODO ENLEVER LE TEST
                return false;
            }
        }

        System.out.println("Test OUI"); //TODO ENLEVER LE TEST
        CompteClient compteClient = new CompteClient(numCompteClient, nip);
        String numCompteBancaire = CompteBancaire.genereNouveauNumero();
        CompteCheque compteCheque = new CompteCheque(numCompteBancaire, TypeCompte.CHEQUE);
        System.out.println(numCompteBancaire);
        compteClient.ajouter(compteCheque);
        comptes.add(compteClient);
        return true;
    }

    /**
     * Fait par (Nancy Nguyen et Mathis Odjo'o Ada)
     * Retourne le numéro du compte-chèque d'un client à partir de son numéro de compte-client.
     *
     * @param numCompteClient numéro du compte-client
     * @return numéro du compte-chèque du client ayant le numéro de compte-client
     */
    public String getNumeroCompteParDefaut(String numCompteClient) {
        CompteClient compteClient =getCompteClient(numCompteClient);

        if(compteClient != null){
            return getCompteClient(numCompteClient).toString();
        }
        return null; // Retourne null si aucun-compte-chèque n'a été trouvé
    }
    public boolean numeroEstValide (String numDeCompte){
        for (CompteClient compteClient : comptes) {
            if(compteClient.getCompteDestinataire(numDeCompte)!= null) {
                System.out.println("Test NON3"); //TODO ENLEVER LE TEST
                return false;
            }
        }
        return true;
    }
}