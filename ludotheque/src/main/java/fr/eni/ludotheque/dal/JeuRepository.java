package fr.eni.ludotheque.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.ludotheque.bo.Jeu;

public interface JeuRepository extends JpaRepository<Jeu, Integer>{

}
