package fr.eni.ludotheque.bo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClientTest {

    @Test
    @DisplayName("test de creation client - cas droit")
    //Test JUnit
    public void testCreationClient() {
        Adresse adresse = new Adresse("rue des Cormorans", "44800", "Saint Herblain");
        Client client = new Client("Stiller", "Ben", "ben.stiller@eni.fr", adresse);
        client.setNoTelephone("0101010101");
        Assertions.assertNotNull(client);
        Assertions.assertEquals("0101010101", client.getNoTelephone());
        Assertions.assertEquals("Stiller", client.getNom());
        Assertions.assertEquals("Ben", client.getPrenom());
        Assertions.assertEquals("ben.stiller@eni.fr", client.getEmail());
        Assertions.assertEquals(adresse, client.getAdresse());
    }

}
