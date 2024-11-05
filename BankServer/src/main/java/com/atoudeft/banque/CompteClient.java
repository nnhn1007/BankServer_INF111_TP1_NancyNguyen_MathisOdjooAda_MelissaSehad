package com.atoudeft.banque;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    /* public boolean numeroValide(String numeroDeCompte){
         for (CompteBancaire compteBancaire: comptes){
             if(compteBancaire.(numeroDeCompte)!=null){
                 return false;
             }
         }
         return true;
     } /*

     /**
      *          //TODO Ne pas oublier le constructeur.
      * @param numDeCompte
      * @return
      */
    public CompteBancaire getCompteDestinataire(String numDeCompte) {
        for (CompteBancaire compteBancaire : comptes) {
            if (Objects.equals(compteBancaire.getNumero(), numDeCompte)) {
                System.out.println("TEST CAS 2.1 :" + compteBancaire.getNumero());
                return compteBancaire;
            }
        }
        return null;
    }

}