package com.up.KGLSol.service;

import com.up.KGLSol.entity.Rentable;
import com.up.KGLSol.repositories.RentableRepository;
import com.up.KGLSol.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentableServiceImpl
        implements RentableService{

    @Autowired
    private RentableRepository rentableRepository;


    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Iterable<Rentable> findAll() {
        return rentableRepository.findAll();
    }

    @Override
    public Optional<Rentable> findById(Long id) {
        return rentableRepository.findById(id);
    }

    @Override
    public Rentable save(Rentable rentable) {
        return rentableRepository.save(rentable);
    }

    @Override
    public void deleteById(Long id) {
        if (rentableRepository.findById(id).get().getReservations().size() > 0){
            reservationRepository.deleteAll(rentableRepository.findById(id).get().getReservations());
        }
        rentableRepository.deleteById(id);
    }
}
