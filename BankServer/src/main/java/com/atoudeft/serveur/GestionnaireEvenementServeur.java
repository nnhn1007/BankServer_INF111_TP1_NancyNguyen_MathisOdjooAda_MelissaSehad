package com.atoudeft.serveur;

import com.atoudeft.banque.*;
import com.atoudeft.banque.Operation.Operation;
import com.atoudeft.banque.Operation.OperationDepot;
import com.atoudeft.banque.serveur.ConnexionBanque;
import com.atoudeft.banque.serveur.ServeurBanque;
import com.atoudeft.commun.evenement.Evenement;
import com.atoudeft.commun.evenement.GestionnaireEvenement;
import com.atoudeft.commun.net.Connexion;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


/**
 * Cette classe représente un gestionnaire d'événement d'un serveur. Lorsqu'un serveur reçoit un texte d'un client,
 * il crée un événement à partir du texte reçu et alerte ce gestionnaire qui réagit en gérant l'événement.
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-09-01
 */
public class GestionnaireEvenementServeur implements GestionnaireEvenement {
    private Serveur serveur;
    final double TAUX_INTERET = 0.05;

    /**
     * Construit un gestionnaire d'événements pour un serveur.
     *
     * @param serveur Serveur Le serveur pour lequel ce gestionnaire gère des événements
     */
    public GestionnaireEvenementServeur(Serveur serveur) {
        this.serveur = serveur;
    }

    /**
     * Méthode de gestion d'événements. Cette méthode contiendra le code qui gère les réponses obtenues d'un client.
     *
     * @param evenement L'événement à gérer.
     */
    @Override
    public void traiter(Evenement evenement) {
        Object source = evenement.getSource();
        ServeurBanque serveurBanque = (ServeurBanque) serveur;
        Banque banque;
        ConnexionBanque cnx;
        String msg, typeEvenement, argument, numCompteClient, nip;
        String[] t;

        if (source instanceof Connexion) {
            cnx = (ConnexionBanque) source;
            System.out.println("SERVEUR: Recu : " + evenement.getType() + " " + evenement.getArgument());
            typeEvenement = evenement.getType();
            cnx.setTempsDerniereOperation(System.currentTimeMillis());
            switch (typeEvenement) {
                /******************* COMMANDES GÉNÉRALES *******************/
                case "EXIT": //Ferme la connexion avec le client qui a envoyé "EXIT":
                    cnx.envoyer("END");
                    serveurBanque.enlever(cnx);
                    cnx.close();
                    break;
                case "LIST": //Envoie la liste des numéros de comptes-clients connectés :
                    cnx.envoyer("LIST " + serveurBanque.list());
                    break;
                /******************* COMMANDES DE GESTION DE COMPTES *******************/
                case "NOUVEAU": //Crée un nouveau compte-client :
                    if (cnx.getNumeroCompteClient() != null) {
                        cnx.envoyer("NOUVEAU NO deja connecte");
                        break;
                    }
                    argument = evenement.getArgument();
                    t = argument.split(":");
                    if (t.length < 2) {
                        cnx.envoyer("NOUVEAU NO");
                    } else {
                        numCompteClient = t[0];
                        nip = t[1];
                        banque = serveurBanque.getBanque();
                        if (banque.ajouter(numCompteClient, nip)) {
                            cnx.setNumeroCompteClient(numCompteClient);
                            cnx.setNumeroCompteActuel(banque.getNumeroCompteParDefaut(numCompteClient));
                            cnx.envoyer("NOUVEAU OK " + t[0] + " cree");
                        } else
                            cnx.envoyer("NOUVEAU NO " + t[0] + " existe");
                    }
                    break;
                /******************* Q3.1 - CAS OÙ LE CLIENT A DÉJÀ UN COMPTE-CLIENT *******************/
                case "CONNECT":  // Fait par Nancy Nguyen
                    argument = evenement.getArgument(); //Récupération des informations du client : Numéro de compte-client et nip
                    t = argument.split(":");
                    if (t.length < 2) { /* Si t < 2, il manque au moins une information, et on sort du switch */
                        cnx.envoyer("CONNECT NO");
                        break;
                    }
                    numCompteClient = t[0];  //Numéro de Compte-Client
                    nip = t[1];              //Nip envoyé par le client
                    banque = serveurBanque.getBanque();
                    if (banque.getCompte(numCompteClient) == null || cnx.getNumeroCompteClient() != null) {
                        cnx.envoyer("CONNECT NO"); //Le client est déjà connecté
                        break;
                    }
                    if ((banque.numeroEstValide(numCompteClient))) {
                        cnx.envoyer("CONNECT NO"); //Le client est déjà connecté
                    }
                    //Vérifier si le nip correspond au nip enregistré relié au compte-chèque
                    banque = serveurBanque.getBanque();
                    CompteClient compteClient2 = new CompteClient(numCompteClient, nip);
                    String nipEnregistre = compteClient2.getNip();
                    if (!nipEnregistre.equals(nip)) {
                        cnx.envoyer("CONNECT NO"); //Le nip enregistré est différent au nip entré
                        break;
                    }
                    //Inscrire le numéro du compte-client et son compte-chèque dans l'objet
                    cnx.setNumeroCompteClient(numCompteClient);
                    cnx.setNumeroCompteActuel(banque.getNumeroCompteParDefaut(numCompteClient));
                    cnx.envoyer("CONNECT OK");
                    break;
                /************************      Q4.2 ÉPARGNE       ******************************/
                case "EPARGNE":
                    // Fait par Nancy Nguyen
                    numCompteClient = cnx.getNumeroCompteClient();   // Récupération des information du clients
                    banque = serveurBanque.getBanque();
                    //2. Vérifier si le client est connecté au serveur
                    if (numCompteClient == null) {
                        cnx.envoyer("EPARGNE NO");
                        break;
                    }
                    //3.Vérifier si le client a déjà un compte-épargne
                    if (banque.getNumeroCompteParDefaut(numCompteClient) == null) {
                        cnx.envoyer("EPARGNE NO");
                        break;
                    }
                    // Générer un numéro unique pour le compte-épargne
                    String numCompteEpargne = "";
                    while (banque.getCompteClient(numCompteEpargne) != null && banque.numeroEstValide(numCompteEpargne)) {
                        numCompteEpargne = CompteBancaire.genereNouveauNumero();
                    }
                    CompteClient compteClient = banque.getCompte(numCompteClient);
                    CompteEpargne compteEpargne = (new CompteEpargne(numCompteEpargne, TypeCompte.EPARGNE, TAUX_INTERET));
                    compteClient.ajouter(compteEpargne);
                    cnx.envoyer("EPARGNE OK");
                    break;

                /************************      Q5.1 SELECT       ******************************/
                case "SELECT":
                    // Fait par Nancy Nguyen
                    CompteBancaire compteBancaire = null;   // Vérifier si le client est connecté
                    numCompteClient = cnx.getNumeroCompteClient();
                    if (numCompteClient == null) {
                        cnx.envoyer("SELECT NO"); // le client n'est pas connecté
                        break;
                    }
                    //3. Recupération de l'argument de la commande (chèque ou épargne)
                    argument = evenement.getArgument();
                    banque = serveurBanque.getBanque();
                    compteClient = banque.getCompte(numCompteClient);
                    switch (argument) {
                        case "CHEQUE": {
                            compteBancaire = compteClient.getCompteBancaire(TypeCompte.CHEQUE);
                            break;
                        }

                        case "EPARGNE": {
                            compteBancaire = compteClient.getCompteBancaire(TypeCompte.EPARGNE);
                            break;
                        }
                    }
                    if (compteBancaire != null) {
                        cnx.setNumeroCompteActuel(numCompteClient); // Si le compte bancaire n'existe pas !
                        cnx.envoyer("SELECT OK"); // le client n'est pas connecté
                        break;
                    } else {
                        cnx.envoyer("SELECT NO"); // le client n'est pas connecté
                        break;
                    }
                    /************************      Q6.1 DEPOT      ******************************/
                case "DEPOT":
                    argument = evenement.getArgument();   // Récupération des informations du client
                    t = argument.split(" ");
                    if (t.length < 1) { // Si t < 1, il manque une information, et on sort du switch
                        cnx.envoyer("DEPOT NO");
                        break;
                    }
                    banque = serveurBanque.getBanque();
                    numCompteClient = cnx.getNumeroCompteActuel();
                    double montant = Double.parseDouble(t[0]);
                    //2. Vérifier si le client est connecté au serveur
                    if (numCompteClient == null) {
                        cnx.envoyer("DEPOT NO");
                        break;
                    }
                    if (banque.deposer(montant, numCompteClient)) {
                        cnx.envoyer("DEPOT OK");
                    } else {
                        cnx.envoyer("DEPOT NO");
                    }
                    break;
                /************************      Q6.2 RETRAIT      ******************************/
                case "RETRAIT":
                    argument = evenement.getArgument();  // Récupération des informations du client
                    t = argument.split(" ");
                    if (t.length < 1) { //Vérifie qu'il y a bien un montant
                        cnx.envoyer("RETRAIT NO"); //Il n'y a pas de montant à retirer
                        break;
                    }
                    //Déclaration des variables
                    banque = serveurBanque.getBanque();
                    numCompteClient = cnx.getNumeroCompteClient();
                    montant = Double.parseDouble(t[0]);

                    //2. Vérifier si le client est connecté
                    if (numCompteClient == null) {
                        cnx.envoyer("RETRAIT NO");
                        break;
                    }
                    if (banque.retirer(montant, numCompteClient)) {
                        cnx.envoyer("RETRAIT OK");
                    } else {
                        cnx.envoyer("RETRAIT NO");
                    }

                    break;
                /************************      Q6.3 FACTURE      ******************************/
                case "FACTURE":
                    argument = evenement.getArgument(); // Récupération des informations du client
                    t = argument.split(" ", 3);
                    if (t.length < 3) { //Vérifie qu'il y a bien ces éléments; montant NUMFACT Description
                        cnx.envoyer("FACTURE NO");
                        break;
                    }
                    //Déclaration des variables
                    montant = Double.parseDouble(t[0]);
                    String numFact = t[1];
                    String description = t[2];
                    banque = serveurBanque.getBanque();
                    numCompteClient = cnx.getNumeroCompteClient();
                    //2. Vérifier si le client est connecté
                    if (numCompteClient == null || numFact == null || description == null) {
                        cnx.envoyer("FACTURE NO");
                        break;
                    }
                    if (banque.payerFacture(montant, numCompteClient, numFact, description)) {
                        cnx.envoyer("FACTURE OK");
                    } else {
                        cnx.envoyer("FACTURE NO");
                    }
                    break;
                /************************      Q6.4 TRANSFER      ******************************/
                case "TRANSFER":
                    // Fait par Nancy Nguyen
                    argument = evenement.getArgument();
                    t = argument.split(" ", 3);
                    if (t.length < 3) { //Vérifie qu'il y a bien ces éléments; montant cpt1 cpt2
                        cnx.envoyer("TRANSFER NO");
                        break;
                    }
                    montant = Double.parseDouble(t[0]);  //Déclaration des variables
                    numCompteClient = t[1];
                    String compteDestinataire = t[2];
                    banque = serveurBanque.getBanque();
                    if (!(banque.numeroEstValide(numCompteClient)) || !(banque.numeroEstValide(compteDestinataire))
                            || numCompteClient == null || compteDestinataire == null) {
                        cnx.envoyer("TRANSFER NO");
                        break;
                    }
                    if (banque.transferer(montant, numCompteClient, compteDestinataire)) {
                        cnx.envoyer("TRANSFER OK");
                    } else {
                        cnx.envoyer("TRANSFER NO");
                    }
                    break;
                /************************      Q7.7 HIST      ******************************/
                case "HIST":
                    numCompteClient = cnx.getNumeroCompteClient();
                    if (numCompteClient == null) {
                        cnx.envoyer("HIST NO");
                        break;
                    }
                    banque = serveurBanque.getBanque();
                    compteClient = banque.getCompte(numCompteClient);
                    compteBancaire = compteClient.getCompte(numCompteClient);
                    ArrayList<Operation> historique = compteClient.getCompte(numCompteClient).afficherHistoriqueOperation();
                    StringBuilder historiqueMessage = new StringBuilder("HIST \n");
                    for (Operation operation : historique) {
                        historiqueMessage.append((operation.toString())).append("\n");
                    }
                    cnx.envoyer(historiqueMessage.toString());
                    System.out.println(compteBancaire.afficherHistoriqueOperation());
                    break;
                /******************* TRAITEMENT PAR DÉFAUT *******************/
                default: //Renvoyer le texte recu convertit en majuscules :
                    msg = (evenement.getType() + " " + evenement.getArgument()).toUpperCase();
                    cnx.envoyer(msg);
            }
        }
    }
}