package app.futebol.dto; // Pacote atualizado

 import java.math.BigDecimal;

 public class PagamentoDTO {

     private Integer codPagamento;
     private Short ano;
     private Byte mes;
     private BigDecimal valor;
     private Integer codJogador;

     // Construtores
     public PagamentoDTO() {
     }

     public PagamentoDTO(Integer codPagamento, Short ano, Byte mes, BigDecimal valor, Integer codJogador) {
         this.codPagamento = codPagamento;
         this.ano = ano;
         this.mes = mes;
         this.valor = valor;
         this.codJogador = codJogador;
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

     public Integer getCodJogador() {
         return codJogador;
     }

     public void setCodJogador(Integer codJogador) {
         this.codJogador = codJogador;
     }
 }