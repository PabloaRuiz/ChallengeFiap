package com.fiap.challengefiap.entity;


import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "tb_bebidas")
public class Bebida {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "bebida_id")
    private Long id;
    private String tipo_Cerveja;
    private BigDecimal consumo;
    private BigDecimal valor;

    public Bebida() {

    }

    public Bebida(Long id, String tipo_Cerveja, BigDecimal consumo, BigDecimal valor) {
        this.id = id;
        tipo_Cerveja = tipo_Cerveja;
        consumo = consumo;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo_Cerveja() {
        return tipo_Cerveja;
    }

    public void setTipo_Cerveja(String tipo_Cerveja) {
        tipo_Cerveja = tipo_Cerveja;
    }

    public BigDecimal getConsumo() {
        return consumo;
    }

    public void setConsumo(BigDecimal consumo) {
        consumo = consumo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }



    @Override
    public String toString() {
        return "Bebida{" +
                "id=" + id +
                ", Tipo_Cerveja='" + tipo_Cerveja + '\'' +
                ", Consumo=" + consumo +
                ", valor=" + valor +
                '}';
    }
}
