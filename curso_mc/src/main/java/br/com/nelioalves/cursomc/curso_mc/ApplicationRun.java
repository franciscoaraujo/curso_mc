package br.com.nelioalves.cursomc.curso_mc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationRun implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(ApplicationRun.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
