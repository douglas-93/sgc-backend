package com.dolts.sgcback.models;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "SGCPESSOAS")
public class PessoaModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nome;
    @Column(unique = true, length = 11, nullable = false)
    private String cpf;
    @OneToOne
    private SepulturaModel sepultura;
    @Column
    private Date falecimento;
    @Column
    private Date sepultamento;
}
