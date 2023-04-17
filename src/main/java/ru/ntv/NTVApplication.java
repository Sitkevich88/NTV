package ru.ntv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Configuration
@SpringBootApplication

//@EnableJpaRepositories
//@EntityScan
public class NTVApplication {

    public static void main(String[] args) {
        SpringApplication.run(NTVApplication.class, args);
    }
}
