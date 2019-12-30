package br.com.nelioalves.cursomc.curso_mc.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.nelioalves.cursomc.curso_mc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage obj);

}
