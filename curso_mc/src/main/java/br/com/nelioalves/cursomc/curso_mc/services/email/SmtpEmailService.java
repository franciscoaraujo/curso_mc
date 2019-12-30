package br.com.nelioalves.cursomc.curso_mc.services.email;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractMailService {

	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSeder;
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Envio de Email...");
		mailSender.send(msg);
		LOG.info("Email enviado");
		
	}
	
	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Envio de Email...");
		javaMailSeder.send(msg);
		LOG.info("Email enviado");
		
	}
	
}
