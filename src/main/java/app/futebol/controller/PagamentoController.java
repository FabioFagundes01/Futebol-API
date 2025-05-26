package app.futebol.controller; // Pacote atualizado

 import app.futebol.dto.PagamentoDTO; // Import atualizado
 import app.futebol.model.Pagamento; // Import atualizado
 import app.futebol.service.PagamentoService; // Import atualizado
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

 import java.util.List;
 import java.util.stream.Collectors;

 @RestController
 @RequestMapping("/api")
 public class PagamentoController {

     private final PagamentoService pagamentoService;

     @Autowired
     public PagamentoController(PagamentoService pagamentoService) {
         this.pagamentoService = pagamentoService;
     }

     @PostMapping("/jogadores/{codJogador}/pagamentos")
     public ResponseEntity<PagamentoDTO> criarPagamento(@PathVariable Integer codJogador, @RequestBody PagamentoDTO pagamentoDTO) {
         try {
             Pagamento pagamento = converterParaEntidade(pagamentoDTO);
             Pagamento novoPagamento = pagamentoService.criarPagamento(pagamento, codJogador);
             return new ResponseEntity<>(converterParaDto(novoPagamento), HttpStatus.CREATED);
         } catch (RuntimeException e) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
         }
     }

     @GetMapping("/pagamentos")
     public ResponseEntity<List<PagamentoDTO>> listarTodosPagamentos() {
         List<Pagamento> pagamentos = pagamentoService.listarPagamentos();
         List<PagamentoDTO> pagamentoDTOs = pagamentos.stream().map(this::converterParaDto).collect(Collectors.toList());
         return ResponseEntity.ok(pagamentoDTOs);
     }

     @GetMapping("/jogadores/{codJogador}/pagamentos")
     public ResponseEntity<List<PagamentoDTO>> listarPagamentosPorJogador(@PathVariable Integer codJogador) {
          try {
             List<Pagamento> pagamentos = pagamentoService.buscarPagamentosPorJogador(codJogador);
             if (pagamentos.isEmpty()) {
                 return ResponseEntity.noContent().build();
             }
             List<PagamentoDTO> pagamentoDTOs = pagamentos.stream().map(this::converterParaDto).collect(Collectors.toList());
             return ResponseEntity.ok(pagamentoDTOs);
         } catch (RuntimeException e) {
             return ResponseEntity.notFound().build();
         }
     }

     @GetMapping("/pagamentos/{codPagamento}")
     public ResponseEntity<PagamentoDTO> buscarPagamentoPorId(@PathVariable Integer codPagamento) {
         return pagamentoService.buscarPagamentoPorId(codPagamento)
                 .map(pagamento -> ResponseEntity.ok(converterParaDto(pagamento)))
                 .orElse(ResponseEntity.notFound().build());
     }

     @PutMapping("/pagamentos/{codPagamento}")
     public ResponseEntity<PagamentoDTO> atualizarPagamento(@PathVariable Integer codPagamento, @RequestBody PagamentoDTO pagamentoDTO) {
         try {
             Pagamento pagamentoDetails = converterParaEntidade(pagamentoDTO);
             Pagamento pagamentoAtualizado = pagamentoService.atualizarPagamento(codPagamento, pagamentoDetails);
             return ResponseEntity.ok(converterParaDto(pagamentoAtualizado));
         } catch (RuntimeException e) {
             return ResponseEntity.notFound().build();
         }
     }

     @DeleteMapping("/pagamentos/{codPagamento}")
     public ResponseEntity<Void> deletarPagamento(@PathVariable Integer codPagamento) {
         try {
             pagamentoService.deletarPagamento(codPagamento);
             return ResponseEntity.noContent().build();
         } catch (RuntimeException e) {
             return ResponseEntity.notFound().build();
         }
     }

     private PagamentoDTO converterParaDto(Pagamento pagamento) {
         PagamentoDTO dto = new PagamentoDTO();
         dto.setCodPagamento(pagamento.getCodPagamento());
         dto.setAno(pagamento.getAno());
         dto.setMes(pagamento.getMes());
         dto.setValor(pagamento.getValor());
         if (pagamento.getJogador() != null) {
             dto.setCodJogador(pagamento.getJogador().getCodJogador());
         }
         return dto;
     }

     private Pagamento converterParaEntidade(PagamentoDTO dto) {
         Pagamento pagamento = new Pagamento();
         pagamento.setAno(dto.getAno());
         pagamento.setMes(dto.getMes());
         pagamento.setValor(dto.getValor());
         return pagamento;
     }
 }