package com.up.KGLSol.service;

import com.up.KGLSol.entity.Client;
import com.up.KGLSol.entity.Reservation;
import com.up.KGLSol.repositories.ClientRepository;
import com.up.KGLSol.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Long id, Client client) {
        Client clientToBeUpdated = clientRepository.findById(id).get();

        System.out.println(client);
        System.out.println(clientToBeUpdated);

        clientToBeUpdated.setName(client.getName());

        System.out.println(clientToBeUpdated);
        System.out.println(client);
        System.out.println(clientRepository.findById(id).get());
        return clientToBeUpdated;

    }

    @Override
    public void deleteById(Long id) {
        if (clientRepository.findById(id).get().getReservations().size() > 0){
            reservationRepository.deleteAll(clientRepository.findById(id).get().getReservations());
        }
        clientRepository.deleteById(id);
    }
}
