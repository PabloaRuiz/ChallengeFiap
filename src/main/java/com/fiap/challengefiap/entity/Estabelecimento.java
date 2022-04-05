package com.fiap.challengefiap.entity;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "tb_estabelecimento")
public class Estabelecimento {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "estabelecimento_id")
    private Long id;
    private String nomeEstabelecimento;
    private LocalDate visita;

    public Estabelecimento() {
    }


    public Estabelecimento(Long id, String nomeEstabelecimento, LocalDate visita) {
        this.id = id;
        this.nomeEstabelecimento = nomeEstabelecimento;
        this.visita = visita;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = nomeEstabelecimento;
    }

    public LocalDate getVisita() {
        return visita;
    }

    public void setVisita(LocalDate visita) {
        this.visita = visita;
    }

    @Override
    public String toString() {
        return "Estabelecimento{" +
                "id=" + id +
                ", Estabelecimento='" + nomeEstabelecimento + '\'' +
                ", visita=" + visita +
                '}';
    }
}
