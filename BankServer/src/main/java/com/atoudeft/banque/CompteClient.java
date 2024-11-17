package com.atoudeft.banque;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompteClient implements Serializable {
    private String numero;
    private String nip;
    private List<CompteBancaire> comptes;

    /**
     * Crée un compte-client avec un numéro et un nip.
     *
     * @param numero le numéro du compte-client
     * @param nip    le nip
     */
    public CompteClient(String numero, String nip) {
        this.numero = numero;
        this.nip = nip;
        comptes = new ArrayList<>();
    }

    /**
     * Ajoute un compte bancaire au compte-client.
     *
     * @param compte le compte bancaire
     * @return true si l'ajout est réussi
     */
    public boolean ajouter(CompteBancaire compte) {
        return this.comptes.add(compte);
    }

    public String getNip() {
        return this.nip;
    } //Q3.1 - Ecq on peut l'ajouter??


    /**
     * //TODO Ne pas oublier le constructeur.
     *
     * @param numDeCompte
     * @return
     */
    public boolean getCompteDestinataire(String numDeCompte) {
        for (int i=0; i<comptes.size(); i++) {  //TODO À changer.
            if ((numDeCompte.equals(getNumero()))){
                return false;
            }
        }
        return true;
    }

    /**
     * @param typeCompte
     * @return
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
     * @param numDeCompte
     * @return
     */
    public CompteBancaire getCompteBancaire(String numDeCompte) {
        System.out.println("Recherche du compte : " + numDeCompte); //TODO TEST À SUPPRIMER.
        for (CompteBancaire compteBancaire : comptes) {
            System.out.println("Compte présent : " + compteBancaire.getNumero()); //TODO TEST À SUPPRIMER.
            if (compteBancaire.getNumero().equals(numDeCompte)) {
                return compteBancaire;
            }
        }
        return null;
    }


    /**
     * Fait par Melissa Sehad
     * Methode qui returne le numero du compte-client
     *
     * @return le numero du compte-client
     */
    public String getNumero() {
        return numero;
    }
}