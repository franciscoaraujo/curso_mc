package br.com.nelioalves.cursomc.curso_mc.services.email;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;


/*Padrao strategy e template*/
public class MockEmailService extends AbstractMailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando o Envio de Email...");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
		
	}
	
	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulando o Envio de Email Html...");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
		

	}
}

