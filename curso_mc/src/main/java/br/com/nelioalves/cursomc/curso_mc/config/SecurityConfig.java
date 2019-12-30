package br.com.nelioalves.cursomc.curso_mc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment environment;
	
	public static final String[] PUBLIC_MATCHERS = {"h2-console"};
	
	public static final String[] PUBLIC_MATCHERS_GET = { "/produtos/**", "/categorias/**" };//recupero produtos e categorias mas nao posso altera-los
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*pra acessar o H2 pelo navegador*/
		if(Arrays.asList(environment.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.cors().and().csrf().disable();
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
		.antMatchers(PUBLIC_MATCHERS) //todos os caminhos que estao aqui no vetor poderar ser acessado
		.permitAll()
		.anyRequest()
		.authenticated();//o resto vai ter que ser autenticado
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//a segura que o backend nao vai criar sessao de usuario
	}

	@Bean//definindo um bean de corsconfigurationsource.
	CorsConfigurationSource corsConfigurationSource() {
		
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		//dando um acesso basico de acesso a multiplas fontes para todos os caminhos, permitindo
		//o acesso aos endpoints por multiplas fontes con as configuracoes basicas
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
