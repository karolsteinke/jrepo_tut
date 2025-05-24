package sk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

//TODO Not allow any wrong books (input validation, the same title...)
//TODO books filtering
//TODO tests