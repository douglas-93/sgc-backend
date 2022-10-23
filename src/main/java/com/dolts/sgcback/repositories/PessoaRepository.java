package com.dolts.sgcback.repositories;

import com.dolts.sgcback.models.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Integer> {
}
