package com.programmes;

import com.atoudeft.banque.CompteEpargne;
import com.atoudeft.banque.Operation.*;
import com.atoudeft.banque.TypeCompte;
import com.atoudeft.serveur.Config;
import com.atoudeft.serveur.Serveur;
import com.atoudeft.banque.serveur.ServeurBanque;

import java.util.Scanner;

/**
 * Programme simple de démonstration d'un serveur. Le programme démarre un serveur qui se met à écouter
 * l'arrivée de connexions.
 *
 * @author Abdelmoum�ne Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-09-01
 */
public class ProgrammeServeurTP1 {
	/**
	 * M�thode principale du programme.
	 *
	 * @param args Arguments du programme
	 */
    public static void main(String[] args) {


        Scanner clavier = new Scanner(System.in);
        String saisie;

        Serveur serveur = new ServeurBanque(Config.PORT_SERVEUR);
        if (serveur.demarrer()) {
            System.out.println("Serveur a l'ecoute sur le port " + serveur.getPort());
        }

        System.out.println("Saisissez EXIT pour arreter le serveur.");
        saisie = clavier.nextLine();
        while (!"EXIT".equals(saisie)) {
            System.out.println("??? Saisissez EXIT pour arreter le serveur.");
            saisie = clavier.nextLine();
        }
        serveur.arreter();
    /*
        //Test sur les Comptes Épargnes
        CompteEpargne compteEpargne= new CompteEpargne("ABD342W", TypeCompte.EPARGNE,0.05);
        compteEpargne.crediter(500);
        System.out.println("Solde après l'avoir crédité :"+compteEpargne.getSolde());

        compteEpargne.debiter(100);
        System.out.println("Solde après l'avoir débité :"+compteEpargne.getSolde());

        compteEpargne.ajouterInterets();
        System.out.println("Solde après avoir ajouté les intérets :"+compteEpargne.getSolde());
    */
        /*
        //Test sur les opérations
        OperationDepot operationDepotDepot= new OperationDepot(50.0);
        OperationRetrait operationRetrait = new OperationRetrait(50.0);
        OperationFacture operationFacture= new OperationFacture(50.0,"1234","Facture");
        OperationTransfer operationTransfer= new OperationTransfer(50.0,"ASD6BW1");
        */
    }



}