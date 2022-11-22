package com.gvendas.gestaovendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GestaoVendasApplication {

	 /**
	  * @Bean
	    public HttpTraceRepository httpTraceRepository() {
	        return new InMemoryHttpTraceRepository();
	    }**/
	
	public static void main(String[] args) {
		SpringApplication.run(GestaoVendasApplication.class, args);
	}

}
