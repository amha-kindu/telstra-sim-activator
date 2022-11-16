package au.com.telstra.simcardactivator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;


@RestController
public class SimCardActRestController {
    HttpClient httpClient = HttpClient.newHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/")
    public void processActivation(@RequestBody Map<String, Object> payload) throws InterruptedException, IOException, URISyntaxException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8444/actuate"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(String.format("{\"iccid\":\"%s\"}", payload.get("iccid"))))
                .build();

        HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());

        Map<String, Boolean> map = mapper.readValue(response.body(), Map.class);
        if(map.get("success")==true)
            System.out.println("Successful");
        else
            System.out.println("Failed");
    }
}