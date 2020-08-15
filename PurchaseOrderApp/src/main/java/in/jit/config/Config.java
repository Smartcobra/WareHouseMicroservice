package in.jit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.jit.client.ClientCalls;

@Configuration
public class Config {
  
	@Bean
	public RestTemplate createRestCalls() {
		return new RestTemplate();
	}
	
	@Bean
	public ClientCalls createClientCalls() {
		return new ClientCalls();
	}
}
