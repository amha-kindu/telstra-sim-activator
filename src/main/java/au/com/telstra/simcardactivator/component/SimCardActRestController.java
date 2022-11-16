package au.com.telstra.simcardactivator.component;

import au.com.telstra.simcardactivator.entity.SimCard;
import au.com.telstra.simcardactivator.repositories.SimCardRepository;
import org.springframework.web.bind.annotation.*;
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
        simCard.setActive(result.getSuccess());
        simcardRepo.save(simCard);
    }

    @GetMapping("/simcard")
    public SimCard getSimcards(@RequestParam("id") long id){
        Optional<SimCard> sim = simcardRepo.findById(id);
        SimCard simCard = null;
        if(sim.isPresent())
            simCard = sim.get();
        return simCard;
    }
}