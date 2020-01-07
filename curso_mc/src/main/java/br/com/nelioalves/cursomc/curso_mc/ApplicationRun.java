package br.com.nelioalves.cursomc.curso_mc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.nelioalves.cursomc.curso_mc.services.S3Service;

@SpringBootApplication
public class ApplicationRun implements CommandLineRunner {

	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(ApplicationRun.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		s3Service.upload("C:\\Users\\FOAJ\\Pictures\\carro_2.teste.jpg");
	}

}
