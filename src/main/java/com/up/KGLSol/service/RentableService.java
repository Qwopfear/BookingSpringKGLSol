package com.up.KGLSol.service;

import com.up.KGLSol.entity.Rentable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RentableService {
    Iterable<Rentable> findAll();

    Optional<Rentable> findById(Long id);

    Rentable save(Rentable rentable);

    void deleteById(Long id);
}
