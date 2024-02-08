package me.dri.Catvie;

import me.dri.Catvie.infra.tokens.Pessoa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatvieApplication {



	public static void main(String[] args) {
		Pessoa pessoa1 = new Pessoa("diego", "senha");

		Pessoa pessoa = new Pessoa.Builder()
				.withUsername("diego")
						.withPassword("senha")
								.build();

		SpringApplication.run(CatvieApplication.class, args);

	}
}
