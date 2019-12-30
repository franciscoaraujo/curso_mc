package br.com.nelioalves.cursomc.curso_mc.services.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractMailService {

	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

	@Autowired
	private MailSender mailSernder;

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Envio de Email...");
		mailSernder.send(msg);
		LOG.info("Email enviado");
	}

}
