package br.com.nelioalves.cursomc.curso_mc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nelioalves.cursomc.curso_mc.domain.PagamentoComBoleto;
import br.com.nelioalves.cursomc.curso_mc.domain.PagamentoComCartao;

//https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare

/**
 * 
 * @author foaj
 *
 */
@Configuration
public class JacksonConfig {
	
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				/*Registrando as classes para serem instanciados pela chamada do Json*/
				objectMapper.registerSubtypes(PagamentoComCartao.class);
				objectMapper.registerSubtypes(PagamentoComBoleto.class);
				super.configure(objectMapper);
				
			}
		};
		return builder;
	}
}
