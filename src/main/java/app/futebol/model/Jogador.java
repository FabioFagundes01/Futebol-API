package app.futebol.model; // Pacote atualizado

 import jakarta.persistence.*;
 import java.time.LocalDate;
 import java.util.List;

 @Entity
 @Table(name = "jogador")
 public class Jogador {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "cod_jogador") // [PK]
     private Integer codJogador;

     @Column(name = "nome", length = 60, nullable = false)
     private String nome;

     @Column(name = "email", length = 60, unique = true)
     private String email;

     @Column(name = "datanasc") // DATE
     private LocalDate dataNasc;

     @OneToMany(mappedBy = "jogador", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<Pagamento> pagamentos;

     // Construtores
     public Jogador() {
     }

     public Jogador(String nome, String email, LocalDate dataNasc) {
         this.nome = nome;
         this.email = email;
         this.dataNasc = dataNasc;
     }

     // Getters e Setters
     public Integer getCodJogador() {
         return codJogador;
     }

     public void setCodJogador(Integer codJogador) {
         this.codJogador = codJogador;
     }

     public String getNome() {
         return nome;
     }

     public void setNome(String nome) {
         this.nome = nome;
     }

     public String getEmail() {
         return email;
     }

     public void setEmail(String email) {
         this.email = email;
     }

     public LocalDate getDataNasc() {
         return dataNasc;
     }

     public void setDataNasc(LocalDate dataNasc) {
         this.dataNasc = dataNasc;
     }

     public List<Pagamento> getPagamentos() {
         return pagamentos;
     }

     public void setPagamentos(List<Pagamento> pagamentos) {
         this.pagamentos = pagamentos;
     }
 }