package fr.eni.ludotheque.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.ludotheque.bo.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer>{

    List<Client> findByNomStartingWithIgnoreCase(String p);
}
