package com.example.server;

import com.example.server.model.Server;
import com.example.server.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.server.enumeration.Status.SERVER_UP;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	@Bean
	CommandLineRunner run(ServerRepo serverRepo){
		return args -> {
			serverRepo.save(new Server( "192.168.1.160", "Windows 11", "16 GB","Personal Computer", "http://localhost:8080/server/image/server1.png", SERVER_UP));
			serverRepo.save(new Server( "192.122.1.170", "Windows 10", "16 GB","Personal Computer", "http://localhost:8080/server/image/server2.png", SERVER_UP));
			serverRepo.save(new Server( "192.138.1.150", "Windows Vista", "12 GB","Personal Computer", "http://localhost:8080/server/image/server3.png", SERVER_UP));
			serverRepo.save(new Server( "192.178.1.160", "Ubuntu linux", "8 GB","Personal Computer", "http://localhost:8080/server/image/server4.png", SERVER_UP));
		};
	}


}
