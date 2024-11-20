package bg.softuni.eventschedulingcaching.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private static int counter = 0;

    @EventListener
    public void onHelloWorldEvent(HelloWorldEvent helloWorldEvent) {
        System.out.println(++counter + " events were received. Last message: " + helloWorldEvent.getMessage());
    }
}
