package controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	 
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setShowBanner(true);
        app.run(args);
        log.info("###################################");
        log.info("###################################");
        log.info("######   AP SERVER START   #######");
        log.info("###################################");
        log.info("###################################");
    }

}
