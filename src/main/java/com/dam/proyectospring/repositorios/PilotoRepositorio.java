package com.dam.proyectospring.repositorios;

import com.dam.proyectospring.modelos.Piloto;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;



import org.springframework.stereotype.Repository;


@Repository
public interface PilotoRepositorio extends MongoRepository<Piloto, String> {
    Optional<Piloto> findPilotoBy_id(String id);
}