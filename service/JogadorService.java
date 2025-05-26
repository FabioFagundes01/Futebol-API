package app.futebol.service; // Pacote atualizado

 import app.futebol.model.Jogador; // Import atualizado
 import app.futebol.repository.JogadorRepository; // Import atualizado
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;

 import java.util.List;
 import java.util.Optional;

 @Service
 public class JogadorService {

     private final JogadorRepository jogadorRepository;

     @Autowired
     public JogadorService(JogadorRepository jogadorRepository) {
         this.jogadorRepository = jogadorRepository;
     }

     @Transactional
     public Jogador criarJogador(Jogador jogador) {
         return jogadorRepository.save(jogador);
     }

     @Transactional(readOnly = true)
     public List<Jogador> listarJogadores() {
         return jogadorRepository.findAll();
     }

     @Transactional(readOnly = true)
     public Optional<Jogador> buscarJogadorPorId(Integer codJogador) {
         return jogadorRepository.findById(codJogador);
     }

     @Transactional
     public Jogador atualizarJogador(Integer codJogador, Jogador jogadorDetails) {
         Jogador jogador = jogadorRepository.findById(codJogador)
                 .orElseThrow(() -> new RuntimeException("Jogador não encontrado com o código: " + codJogador));

         jogador.setNome(jogadorDetails.getNome());
         jogador.setEmail(jogadorDetails.getEmail());
         jogador.setDataNasc(jogadorDetails.getDataNasc());
         return jogadorRepository.save(jogador);
     }

     @Transactional
     public void deletarJogador(Integer codJogador) {
         if (!jogadorRepository.existsById(codJogador)) {
             throw new RuntimeException("Jogador não encontrado com o código: " + codJogador);
         }
         jogadorRepository.deleteById(codJogador);
     }
 }