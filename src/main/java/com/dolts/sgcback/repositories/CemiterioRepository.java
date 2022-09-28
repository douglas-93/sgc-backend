package com.dolts.sgcback.repositories;

import com.dolts.sgcback.models.CemiterioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CemiterioRepository extends JpaRepository<CemiterioModel, Integer> {
}
