package in.jit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GrnApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrnApplication.class, args);
	}

}
