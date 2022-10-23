package com.dolts.sgcback.controllers;

import com.dolts.sgcback.models.SepulturaModel;
import com.dolts.sgcback.repositories.SepulturaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/sepulturas")
public class SepulturaController {
    private final SepulturaRepository sepulturaRepository;

    public SepulturaController(SepulturaRepository sepulturaRepository) {
        this.sepulturaRepository = sepulturaRepository;
    }

    @GetMapping
    public ResponseEntity<List<SepulturaModel>> todasSepulturas() {
        return ResponseEntity.status(HttpStatus.OK).body(sepulturaRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> encontraSepulturaPorId(@PathVariable int id) {
        Optional<SepulturaModel> sepultura = sepulturaRepository.findById(id);
        if (sepultura.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sepultura não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(sepulturaRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<SepulturaModel> novaSepultura(@RequestBody SepulturaModel sepulturaModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sepulturaRepository.save(sepulturaModel));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<SepulturaModel> editaSepultura(@PathVariable int id, @RequestBody SepulturaModel sepulturaModel) {
        return sepulturaRepository.findById(id).map(
                data -> {
                    data.setLocal(sepulturaModel.getLocal());
                    data.setCemiterio(sepulturaModel.getCemiterio());
                    SepulturaModel atualizado = sepulturaRepository.save(data);
                    return ResponseEntity.status(HttpStatus.OK).body(atualizado);
                }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity deletaSepultura(@PathVariable int id) {
        return sepulturaRepository.findById(id)
                .map(record -> {
                    sepulturaRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.OK).build();
                }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
