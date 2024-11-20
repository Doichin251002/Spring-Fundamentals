package bg.softuni.eventschedulingcaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class EventSchedulingCachingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventSchedulingCachingApplication.class, args);
    }

}
