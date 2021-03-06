package in.jit.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
  
	@LoadBalanced
	@Bean
	public RestTemplate createRestCalls() {
		return new RestTemplate();
	}
	
}
