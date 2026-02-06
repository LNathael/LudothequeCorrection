package fr.eni.ludotheque.bll.service.impl;

import fr.eni.ludotheque.bll.exception.GenreNotFoundException;
import fr.eni.ludotheque.bll.service.JeuService;
import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.dal.GenreRepository;
import fr.eni.ludotheque.dal.JeuRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.HashSet;
import java.util.Set;

@Service
@Validated
public class JeuServiceImpl implements JeuService {

    private final JeuRepository jeuRepository;
    private final GenreRepository genreRepository;

    public JeuServiceImpl(JeuRepository jeuRepository, GenreRepository genreRepository) {
        this.jeuRepository = jeuRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional
    public Jeu ajouterJeuAvecGenres(@Valid Jeu jeu, Set<Long> genreIds) {
        Set<Long> ids = (genreIds == null) ? Set.of() : genreIds;

        Set<Genre> genres = new HashSet<>();
        for (Long id : ids) {
            Genre g = genreRepository.findById(Math.toIntExact(id))
                    .orElseThrow(() -> new GenreNotFoundException(id));
            genres.add(g);
        }

        jeu.setGenres(genres);
        return jeuRepository.save(jeu);
    }
}
