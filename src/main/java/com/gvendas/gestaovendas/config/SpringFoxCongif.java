package com.gvendas.gestaovendas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxCongif {

	@Bean
	public Docket swagger() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.gvendas"))						
				.paths(PathSelectors.any())				
				.build();
				//.apiInfo(infoApi());
	}

	private ApiInfo infoApi() {
		return new ApiInfoBuilder()
				.title("Gestão de Vendas")
				.description("Sistema de Gestão de Vendas")
				.version("1.0.0")
				.build();
	}

}
