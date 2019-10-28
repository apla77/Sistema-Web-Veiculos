package br.com.ifrn.swv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled = true)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/fragmentos/**").permitAll()
				.antMatchers("/usuario/**").hasAnyAuthority("ADMINISTRADOR")
				.antMatchers("http://**").hasAnyAuthority("ADMINISTRADOR", "CLIENTE")
				.antMatchers("https://**").hasAnyAuthority("ADMINISTRADOR", "CLIENTE")
				.antMatchers("/veiculo/**").hasAnyAuthority("ADMINISTRADOR", "CLIENTE")
				.antMatchers("/user/**").hasAnyAuthority("ADMINISTRADOR")
				.antMatchers("/api/**").hasAnyAuthority("ADMINISTRADOR", "CLIENTE")
				.antMatchers("layout").permitAll()
				.antMatchers("/images/**").permitAll()
				.antMatchers("/static/**").permitAll()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/vendor/**").permitAll()
				.antMatchers("/fonts/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/distribution/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/user/**").permitAll()
				.antMatchers(HttpMethod.POST, "/user/salvar").permitAll()
				.antMatchers(HttpMethod.GET, "/login").permitAll()
				.antMatchers(HttpMethod.GET, "/api").permitAll()
				.antMatchers(HttpMethod.GET, "/api/veiculo").permitAll()
				.antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
				.antMatchers(HttpMethod.POST, "/home").hasAnyAuthority("CLIENTE", "ADMINISTRADOR")
				.antMatchers(HttpMethod.GET, "/").permitAll()
				.antMatchers(HttpMethod.POST, "/cadastrar").permitAll()
				.antMatchers(HttpMethod.GET, "/cadastrar").permitAll()
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/entrar").permitAll()
				.successForwardUrl("/home").and().logout().permitAll()
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
		http.csrf().disable();
        http.headers().frameOptions().disable();
	
	}
	
	@Autowired
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	  }
	 
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**",  "/distribuition/**", "/fonts/**", "/vendor/**", "/img/**",  "/js/**",  "/scss/**", "/h2/**");
		web.ignoring().antMatchers("/layout", "http::/**", "https::/**", "/http::/**", "/https::/**", "/template-login/**");
  
 }
 
}

