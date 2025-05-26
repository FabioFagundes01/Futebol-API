package app.futebol.service; // Pacote atualizado

 import app.futebol.model.Jogador; // Import atualizado
 import app.futebol.model.Pagamento; // Import atualizado
 import app.futebol.repository.JogadorRepository; // Import atualizado
 import app.futebol.repository.PagamentoRepository; // Import atualizado
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;

 import java.util.List;
 import java.util.Optional;

 @Service
 public class PagamentoService {

     private final PagamentoRepository pagamentoRepository;
     private final JogadorRepository jogadorRepository;

     @Autowired
     public PagamentoService(PagamentoRepository pagamentoRepository, JogadorRepository jogadorRepository) {
         this.pagamentoRepository = pagamentoRepository;
         this.jogadorRepository = jogadorRepository;
     }

     @Transactional
     public Pagamento criarPagamento(Pagamento pagamento, Integer codJogador) {
         Jogador jogador = jogadorRepository.findById(codJogador)
                 .orElseThrow(() -> new RuntimeException("Jogador não encontrado com o código: " + codJogador));
         pagamento.setJogador(jogador);
         return pagamentoRepository.save(pagamento);
     }

     @Transactional(readOnly = true)
     public List<Pagamento> listarPagamentos() {
         return pagamentoRepository.findAll();
     }

     @Transactional(readOnly = true)
     public Optional<Pagamento> buscarPagamentoPorId(Integer codPagamento) {
         return pagamentoRepository.findById(codPagamento);
     }

     @Transactional(readOnly = true)
     public List<Pagamento> buscarPagamentosPorJogador(Integer codJogador) {
         if (!jogadorRepository.existsById(codJogador)) {
             throw new RuntimeException("Jogador não encontrado com o código: " + codJogador);
         }
         return pagamentoRepository.findByJogadorCodJogador(codJogador);
     }

      @Transactional
     public Pagamento atualizarPagamento(Integer codPagamento, Pagamento pagamentoDetails) {
         Pagamento pagamento = pagamentoRepository.findById(codPagamento)
                 .orElseThrow(() -> new RuntimeException("Pagamento não encontrado com o código: " + codPagamento));

         pagamento.setAno(pagamentoDetails.getAno());
         pagamento.setMes(pagamentoDetails.getMes());
         pagamento.setValor(pagamentoDetails.getValor());
         return pagamentoRepository.save(pagamento);
     }

     @Transactional
     public void deletarPagamento(Integer codPagamento) {
          if (!pagamentoRepository.existsById(codPagamento)) {
             throw new RuntimeException("Pagamento não encontrado com o código: " + codPagamento);
         }
         pagamentoRepository.deleteById(codPagamento);
     }
 }