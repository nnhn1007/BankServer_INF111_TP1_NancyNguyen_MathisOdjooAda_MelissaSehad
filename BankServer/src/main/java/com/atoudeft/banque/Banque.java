package com.atoudeft.banque;

import com.atoudeft.banque.Operation.Operation;
import com.atoudeft.banque.Operation.OperationDepot;
import com.atoudeft.banque.Operation.OperationFacture;
import com.atoudeft.banque.Operation.OperationRetrait;
import org.w3c.dom.ls.LSOutput;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Banque implements Serializable {
    private String nom;
    private List<CompteClient> comptes;
    private TypeCompte type;

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
     * Fait par Mathis Odjo'o Ada
     *
     * @param numDeCompteClient
     * @return
     */
    public CompteClient getCompte(String numDeCompteClient) {
        System.out.println("Recherche du compte : " + numDeCompteClient); //TODO TEST À SUPPRIMER.
        for (CompteClient compte : comptes) {
            System.out.println("Compte présent dans la liste : " + compte.getNumero()); //TODO TEST À SUPPRIMER.

            if (compte.getNumero().equals(numDeCompteClient)) {
                return compte;
            }
        }
        return null; // Retourne null si aucun compte client n'est trouvé
    }

    /**
     * Vérifier qu'un compte-bancaire appartient bien au compte-client.
     *
     * @param numeroCompteBancaire numéro du compte-bancaire
     * @param numeroCompteClient   numéro du compte-client
     * @return true si le compte-bancaire appartient au compte-client
     */
    public boolean appartientA(String numeroCompteBancaire, String numeroCompteClient) {
        CompteClient compteClient = getCompteClient(numeroCompteClient);
        return (compteClient.getCompte(numeroCompteBancaire)!= null);
    }

    /**
     * Fait par Mathis Odjo'o Ada
     * Effectue un dépot d'argent dans un compte-bancaire
     *
     * @param montant      montant à déposer
     * @param numeroCompte numéro du compte
     * @return true si le dépot s'est effectué correctement
     */
    public boolean deposer(double montant, String numeroCompte) { // À tester
        CompteClient compteClient = getCompte(numeroCompte);

        if (compteClient != null) {
            CompteBancaire compteBancaire = getCompteBancaire(numeroCompte);
            if (compteBancaire != null && compteBancaire.crediter(montant) &&
                    appartientA(compteBancaire.getNumero(),compteClient.getNumero())) {
                System.out.println("Solde du début :" + compteBancaire.getSolde());
                System.out.println(compteBancaire.getSolde());
                OperationDepot operationDepot = new OperationDepot(montant);
                compteBancaire.ajouterOperation(operationDepot);
                return true;
            }
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
        CompteClient compteClient = getCompte(numeroCompte);
        if (compteClient != null) {
            CompteBancaire compteBancaire= getCompteBancaire(numeroCompte);
            if (compteBancaire != null && compteBancaire.debiter(montant)) {
                System.out.println("Solde du début (RETIRER) :" + compteBancaire.getSolde()); //TODO à enlever
                System.out.println(compteBancaire.getSolde());
                OperationRetrait operationRetrait = new OperationRetrait(montant);
                compteBancaire.ajouterOperation(operationRetrait);

                return true;
            }
        }
        return false;
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
        CompteClient compteClient = getCompte(numeroCompte);
        if (compteClient != null) {
            CompteBancaire compteBancaire = compteClient.getCompteBancaire(TypeCompte.EPARGNE);//TODO À changer
            if (compteBancaire != null && compteBancaire.payerFacture(numeroFacture, montant, description)) {
                System.out.println("Solde du début (RETIRER) :" + compteBancaire.getSolde()); //TODO à enlever
                System.out.println(compteBancaire.getSolde());
                OperationFacture operationFacture = new OperationFacture(montant, numeroFacture, description);
                compteBancaire.ajouterOperation(operationFacture);
                return true;
            }
        }
        return false;
    }

    /**
     * Fait par Mathis Odjo'o Ada
     * Crée un nouveau compte-client avec un numéro et un nip et l'ajoute à la liste des comptes.
     *
     * @param numCompteClient numéro du compte-client à créer
     * @param nip             nip du compte-client à créer
     * @return true si le compte a été créé correctement
     */
    public boolean ajouter(String numCompteClient, String nip) {
        for (int i = 0; i < numCompteClient.length(); i++) {
            char caractere = numCompteClient.charAt(i);
            if (!((caractere >= 'A' && caractere <= 'Z') || ((caractere >= '0' && caractere <= '9'))) || ( // Fonctionne et tester
                    numCompteClient.length() < 6 || numCompteClient.length() > 8)) {
                System.out.println("Test NON1"); //TODO ENLEVER LE TEST
                return false;
            }
        }
        if (!nip.matches("[0-9]+") || (nip.length() < 4 || nip.length() > 5)) {  //Fonctionne et tester
            System.out.println("Test NON2"); //TODO ENLEVER LE TEST
            return false;
        }
        if (!numeroEstValide(numCompteClient)) {
            System.out.println("Test NON3"); //TODO ENLEVER LE TEST
            return false;
        }
        creationDeCompte(numCompteClient, nip);
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
        String numeroCompte = ""; // Initialise la valeur
        CompteClient compteClient = getCompteClient(numCompteClient);
        if (compteClient != null) {
            CompteBancaire compteBancaire = compteClient.getCompteBancaire(TypeCompte.CHEQUE);
            if (compteBancaire != null) {
                numeroCompte = compteBancaire.getNumero();
            }
        }
        return numeroCompte; //Retourne le compte bancaire
    }

    /**
     * Fait par Mathis Odjo'o Ada
     *
     * @param numDeCompte
     * @return
     */
    public boolean numeroEstValide(String numDeCompte) {
        for (CompteClient compteClient : comptes) {
            if (!(compteClient.getCompteDestinataire(numDeCompte))) {
                return false;
            }
        }
        return true;
    }

    private void creationDeCompte(String numCompteClient, String nip) {
        System.out.println("Test OUI"); //TODO ENLEVER LE TEST
        CompteClient compteClient = new CompteClient(numCompteClient, nip);
        String numCompteBancaire = CompteBancaire.genereNouveauNumero();
        CompteCheque compteCheque = new CompteCheque(numCompteBancaire, TypeCompte.CHEQUE);
        System.out.println(numCompteBancaire);
        compteClient.ajouter(compteCheque);
        comptes.add(compteClient);
        System.out.println("Test Encore : " + compteClient.getNumero());
    }

    public CompteBancaire getCompteBancaire(String numCompteBancaire) {
        CompteBancaire compteBancaire;

        for (CompteClient compteClient : comptes) {
            System.out.println("ALLOTEST : "+compteClient.getCompte(numCompteBancaire));
            if (compteClient.getCompte(numCompteBancaire) != null) {
                compteBancaire = compteClient.getCompte(numCompteBancaire);
                return compteBancaire;
            }

        }
        return null;
    }
}