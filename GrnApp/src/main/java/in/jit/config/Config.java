package in.jit.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import in.jit.client.ClientCalls;

@Configuration
public class Config {
  
	@LoadBalanced
	@Bean
	public RestTemplate createRestCalls() {
		return new RestTemplate();
	}
	
	@Bean
	public ClientCalls createClientCalls() {
		return new ClientCalls();
	}
	
}
