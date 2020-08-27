package in.jit.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket createDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("in.jit.controller"))
				.paths(PathSelectors.regex("/rest.*"))
				.build().apiInfo(getApiInfo());

	}

	@SuppressWarnings("rawtypes")
	private ApiInfo getApiInfo() {
		// TODO Auto-generated method stub
		return new ApiInfo("WAREHOUSE APP", "JITSOFT WH APP ", "2.3", 
				"http://jitsoft.com/",
				new springfox.documentation.service.Contact("JIT SOFT", "http://jitsoft.com/", "jitu@jitusoft.com"), 
				"JIT LICENSE", "http://jitsoft.com/", new ArrayList<VendorExtension>());
	}

}
