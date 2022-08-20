package com.up.KGLSol.service;

import com.up.KGLSol.entity.Client;
import com.up.KGLSol.entity.Rentable;
import com.up.KGLSol.entity.Reservation;
import com.up.KGLSol.exception.ReservationIllegalException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;

@Service
public interface ReservationService {
    Iterable<Reservation> findAll();

    Optional<Reservation> findById(Long id);

    Reservation save(Reservation reservation) throws ReservationIllegalException, ParseException;

    Reservation save(Client client, Rentable rentable, String from, String to, Reservation reservation) throws ParseException, ReservationIllegalException;
}
