package vn.techmaster.woodshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import vn.techmaster.woodshop.config.StorageProperties;
import vn.techmaster.woodshop.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class WoodshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WoodshopApplication.class, args);
	}

	@Bean
		//Cho phép gọi init trong service dùng để khởi tạo các thư mục
	CommandLineRunner init(StorageService storageService) {
		return (args -> {
			storageService.init();
		});
	}
}
