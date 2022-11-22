package com.gvendas.gestaovendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class GestaoVendasApplication {

	 @Bean
	    public HttpTraceRepository httpTraceRepository() {
	        return new InMemoryHttpTraceRepository();
	    }
	
	public static void main(String[] args) {
		SpringApplication.run(GestaoVendasApplication.class, args);
	}

}
