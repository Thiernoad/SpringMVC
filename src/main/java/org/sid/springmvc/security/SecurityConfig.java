package org.sid.springmvc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//utilise un seul mot de passe par defaut qui utiliser au demarrage exp:54cbe6f5-0fe3-4c69-ae85-e9ca962ef9b6
		//super.configure(auth);
	 //comment chercher les utilisateur
	   auth.inMemoryAuthentication().withUser("user1").password("{noop}1234").roles("USER");
	   auth.inMemoryAuthentication().withUser("user2").password("{noop}1234").roles("USER");
	   auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("USER","ADMIN");
	   
	}
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//super.configure(http);
    	//maintenant pour authoriser l'authentification
    	http.formLogin();
    	//http.httpBasic();
    	//quelque choix les caracteres apres (**)
    	http.authorizeRequests().antMatchers("/save**/**","/delete**/**").hasRole("ADMIN");
    	http.authorizeRequests().anyRequest().authenticated();
    	
    	//pour l'activer ou le desactiver le sincroniger tookens
    	
    	//http.csrf().disable();
    	http.csrf();
    }
}
