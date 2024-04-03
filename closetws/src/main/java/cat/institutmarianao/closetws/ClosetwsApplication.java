package cat.institutmarianao.closetws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@PropertySource("classpath:openapi.properties")
@OpenAPIDefinition(info = @Info(title = "${ws.title}", version = "${ws.version}"))
public class ClosetwsApplication {
	public static final String DATE_PATTERN = "dd/MM/yyyy";
	@Bean
	protected PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(ClosetwsApplication.class, args);
	}

}
