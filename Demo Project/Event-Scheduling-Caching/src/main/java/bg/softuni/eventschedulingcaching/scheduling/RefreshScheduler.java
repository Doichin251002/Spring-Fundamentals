package bg.softuni.eventschedulingcaching.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RefreshScheduler {

    @Scheduled(cron = "*/3 * * * * *")
    public void refreshData() {

        System.out.println("Data has been updated");
    }
}
