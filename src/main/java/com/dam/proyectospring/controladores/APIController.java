package com.dam.proyectospring.controladores;

import com.dam.proyectospring.modelos.Piloto;
import com.dam.proyectospring.servicios.PilotoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class APIController {
    @Autowired
    private PilotoServicio pilotoServicio;

    // GET de todos los pilotos, devuelve un JSON con todos los pilotos
    @GetMapping(value = "/api/pilotos")
    public ResponseEntity<List<Piloto>> getProduct()
    {
        List<Piloto> pilotos = pilotoServicio.findAllPilotos();
        return new ResponseEntity<>(pilotos, HttpStatus.OK);
    }

    // GET de un piloto, devuelve un JSON del piloto
    @GetMapping("/piloto/{id}")
    public ResponseEntity<Piloto> getProduct(@PathVariable String id) {
        Piloto piloto;
        //  Buscar el piloto con identificador id

        // Comprueba si el valor existe
        if (this.pilotoServicio.getPilotoById(id).isPresent()) {
            // Si existe, lo recoge
            piloto = this.pilotoServicio.getPilotoById(id).get();
        } else {
            // Si no existe, devuelve un error 404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Devuelve el piloto
        return new ResponseEntity<>(piloto, HttpStatus.OK);
    }

    // POST de un piloto, crea un piloto
    @PostMapping("/pilotos")
    public ResponseEntity<Piloto> addProduct(@RequestBody Piloto piloto) {
        // guardar el piloto en la BB.DD.
        this.pilotoServicio.savePiloto(piloto);
        // Devuelve el piloto
        return ResponseEntity.ok(piloto);
    }

    // PUT de un piloto, actualiza un piloto
    @PutMapping("/pilotos/{id}")
    public ResponseEntity<Piloto> update(@RequestBody Piloto piloto) {
        // actualiza el piloto en la BB.DD.
        this.pilotoServicio.savePiloto(piloto);
        // Devuelve el piloto
        return ResponseEntity.ok(piloto);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Piloto> deleteProduct(@PathVariable String id) {
        // Borrar el piloto con identificador id
        this.pilotoServicio.deletePilotoById(id);
        // Devuelve un 204
        return ResponseEntity.noContent().build();
    }


}
