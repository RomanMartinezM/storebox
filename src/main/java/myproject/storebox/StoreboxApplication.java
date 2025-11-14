package myproject.storebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"myproject.storebox", "myproject.storebox.auth"})
public class StoreboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreboxApplication.class, args);
	}

}
