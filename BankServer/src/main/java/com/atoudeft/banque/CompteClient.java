package com.atoudeft.banque;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.atoudeft.banque.serveur.ConnexionBanque;

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
    public String getNumero() {
        return numero;
    }

    public String getNip() {
        return this.nip;
    } //Q3.1 - Ecq on peut l'ajouter??

    /* public boolean numeroValide(String numeroDeCompte){
         for (CompteBancaire compteBancaire: comptes){
             if(compteBancaire.(numeroDeCompte)!=null){
                 return false;
             }
         }
         return true;
     } /*

    /**
     * //TODO Ne pas oublier le constructeur.
     *
     * @param numDeCompte
     * @return
     */
    public boolean getCompteDestinataire(String numDeCompte) {
        for (CompteBancaire compteBancaire : comptes) {  //TODO À changer.
            if ((numDeCompte.equals(getNumero()))) {
                return false;
            }
        }
        return true;
    }
    public CompteBancaire getCompteBancaire(TypeCompte typeCompte) {
        for (CompteBancaire compteBancaire : comptes) {
            if (compteBancaire.getType() == typeCompte) {
                return compteBancaire;
            }
        }
        return null; // Si on ne trouve pas de compte bancaire relié
    }
    public CompteBancaire getCompte(String numDeCompte) {
        for (CompteBancaire compteBancaire : comptes) {
            if (compteBancaire.getNumero() == numero) {
                return compteBancaire;
            }
        }
        return null;
    }
}