package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.nelioalves.cursomc.curso_mc.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	/*Trocar essa chamada por um web service que gera boleto*/
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date pagamento) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(pagamento);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVenciemento(cal.getTime());
	}
	
	
}
