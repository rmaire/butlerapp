package ch.uprisesoft.butler;

import ch.uprisesoft.butler.cli.CliRunner;
import freemarker.template.TemplateException;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class ButlerApplication {

	public static void main(String[] args) throws InterruptedException, IOException, TemplateException {
            if(args != null && args.length > 0) {
                CliRunner runner = new CliRunner();
                
                if(args[0].equals("--jobfile") || args[0].equals("-j") ) {
                    System.out.println(runner.generateJobFile());
                }
                
                if(args[0].equals("--setup") || args[0].equals("-s") ) {
                    Thread.sleep(120000);
                    runner.setup();
                    while(true) {
                        Thread.sleep(100000);
                    }
                }
                
            } else {
		SpringApplication.run(ButlerApplication.class, args);
            }
	}
}
