package com.propets.apirest.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PropetsBackendApirestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PropetsBackendApirestApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
	}
}
