package fr.eni.ludotheque.bll.service;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;

import java.util.List;

public interface ClientService {

    // S2008
    Client ajouterClient(Client client);

    // S2009
    List<Client> trouverClientsDontNomCommencePar(String prefix);

    // S2010
    Client modifierClientComplet(Long clientId, Client nouveauClient);

    // S2011
    Client modifierAdresseClient(Long clientId, Adresse nouvelleAdresse);
}
