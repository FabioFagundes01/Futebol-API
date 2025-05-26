package app.futebol.model; // Pacote atualizado

 import jakarta.persistence.*;
 import java.math.BigDecimal;

 @Entity
 @Table(name = "pagamento")
 public class Pagamento {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "cod_pagamento") // [PK]
     private Integer codPagamento;

     @Column(name = "ano", nullable = false) // SMALLINT
     private Short ano;

     @Column(name = "mes", nullable = false) // TINYINT
     private Byte mes;

     @Column(name = "valor", nullable = false) // NUMERIC
     private BigDecimal valor;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "cod_jogador", nullable = false) // [FK] referenciando Jogador
     private Jogador jogador;

     // Construtores
     public Pagamento() {
     }

     public Pagamento(Short ano, Byte mes, BigDecimal valor, Jogador jogador) {
         this.ano = ano;
         this.mes = mes;
         this.valor = valor;
         this.jogador = jogador;
     }

     // Getters e Setters
     public Integer getCodPagamento() {
         return codPagamento;
     }

     public void setCodPagamento(Integer codPagamento) {
         this.codPagamento = codPagamento;
     }

     public Short getAno() {
         return ano;
     }

     public void setAno(Short ano) {
         this.ano = ano;
     }

     public Byte getMes() {
         return mes;
     }

     public void setMes(Byte mes) {
         this.mes = mes;
     }

     public BigDecimal getValor() {
         return valor;
     }

     public void setValor(BigDecimal valor) {
         this.valor = valor;
     }

     public Jogador getJogador() {
         return jogador;
     }

     public void setJogador(Jogador jogador) {
         this.jogador = jogador;
     }
 }