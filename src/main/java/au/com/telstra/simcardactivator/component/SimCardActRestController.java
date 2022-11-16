package au.com.telstra.simcardactivator.component;

import au.com.telstra.simcardactivator.entity.SimCard;
import au.com.telstra.simcardactivator.repositories.SimCardRepository;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
public class SimCardActRestController {

    private final ActuationRequestHandler requestHandler;
    private final SimCardRepository simcardRepo;

    public SimCardActRestController(ActuationRequestHandler requestHandler, SimCardRepository simcardRepo) {
        this.requestHandler = requestHandler;
        this.simcardRepo = simcardRepo;
    }

    @PostMapping("/actuate")
    public void processActuation(@RequestBody SimCard simCard) {
        var result = requestHandler.actuate(simCard);
//        System.out.println(result.getSuccess());
        simCard.setActive(result.getSuccess());
        simcardRepo.save(simCard);
//        System.out.println(simCard.getId());
    }

    @GetMapping("/simcard")
    public HttpEntity<String> getSimcards(@RequestParam("id") long id){
        Optional<SimCard> sim = simcardRepo.findById(id);
        SimCard simCard = new SimCard();
        if(sim.isPresent())
            simCard = sim.get();
        return new HttpEntity<String>(simCard.toString());
    }

}