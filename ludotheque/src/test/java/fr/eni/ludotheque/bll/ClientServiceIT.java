package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bll.exception.ClientNotFoundException;
import fr.eni.ludotheque.bll.service.ClientService;
import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ClientServiceIT {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    // S2008
    @Test
    void ajouterClient_testPositif() {
        // Arrange
        Client client = Client.builder()
                .nom("Le Bihan")
                .prenom("Nathaël")
                .email("nathael@test.com")
                .telephone("0600000000")
                .adresse(Adresse.builder()
                        .rue("10 rue des Lilas")
                        .complement("Bat A")
                        .codePostal("75000")
                        .ville("Paris")
                        .pays("France")
                        .build())
                .build();

        // Act
        Client saved = clientService.ajouterClient(client);

        // Assert
        assertThat(saved.getId()).isNotNull();
        Client found = clientRepository.findById(saved.getId()).orElseThrow();
        assertThat(found.getEmail()).isEqualTo("nathael@test.com");
        assertThat(found.getAdresse().getVille()).isEqualTo("Paris");
    }

    // S2009
    @Test
    void trouverClientsDontNomCommencePar() {
        // Arrange
        clientService.ajouterClient(clientAvecNom("Martin", "a@test.com"));
        clientService.ajouterClient(clientAvecNom("Martinez", "b@test.com"));
        clientService.ajouterClient(clientAvecNom("Dupont", "c@test.com"));

        // Act
        List<Client> res = clientService.trouverClientsDontNomCommencePar("Mar");

        // Assert
        assertThat(res).hasSize(2);
        assertThat(res).extracting(Client::getNom)
                .containsExactlyInAnyOrder("Martin", "Martinez");
    }

    // S2010 - cas positif
    @Test
    void modifierClientComplet_casPositif() {
        // Arrange
        Client saved = clientService.ajouterClient(clientAvecNom("Dupont", "dupont@test.com"));

        Client nouveau = Client.builder()
                .nom("Durand")
                .prenom("Yanis")
                .email("yanis@test.com")
                .telephone("0700000000")
                .adresse(Adresse.builder()
                        .rue("99 avenue Java")
                        .complement(null)
                        .codePostal("13000")
                        .ville("Marseille")
                        .pays("France")
                        .build())
                .build();

        // Act
        Client updated = clientService.modifierClientComplet(saved.getId(), nouveau);

        // Assert
        assertThat(updated.getNom()).isEqualTo("Durand");
        Client reloaded = clientRepository.findById(saved.getId()).orElseThrow();
        assertThat(reloaded.getEmail()).isEqualTo("yanis@test.com");
        assertThat(reloaded.getAdresse().getRue()).isEqualTo("99 avenue Java");
        assertThat(reloaded.getAdresse().getVille()).isEqualTo("Marseille");
    }

    // S2010 - client non trouvé
    @Test
    void modifierClientComplet_clientNonTrouve() {
        // Arrange
        Client nouveau = clientAvecNom("Durand", "durand@test.com");

        // Act + Assert
        assertThrows(ClientNotFoundException.class,
                () -> clientService.modifierClientComplet(99999L, nouveau));
    }

    // S2011
    @Test
    void modifierAdresseClient_metAJourChampsAdresse() {
        // Arrange
        Client saved = clientService.ajouterClient(clientAvecNom("Dupont", "x@test.com"));

        Adresse newAdr = Adresse.builder()
                .rue("1 rue Update")
                .complement("Etage 2")
                .codePostal("44000")
                .ville("Nantes")
                .pays("France")
                .build();

        // Act
        clientService.modifierAdresseClient(saved.getId(), newAdr);

        // Assert
        Client reloaded = clientRepository.findById(saved.getId()).orElseThrow();
        assertThat(reloaded.getNom()).isEqualTo("Dupont"); // client inchangé
        assertThat(reloaded.getAdresse().getRue()).isEqualTo("1 rue Update");
        assertThat(reloaded.getAdresse().getCodePostal()).isEqualTo("44000");
        assertThat(reloaded.getAdresse().getVille()).isEqualTo("Nantes");
    }

    private Client clientAvecNom(String nom, String email) {
        return Client.builder()
                .nom(nom)
                .prenom("Test")
                .email(email)
                .telephone("0600000000")
                .adresse(Adresse.builder()
                        .rue("rue test")
                        .complement(null)
                        .codePostal("00000")
                        .ville("VilleTest")
                        .pays("France")
                        .build())
                .build();
    }
}
