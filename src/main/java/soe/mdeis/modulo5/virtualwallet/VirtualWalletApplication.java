package soe.mdeis.modulo5.virtualwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class VirtualWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualWalletApplication.class, args);
	}

}
