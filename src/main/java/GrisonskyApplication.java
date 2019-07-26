import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class GrisonskyApplication implements CommandLineRunner{
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(GrisonskyApplication.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		
	}
}
