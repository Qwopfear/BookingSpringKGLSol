package com.up.KGLSol.service;

import com.up.KGLSol.entity.Client;

import java.util.Optional;


public interface ClientService {

    Iterable<Client> findAll();

    Optional<Client> findById(Long id);

    Client save(Client client);

    Client update(Long id, Client client);

    void deleteById(Long id);
}
