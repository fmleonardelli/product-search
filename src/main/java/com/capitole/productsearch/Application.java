package com.capitole.productsearch;

import io.vavr.control.Option;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, getCurrentProfile());
		SpringApplication.run(Application.class, args);
	}

	private static String getCurrentProfile() {
		return Option.of(System.getenv("WEB_SCOPE"))
				.getOrElse("dev");
	}
}