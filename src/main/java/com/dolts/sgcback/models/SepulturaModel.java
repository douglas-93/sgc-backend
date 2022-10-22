package com.dolts.sgcback.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "SGCSEPULTURA")
public class SepulturaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String local;
    @ManyToOne
    private CemiterioModel cemiterio;
}
