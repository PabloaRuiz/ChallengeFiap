package com.fiap.challengefiap.Entity;

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
    private int Telefone;
    private String Bebida_Favorita;
    private String Cliente;

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

    public Cliente(Long id, int telefone, String bebida_Favorita, String cliente) {
        this.id = id;
        Telefone = telefone;
        Bebida_Favorita = bebida_Favorita;
        Cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTelefone() {
        return Telefone;
    }

    public void setTelefone(int telefone) {
        Telefone = telefone;
    }

    public String getBebida_Favorita() {
        return Bebida_Favorita;
    }

    public void setBebida_Favorita(String bebida_Favorita) {
        Bebida_Favorita = bebida_Favorita;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
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

    public BigDecimal getValorTotal() {
       BigDecimal total = new BigDecimal(0.00);
        for (Bebida B : Bebidas) {
            total = total.add(B.getConsumo().multiply(B.getValor()));
        }
        return total.setScale(2, RoundingMode.HALF_EVEN);

    }

    public BigDecimal getTicketMedio() {
        BigDecimal TicketMedio = new BigDecimal(0.00);
        BigDecimal Visitas = BigDecimal.valueOf(Estabelecimentos.size());
        TicketMedio = getValorTotal().divide(Visitas);
        return TicketMedio.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", Telefone=" + Telefone +
                ", Bebida_Favorita='" + Bebida_Favorita + '\'' +
                ", Cliente='" + Cliente + '\'' +
                ", Estabelecimentos=" + Estabelecimentos +
                ", Bebidas=" + Bebidas +
                '}';
    }
}
