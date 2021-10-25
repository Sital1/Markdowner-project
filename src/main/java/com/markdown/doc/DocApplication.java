package com.markdown.doc;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableMongoAuditing
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableEncryptableProperties
public class DocApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocApplication.class, args);
	}

}
