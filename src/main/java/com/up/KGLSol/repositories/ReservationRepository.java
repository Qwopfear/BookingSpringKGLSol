package com.up.KGLSol.repositories;

import com.up.KGLSol.entity.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository
        extends CrudRepository<Reservation,Long> {
}
