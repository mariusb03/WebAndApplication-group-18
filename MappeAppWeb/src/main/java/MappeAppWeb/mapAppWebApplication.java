package MappeAppWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import MappeAppWeb.DB.tool.CoursesInitializer;

@SpringBootApplication
public class mapAppWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(mapAppWebApplication.class, args);
	}

}
