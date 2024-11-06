package com.atoudeft.serveur;

import com.atoudeft.banque.*;
        import com.atoudeft.banque.serveur.ConnexionBanque;
import com.atoudeft.banque.serveur.ServeurBanque;
import com.atoudeft.commun.evenement.Evenement;
import com.atoudeft.commun.evenement.GestionnaireEvenement;
import com.atoudeft.commun.net.Connexion;

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
                // Fait par Nancy Nguyen
                case "CONNECT":
                    /*
                     * Stratégie:
                     *   1. Récuperer les informations du client : numéro de compte et le pin
                     *   2. Vérifier s'il y a un autre client déjà connecté sur ce compte
                     *   3. Vérifier si le nip est correct
                     *       3.1. Regarder si le compte existe
                     *       3.2. Regarder si le nip enregistré correspond au NIP entré par l'utilisateur
                     *       3.3. Comparer les deux nip
                     *   4. Inscrire le numéro du compte-client et son compte-chèque dans l'objet
                     *      connexionBanque du client
                     * */

                    //Récupération des informations du client : Numéro de compte-client et nip
                    argument = evenement.getArgument();
                    t = argument.split(":");
                    if (t.length < 2) { /* Si t < 2, il manque au moins une information, et on sort du switch */
                        cnx.envoyer("CONNECT NO");
                        break;
                    }

                    numCompteClient = t[0];  //Numéro de Compte-Client
                    nip = t[1];              //Nip envoyé par le client

                    //Vérifier s'il y a un autre client déjà connecté sur ce compte
                    if (cnx.getNumeroCompteClient() != null) {
                        System.out.println("Test NON1"); //TODO ENLEVER LE TEST
                        cnx.envoyer("CONNECT NO"); //Le client est déjà connecté
                        break;
                    }

                    //Vérifier si le nip correspond au nip enregistré relié au compte-chèque
                    banque = serveurBanque.getBanque();
                    CompteClient compteClient2 = new CompteClient(numCompteClient, nip);

                    String nipEnregistré = compteClient2.getNip();
                    if (!nipEnregistré.equals(nip)) {
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
                    /* Stratégie:
                     *  1. Recueillir les informations du client (FAIT)
                     *  2. Vérifier si le client est connecté (FAIT)
                     *  3. Vérifier si le client possède un compte-épargne
                     *  4. Générer un numéro unique pour le compte-épargne
                     *  5. Créer un compte-épargne
                     */

                    // 1.Récupération des information du clients
                    numCompteClient = cnx.getNumeroCompteClient();
                    banque = serveurBanque.getBanque();

                    //2. Vérifier si le client est connecté au serveur
                    if (numCompteClient == null) {
                        cnx.envoyer("EPARGNE NO");
                        System.out.println("Test NON1"); //TODO ENLEVER LE TEST
                        break;
                    }

                    //3.Vérifier si le client a déjà un compte-épargne
                    if (banque.getNumeroCompteParDefaut(numCompteClient) == null) {
                        cnx.envoyer("EPARGNE NO");
                        System.out.println("Test NON2"); //TODO ENLEVER LE TEST
                        break;
                    }

                    // Générer un numéro unique pour le compte-épargne
                    String numCompteEpargne = "";
                    while (banque.getCompteClient(numCompteEpargne) != null) {
                        numCompteEpargne = CompteBancaire.genereNouveauNumero(); //À vérifier
                    }
                    CompteClient compteClient = banque.getCompte(numCompteClient);
                    CompteEpargne compteEpargne = (new CompteEpargne(numCompteEpargne, TypeCompte.EPARGNE, TAUX_INTERET));
                    compteClient.ajouter(compteEpargne);
                    cnx.envoyer("EPARGNE OK");
                    break;

                /************************      Q5.1 SELECT       ******************************/
                case "SELECT":
                    // Fait par Nancy Nguyen
                    /* Stratégie:
                     *  1. Vérifier si le client est connecté (OK)
                     *  2. Récupérer l'argument de la commande (chèque ou épargne) (OK)
                     *  3. Demander à la banque de donner le numéro de banque du client
                     *     et Stocker le numéro dans l'objet ConnexionBanque du client
                     *      opération réussie => SELECT OK
                     *      opération non réussie => SELECT NO
                     */
                    CompteBancaire compteBancaire = null;
                    //1. Vérifier si le client est connecté
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
                        case "cheque": {
                            compteBancaire = compteClient.getCompteBancaire(TypeCompte.CHEQUE);
                            cnx.envoyer("SELECT OK");
                            break;
                        }

                        case "epargne": {
                            compteBancaire = compteClient.getCompteBancaire(TypeCompte.EPARGNE);
                            cnx.envoyer("SELECT OK");
                            break;
                        }
                    }
                    if (compteBancaire != null) {
                        cnx.setNumeroCompteActuel(numCompteClient); // Si le compte bancaire n'existe pas !
                        cnx.envoyer("SELECT NO"); // le client n'est pas connecté
                    }
                    break;


                    /************************      Q6.1 DEPOT      ******************************/
                case "DEPOT":
                    // 1. Récupération des informations du client (Comme dans le case "NOUVEAU")
                    argument = evenement.getArgument();
                    t = argument.split(" ");
                    if (t.length < 1) { // Si t == 1, il manque une information, et on sort du switch
                        cnx.envoyer("DEPOT NO");
                        System.out.println("Test NON1"); //TODO ENLEVER LE TEST
                        break;
                    }
                    banque = serveurBanque.getBanque();
                    numCompteClient = cnx.getNumeroCompteClient();
                    double montant = Double.parseDouble(t[0]);

                    //2. Vérifier si le client est connecté au serveur
                    if (numCompteClient == null) {
                        cnx.envoyer("DEPOT NO");
                        System.out.println("Test NON2"); //TODO ENLEVER LE TEST Fonctionne et tester
                        break;
                    }
                    //3. Effectuer le dépôt - À tester

                    if (banque.deposer(montant, numCompteClient)) {
                        cnx.envoyer("DEPOT OK");
                    } else {
                        cnx.envoyer("DEPOT NO");
                    }
                    break;
                /************************      Q6.2 RETRAIT      ******************************/
                case "RETRAIT":
                    /*
                     * Stratégie:
                     *  1. Récupérer les informations du client
                     *  2. Vérifier si le client est connecté au serveur
                     *  3. Effectuer le retait
                     */
                    //1. Récupération des informations du client
                    argument = evenement.getArgument();
                    t = argument.split(" ");
                    if (t.length < 1) { //Vérifie qu'il y a bien un montant
                        cnx.envoyer("RETRAIT NO"); //Il n'y a pas de montant à retirer
                        System.out.println("Test NON1"); //TODO ENLEVER LE TEST
                        break;
                    }

                    //Déclaration des variables
                    banque = serveurBanque.getBanque();
                    numCompteClient = cnx.getNumeroCompteClient();
                    montant = Double.parseDouble(t[0]);

                    //2. Vérifier si le client est connecté
                    if (cnx.getNumeroCompteClient() != null) {
                        cnx.envoyer("RETRAIT NO");
                        System.out.println("Test NON2"); //TODO ENLEVER LE TEST
                        break;
                    }

                    //Retirer le montant demandé
                    if(banque.retirer(montant, numCompteClient)) {
                        cnx.envoyer("RETRAIT OK");
                    } else {
                        cnx.envoyer("RETRAIT NO");
                    }

                    break;
                /************************      Q6.3 FACTURE      ******************************/
                case "FACTURE":
                    /*
                     * Stratégie:
                     *  1. Récupérer les informations du client
                     *  2. Vérifier si le client est connecté au serveur
                     *  3. PAYER LA FACTURE
                     */

                    //1. Récupération des informations du client
                    argument = evenement.getArgument();
                    t = argument.split(" ", 3);
                    if (t.length < 3) { //Vérifie qu'il y a bien ces éléments; montant NUMFACT Description
                        cnx.envoyer("FACTURE NO");
                        System.out.println("Test NON1"); //TODO ENLEVER LE TEST
                        break;
                    }

                    //Déclaration des variables
                    montant = Double.parseDouble(t[0]);
                    String numFact = t[1];
                    String description = t[2];
                    banque = serveurBanque.getBanque();
                    numCompteClient = cnx.getNumeroCompteClient();

                    //2. Vérifier si le client est connecté
                    if (cnx.getNumeroCompteClient() != null) {
                        cnx.envoyer("FACTURE NO");
                        System.out.println("Test NON2"); //TODO ENLEVER LE TEST
                        break;
                    }

                    //Paiement de la facture
                    if(banque.payerFacture(montant, numCompteClient, numFact, description)){
                        cnx.envoyer("FACTURE OK");
                    } else {
                        cnx.envoyer("FACTURE NO");
                    }

                    break;
                /******************* TRAITEMENT PAR DÉFAUT *******************/

                default: //Renvoyer le texte recu convertit en majuscules :
                    msg = (evenement.getType() + " " + evenement.getArgument()).toUpperCase();
                    cnx.envoyer(msg);
            }
        }
    }
}