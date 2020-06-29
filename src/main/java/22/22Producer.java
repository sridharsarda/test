package 22;

import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class 22Producer extends DefaultProducer {
    private static final Logger LOG = LoggerFactory.getLogger(22Producer.class);
    private 22Endpoint endpoint;

    public 22Producer(22Endpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {
        System.out.println(exchange.getIn().getBody());    
    }

}
