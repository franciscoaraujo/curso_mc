package br.com.nelioalves.cursomc.curso_mc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.nelioalves.cursomc.curso_mc.services.DBService;
import br.com.nelioalves.cursomc.curso_mc.services.email.EmailService;
import br.com.nelioalves.cursomc.curso_mc.services.email.MockEmailService;
import br.com.nelioalves.cursomc.curso_mc.services.email.SmtpEmailService;

@Configuration
@Profile("mysql")
public class MysqlConfig {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDataBase() throws ParseException {

		if (!"create".equals(strategy)) {
			return false;
		}
		dbService.instatiateTestDatabase();

		return true;
	}

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}
