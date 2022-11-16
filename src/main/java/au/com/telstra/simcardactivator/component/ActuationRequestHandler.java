package au.com.telstra.simcardactivator.component;

import au.com.telstra.simcardactivator.entity.ActuationResult;
import au.com.telstra.simcardactivator.entity.SimCard;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ActuationRequestHandler {
    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ActuationRequestHandler(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
        this.apiUrl = "http://localhost:8444/actuate";
    }

    public ActuationResult actuate(SimCard simCard) {
        return restTemplate.postForObject(apiUrl, simCard, ActuationResult.class);
    }

}