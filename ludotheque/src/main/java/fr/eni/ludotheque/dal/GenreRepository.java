package fr.eni.ludotheque.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.ludotheque.bo.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>{

}
