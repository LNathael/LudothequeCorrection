package fr.eni.ludotheque.bll.service.impl;

import fr.eni.ludotheque.bll.exception.ClientNotFoundException;
import fr.eni.ludotheque.bll.service.ClientService;
import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // S2008
    @Override
    @Transactional
    public Client ajouterClient(@Valid Client client) {
        return clientRepository.save(client);
    }

    // S2009
    @Override
    @Transactional(readOnly = true)
    public List<Client> trouverClientsDontNomCommencePar(String prefix) {
        String p = (prefix == null) ? "" : prefix.trim();
        return clientRepository.findByNomStartingWithIgnoreCase(p);
    }

    // S2010
    @Override
    @Transactional
    public Client modifierClientComplet(Long clientId, @Valid Client nouveauClient) {
        Client existant = clientRepository.findById(Math.toIntExact(clientId))
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        // Champs client
        existant.setNom(nouveauClient.getNom());
        existant.setPrenom(nouveauClient.getPrenom());
        existant.setEmail(nouveauClient.getEmail());
//        existant.setTelephone(nouveauClient.getTelephone());

        // Champs adresse
        if (existant.getAdresse() == null) {
            existant.setAdresse(nouveauClient.getAdresse());
        } else {
            Adresse a = existant.getAdresse();
            Adresse na = nouveauClient.getAdresse();

            a.setRue(na.getRue());
//            a.setComplement(na.getComplement());
            a.setCodePostal(na.getCodePostal());
            a.setVille(na.getVille());
//            a.setPays(na.getPays());
        }

        return clientRepository.save(existant);
    }

    // S2011
    @Override
    @Transactional
    public Client modifierAdresseClient(Long clientId, @Valid Adresse nouvelleAdresse) {
        Client existant = clientRepository.findById(Math.toIntExact(clientId))
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        if (existant.getAdresse() == null) {
            existant.setAdresse(nouvelleAdresse);
        } else {
            Adresse a = existant.getAdresse();
            a.setRue(nouvelleAdresse.getRue());
//            a.setComplement(nouvelleAdresse.getComplement());
            a.setCodePostal(nouvelleAdresse.getCodePostal());
            a.setVille(nouvelleAdresse.getVille());
//            a.setPays(nouvelleAdresse.getPays());
        }

        return clientRepository.save(existant);
    }
}
