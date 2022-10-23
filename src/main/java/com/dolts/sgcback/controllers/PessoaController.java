package com.dolts.sgcback.controllers;

import com.dolts.sgcback.models.PessoaModel;
import com.dolts.sgcback.repositories.PessoaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaRepository pessoaRepository;

    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @GetMapping
    public ResponseEntity<List<PessoaModel>> todasPessoas() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> encontraPessoaPorId(@PathVariable int id) {
        Optional<PessoaModel> pessoa = pessoaRepository.findById(id);
        if (pessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<PessoaModel> novaPessoa(@RequestBody PessoaModel pessoaModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRepository.save(pessoaModel));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PessoaModel> editaPessoa(@PathVariable int id, @RequestBody PessoaModel pessoaModel) {
        return pessoaRepository.findById(id).map(
                data -> {
                    data.setNome(pessoaModel.getNome());
                    data.setCpf(pessoaModel.getCpf());
                    data.setSepultura(pessoaModel.getSepultura());
                    data.setFalecimento(pessoaModel.getFalecimento());
                    data.setSepultamento(pessoaModel.getSepultamento());
                    PessoaModel atualizado = pessoaRepository.save(data);
                    return ResponseEntity.status(HttpStatus.OK).body(atualizado);
                }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity deletaPessoa(@PathVariable int id) {
        return pessoaRepository.findById(id)
                .map(record -> {
                    pessoaRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.OK).build();
                }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
