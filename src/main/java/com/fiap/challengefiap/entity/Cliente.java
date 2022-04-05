package com.fiap.challengefiap.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "tb_clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cliente_id")
    private Long id;
    private int telefone;
    private String cervejaFavorita;
    private String nome;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "estabelecimento_id"),
            name = "tb_estabelecimentos_cliente")
    private List<Estabelecimento> Estabelecimentos = new ArrayList<Estabelecimento>();


    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "bebida_id"),
            name = "tb_bebidas_cliente")
    private List<Bebida> Bebidas = new ArrayList<Bebida>();


    public Cliente() {

    }

    public Cliente(Long id, int telefone, String cervejaFavorita, String nome) {
        this.id = id;
        telefone = telefone;
        cervejaFavorita = cervejaFavorita;
        nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        telefone = telefone;
    }

    public String getcervejaFavorita() {
        return cervejaFavorita;
    }

    public void cervejaFavorita(String cervejaFavorita) {
        cervejaFavorita = cervejaFavorita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        nome = nome;
    }

    public List<Estabelecimento> getEstabelecimentos() {
        return Collections.unmodifiableList(Estabelecimentos);
    }

    public void setEstabelecimentos(List<Estabelecimento> estabelecimentos) {
        this.Estabelecimentos = estabelecimentos;
    }

    public void adicionaEstabelecimento(Estabelecimento estabelecimento) {
        this.Estabelecimentos.add(estabelecimento);
    }

    public List<Bebida> getBebidas() {
        return Collections.unmodifiableList(Bebidas);
    }

    public void adicionaBebidas(Bebida bebida) {
        this.Bebidas.add(bebida);
    }

    public void setBebidas(List<Bebida> bebidas) {
        this.Bebidas = bebidas;
    }

    public BigDecimal getCalculoTotal() {
       BigDecimal total = new BigDecimal(0.00);
        for (Bebida B : Bebidas) {
            total = total.add(B.getConsumo().multiply(B.getValor()));
        }
        return total.setScale(2, RoundingMode.HALF_EVEN);

    }

    public BigDecimal getCalcularTicketMedio() {
        BigDecimal ticketMedio = new BigDecimal(0.00);
        BigDecimal visitas = BigDecimal.valueOf(Estabelecimentos.size());
        ticketMedio = getCalculoTotal().divide(visitas);
        return ticketMedio.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", Telefone=" + telefone +
                ", Bebida_Favorita='" + cervejaFavorita + '\'' +
                ", Cliente='" + nome + '\'' +
                ", Estabelecimentos=" + Estabelecimentos +
                ", Bebidas=" + Bebidas +
                '}';
    }
}
