package com.dolts.sgcback.controllers;

import com.dolts.sgcback.models.CemiterioModel;
import com.dolts.sgcback.repositories.CemiterioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cemiterios")
public class CemiterioController {
    private final CemiterioRepository cemiterioRepository;

    public CemiterioController(CemiterioRepository cemiterioRepository) {
        this.cemiterioRepository = cemiterioRepository;
    }

    @GetMapping
    public ResponseEntity<List<CemiterioModel>> todosCemiterios() {
        return ResponseEntity.status(HttpStatus.OK).body(cemiterioRepository.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> encontraCemiterioPorId(@PathVariable int id) {
        Optional<CemiterioModel> cemiterio = cemiterioRepository.findById(id);
        if (!cemiterio.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unidade não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cemiterioRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<CemiterioModel> novoCemiterio(@RequestBody CemiterioModel cemiterioModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cemiterioRepository.save(cemiterioModel));
    }

}
