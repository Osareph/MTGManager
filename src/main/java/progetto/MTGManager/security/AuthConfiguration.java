package progetto.MTGManager.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment environment;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
			.authorizeRequests()
				.antMatchers("/index","/","/singUp","/confermaRegistrazione","/registrazione","/css/**","/assets/**").permitAll()
			.antMatchers("admin/**").hasAnyAuthority("ADMIN")
				
			.anyRequest().authenticated()
			
			.and()

			.formLogin()
				.loginPage("/login").defaultSuccessUrl("/home").permitAll()
				
			.and()
			
			.logout().permitAll().
				logoutUrl("/logout").
					logoutSuccessUrl("/index");
	}
	
    @Bean
    DataSource buildDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(this.buildDatasource())
                .authoritiesByUsernameQuery("SELECT username, role FROM utente WHERE username=?")
                .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM utente WHERE username=?");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
