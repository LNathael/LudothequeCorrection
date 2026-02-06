package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JeuRepositoryTest {

	@Autowired
	private JeuRepository jeuRepository;
	
	@Test
	@DisplayName("test création jeu et liens vers genres CAS POSITIF")
	//@Transactional
	public void testCreationJeu() {
		//Arrange
		Jeu jeu = new Jeu("SkyJo", "refSkyJo", 5.6f );
		jeu.setAgeMin(8);
		jeu.setDescription("Descr skyjo");
		jeu.addGenre(new Genre(1, ""));
		jeu.addGenre(new Genre(2, ""));
		
		//Act
		Jeu jeuActual = jeuRepository.save(jeu);
		
		//Assert
		Jeu jeuBD = jeuRepository.findById(jeuActual.getNoJeu()).orElse(null);
		assertThat(jeuBD).isNotNull();
		assertThat(jeuBD.getNoJeu()).isNotNull();
		assertThat(jeuBD.getTitre()).isEqualTo(jeu.getTitre());
		assertThat(jeuBD.getDescription()).isEqualTo(jeu.getDescription());
		assertThat(jeuBD.getAgeMin()).isEqualTo(jeu.getAgeMin());
		assertThat(jeuBD.getGenres()).hasSize(2);
		assertThat(jeuBD.getReference()).isEqualTo(jeu.getReference());
		
	}
	
}
