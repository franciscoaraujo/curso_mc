package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.nelioalves.cursomc.curso_mc.domain.Cliente;
import br.com.nelioalves.cursomc.curso_mc.repositories.ClienteRepository;
import br.com.nelioalves.cursomc.curso_mc.services.email.EmailService;
import br.com.nelioalves.cursomc.curso_mc.services.exception.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPasswordEmail(String email) throws ObjectNotFoundException{
		 
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));
		clienteRepository.save(cliente);
		emailService. sendNewPasswordEmail( cliente,newPass);
	 }


	private String newPassword() {
		char[] vet = new char[10];
		for(int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		
		return new String(vet);
	}


	private char randomChar() {
		int opt = rand.nextInt(3);
		if(opt == 0) {//gera um digito
			return (char) (rand.nextInt(10) + 48);//caracter conrrespondente de 0-9(olhar tabela asc)
		}else if(opt == 1) {//gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);//carateres conrrespondente de 0-26
		}else {//gera letra minuscula
			return (char) (rand.nextInt(26) + 97);//carateres conrrespondente de 0-26
		}
	}
	
}
