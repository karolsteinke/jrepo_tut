package sk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

//TODO books filtering
//TODO tests
//TODO research: when we avoid sending whole class and send dto instead? how about sending list of objects (refer to problem in this project)