package app.futebol; // Pacote atualizado

 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.SpringBootApplication;

 @SpringBootApplication // Esta anotação já inclui @ComponentScan para o pacote atual e subpacotes
 public class FutebolApplication {

     public static void main(String[] args) {
         SpringApplication.run(FutebolApplication.class, args);
     }

 }