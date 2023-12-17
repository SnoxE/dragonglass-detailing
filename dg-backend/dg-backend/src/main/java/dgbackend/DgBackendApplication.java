package dgbackend;

import dgbackend.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class DgBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(DgBackendApplication.class, args);
	}

}
