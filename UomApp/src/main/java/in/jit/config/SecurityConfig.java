package in.jit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        // your configuration goes here
        http
            .authorizeRequests()
            .antMatchers("/**").permitAll().anyRequest().authenticated();
        
        http
        .httpBasic()
//				/* .and().formLogin().defaultSuccessUrl("/userDetail", true) */
        .and().csrf().disable()
        .cors().disable()
       ;
    }
}
