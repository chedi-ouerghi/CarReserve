package com.reservations.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.reservations.springboot")
public class SpringBootSecurityJwtApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootSecurityJwtApplication.class, args);

		
		String ANSI_RESET = "\u001B[0m";
		String ANSI_RED = "\u001B[31m";
		String ANSI_YELLOW = "\u001B[33m";
		String ANSI_BLUE = "\u001B[34m";
		String ANSI_CYAN = "\u001B[36m";

		
		System.out.println(ANSI_CYAN + "**************************************************" + ANSI_RESET); 
																											
																											
		System.out.println(ANSI_YELLOW + " 🎉 Serveur démarré avec succès ! 🚀 " + ANSI_RESET);
		System.out.println(
				ANSI_BLUE + " 🌐 Accédez à : " + ANSI_RED + "http://localhost:8080/" + ANSI_BLUE + " 🔗" + ANSI_RESET);
		System.out.println(ANSI_CYAN + "**************************************************" + ANSI_RESET); 
																											
																											
	}
}
