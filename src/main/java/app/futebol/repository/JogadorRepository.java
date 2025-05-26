package app.futebol.repository; // Pacote atualizado

 import app.futebol.model.Jogador; // Import atualizado
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;

 @Repository
 public interface JogadorRepository extends JpaRepository<Jogador, Integer> {
 }