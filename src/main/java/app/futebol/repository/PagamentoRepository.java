package app.futebol.repository; // Pacote atualizado

 import app.futebol.model.Pagamento; // Import atualizado
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;
 import java.util.List;

 @Repository
 public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
     List<Pagamento> findByJogadorCodJogador(Integer codJogador);
     List<Pagamento> findByJogadorCodJogadorAndAnoAndMes(Integer codJogador, Short ano, Byte mes);
 }