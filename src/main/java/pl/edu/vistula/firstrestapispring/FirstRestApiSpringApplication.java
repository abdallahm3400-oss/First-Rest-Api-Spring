package pl.edu.vistula.firstrestapispring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Products API for Vistula",
        version = "1.0",
        description = "Products Information"
))
public class FirstRestApiSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstRestApiSpringApplication.class, args);
    }

}
