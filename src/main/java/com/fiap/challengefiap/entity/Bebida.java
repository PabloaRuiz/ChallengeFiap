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
    private String tipoCerveja;
    private BigDecimal consumo;
    private BigDecimal valor;

    public Bebida() {

    }

    public Bebida(Long id, String tipoCerveja, BigDecimal consumo, BigDecimal valor) {
        this.id = id;
        this.tipoCerveja = tipoCerveja;
        this.consumo = consumo;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoCerveja() {
        return tipoCerveja;
    }

    public void setTipoCerveja(String tipoCerveja) {
        tipoCerveja = tipoCerveja;
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
                ", Tipo_Cerveja='" + tipoCerveja + '\'' +
                ", Consumo=" + consumo +
                ", valor=" + valor +
                '}';
    }
}
