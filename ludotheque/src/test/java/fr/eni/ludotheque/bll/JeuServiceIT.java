package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bll.service.JeuService;
import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.dal.GenreRepository;
import fr.eni.ludotheque.dal.JeuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JeuServiceIT {

    @Autowired
    private JeuService jeuService;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private JeuRepository jeuRepository;

    @Test
    void ajouterJeuAvecGenres_testPositif() {
        // Arrange
        Genre g1 = genreRepository.save(Genre.builder().libelle("Stratégie").build());
        Genre g2 = genreRepository.save(Genre.builder().libelle("Coopératif").build());

        Jeu jeu = Jeu.builder()
                .titre("Pandemic")
                .description("Sauver le monde ensemble")
                .build();

        // Act
        Jeu saved = jeuService.ajouterJeuAvecGenres(jeu, Set.of(g1.getId(), g2.getId()));

        // Assert
        Jeu found = jeuRepository.findById(saved.getId()).orElseThrow();
        assertThat(found.getGenres()).hasSize(2);
        assertThat(found.getGenres()).extracting(Genre::getLibelle)
                .containsExactlyInAnyOrder("Stratégie", "Coopératif");
    }
}
