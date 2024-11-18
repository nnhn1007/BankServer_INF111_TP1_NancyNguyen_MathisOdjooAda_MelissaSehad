package com.atoudeft.banque;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompteClient implements Serializable {
    private String numero;
    private String nip;
    private List<CompteBancaire> comptes;

    /**
     * Crée un compte-client avec un numéro et un NIP
     * @param numero le numéro unique du compte-client
     * @param nip    le NIP associé au compte-client
     */
    public CompteClient(String numero, String nip) {
        this.numero = numero;
        this.nip = nip;
        comptes = new ArrayList<>();
    }

    /**
     * Ajoute un compte bancaire à la liste des comptes du client.
     * @param compte le compte bancaire à ajouter
     * @return true si l'ajout est réussi, false sinon
     */
    public boolean ajouter(CompteBancaire compte) {
        return this.comptes.add(compte);
    }

    /**
     *Fait par Nancy Nguyen
     * Accède au nip associé au compte-client
     * @return le nip du compte-client
     */
    public String getNip() {
        return this.nip;
    }


    /**
     * Fait Mathis Odjo'o Ada
     * Vérifie si le numéro de compte fourni est déjà présent dans les comptes
     * bancaires enregistrés.
     * @param numDeCompte le numéro de compte à vérifier
     * @return true si le numéro de compte n'est pas trouvé parmi les comptes,
     *         false sinon.
     */
    public boolean getCompteDestinataire(String numDeCompte) {
        for (int i=0; i<comptes.size(); i++) {
            if ((numDeCompte.equals(getNumero()))){
                return false;
            }
        }
        return true;
    }

    /**
     * Fait Mathis Odjo'o Ada
     * Retourne un compte bancaire correspondant au type de compte spécifié.
     * @param typeCompte Le type de compte recherché par le client
     * @return Le compte bancaire correspondant au type spécifié, sinon retourne
     * null si aucun compte n'est trouvé
     */
    public CompteBancaire getCompteBancaire(TypeCompte typeCompte) {
        for (CompteBancaire compteBancaire : comptes) {
            if (compteBancaire.getType() == typeCompte) {
                return compteBancaire;
            }
        }
        return null; // Si on ne trouve pas de compte bancaire relié
    }

    /**
     * Retourne un compte bancaire correspondant au numéro de compte spécifié.
     * @param numDeCompte le numéro de compte recherché par le client
     * @return le compte bancaire correspondant au numéro de compte, sinon
     * retourne null s'il n'est pas trouvé
     */
    public CompteBancaire getCompteBancaire(String numDeCompte) {
        for (CompteBancaire compteBancaire : comptes) {
            if (compteBancaire.getNumero().equals(numDeCompte)) {
                return compteBancaire;
            }
        }
        return null;
    }

    /**
     * Fait par Melissa Sehad
     * Methode qui retourne le numero du compte-client
     * @return le numéro du compte-client
     */
    public String getNumero() {
        return numero;
    }
}