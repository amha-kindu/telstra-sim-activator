package au.com.telstra.simcardactivator.component;

import au.com.telstra.simcardactivator.entity.SimCard;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class SimCardActRestController {

    private final ActuationRequestHandler requestHandler;

    public SimCardActRestController(ActuationRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @PostMapping("/actuate")
    public void processActuation(@RequestBody SimCard simCard){
        var result = requestHandler.actuate(simCard);
        System.out.println(result.getSuccess());
    }
}