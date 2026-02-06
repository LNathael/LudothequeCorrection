package fr.eni.ludotheque.bll.service;

import fr.eni.ludotheque.bo.Jeu;

import java.util.Set;

public interface JeuService {
    Jeu ajouterJeuAvecGenres(Jeu jeu, Set<Long> genreIds);
}
