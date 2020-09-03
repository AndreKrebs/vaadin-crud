package utfpr.crud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import utfpr.crud.model.Carro;
import utfpr.crud.model.Marca;
import utfpr.crud.model.Opcional;
import utfpr.crud.repository.CarroRepository;
import utfpr.crud.repository.MarcaRepository;
import utfpr.crud.repository.OpcionalRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(CarroRepository carroRepository, MarcaRepository marcaRepository,
                                  OpcionalRepository opcionalRepository) {
        return (args) -> {
            List<Marca> marcas = new ArrayList<>();
            List<Opcional> opcionais = new ArrayList<>();

            marcas.add(marcaRepository.save(new Marca("GM - Chevrolet")));
            marcas.add(marcaRepository.save(new Marca("Kia")));
            marcas.add(marcaRepository.save(new Marca("Audi")));
            marcas.add(marcaRepository.save(new Marca("Peugeot")));
            marcas.add(marcaRepository.save(new Marca("Volkswagen")));

            opcionais.add(opcionalRepository.save(new Opcional("Completo")));
            opcionais.add(opcionalRepository.save(new Opcional("Trio elétrico")));
            opcionais.add(opcionalRepository.save(new Opcional("Automático")));


            carroRepository.save(new Carro(opcionais.get(0), marcas.get(0), "Vermelho", "Camaro", 2020));
            carroRepository.save(new Carro(opcionais.get(1), marcas.get(4), "Branco", "Gol", 2020));
            carroRepository.save(new Carro(opcionais.get(2), marcas.get(1), "Preto", "Picanto", 2020));
        };
    }
}
