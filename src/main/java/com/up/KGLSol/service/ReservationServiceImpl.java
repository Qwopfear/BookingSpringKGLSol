package com.up.KGLSol.service;

import com.up.KGLSol.entity.Client;
import com.up.KGLSol.entity.Rentable;
import com.up.KGLSol.entity.Reservation;
import com.up.KGLSol.exception.ReservationIllegalException;
import com.up.KGLSol.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    public Iterable<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation save(Reservation reservation) throws ReservationIllegalException, ParseException {

        return reservationRepository.save(reservation);
    }




    @Override
    public Reservation save(Client client, Rentable rentable, String from, String to, Reservation reservation) throws ParseException, ReservationIllegalException {
        reservation.setClient(client);
        reservation.setRentable(rentable);
        reservation.setRentStart(new SimpleDateFormat("yyyy-MM-dd").parse(from));
        reservation.setRentEnd(new SimpleDateFormat("yyyy-MM-dd").parse(to));
        reservation.checkReservationAllow(rentable.getReservations());
        return reservationRepository.save(reservation);
    }
}
