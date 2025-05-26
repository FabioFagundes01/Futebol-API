package app.futebol.controller; // Pacote atualizado

 import app.futebol.model.Jogador; // Import atualizado
 import app.futebol.service.JogadorService; // Import atualizado
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

 import java.util.List;

 @RestController
 @RequestMapping("/api/jogadores")
 public class JogadorController {

     private final JogadorService jogadorService;

     @Autowired
     public JogadorController(JogadorService jogadorService) {
         this.jogadorService = jogadorService;
     }

     @PostMapping
     public ResponseEntity<Jogador> criarJogador(@RequestBody Jogador jogador) {
         Jogador novoJogador = jogadorService.criarJogador(jogador);
         return new ResponseEntity<>(novoJogador, HttpStatus.CREATED);
     }

     @GetMapping
     public ResponseEntity<List<Jogador>> listarJogadores() {
         List<Jogador> jogadores = jogadorService.listarJogadores();
         return ResponseEntity.ok(jogadores);
     }

     @GetMapping("/{codJogador}")
     public ResponseEntity<Jogador> buscarJogadorPorId(@PathVariable Integer codJogador) {
         return jogadorService.buscarJogadorPorId(codJogador)
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
     }

     @PutMapping("/{codJogador}")
     public ResponseEntity<Jogador> atualizarJogador(@PathVariable Integer codJogador, @RequestBody Jogador jogadorDetails) {
         try {
             Jogador jogadorAtualizado = jogadorService.atualizarJogador(codJogador, jogadorDetails);
             return ResponseEntity.ok(jogadorAtualizado);
         } catch (RuntimeException e) {
             return ResponseEntity.notFound().build();
         }
     }

     @DeleteMapping("/{codJogador}")
     public ResponseEntity<Void> deletarJogador(@PathVariable Integer codJogador) {
         try {
             jogadorService.deletarJogador(codJogador);
             return ResponseEntity.noContent().build();
         } catch (RuntimeException e) {
             return ResponseEntity.notFound().build();
         }
     }
 }