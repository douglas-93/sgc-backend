package com.dolts.sgcback.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "SGCCEMITERIO")
public class CemiterioModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String nome;
    @Column
    private String endereco;
    @Column
    private String numero;
    @Column
    private String cidade;
    @Column
    private String estado;
    @Column
    private String responsavel;
}
