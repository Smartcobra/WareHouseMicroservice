package in.jit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class WhUserTypeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhUserTypeApplication.class, args);
	}

}
