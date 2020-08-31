package in.jit;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class UomAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UomAppApplication.class, args);
	}
		
}
