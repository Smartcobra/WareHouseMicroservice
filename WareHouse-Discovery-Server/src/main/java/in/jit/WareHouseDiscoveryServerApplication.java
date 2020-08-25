package in.jit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class WareHouseDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WareHouseDiscoveryServerApplication.class, args);
	}

}
