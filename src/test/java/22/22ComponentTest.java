package 22;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class 22ComponentTest extends CamelTestSupport {

    private final EventBusHelper eventBusHelper = EventBusHelper.getInstance();

    @Test
    public void test22() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(5);

        // Trigger events to subscribers
        simulateEventTrigger();

        mock.await();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("22://foo")
                  .to("22://bar")
                  .to("mock:result");
            }
        };
    }

    private void simulateEventTrigger() {
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                final Date now = new Date();
                // publish events to the event bus
                eventBusHelper.publish(now);
            }
        };

        new Timer().scheduleAtFixedRate(task, 1000L, 1000L);
    }
}
