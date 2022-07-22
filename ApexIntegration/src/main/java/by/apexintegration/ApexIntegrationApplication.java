package by.apexintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApexIntegrationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApexIntegrationApplication.class, args);
    }
}