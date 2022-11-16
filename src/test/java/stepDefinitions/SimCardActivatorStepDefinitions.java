package stepDefinitions;

import  au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.entity.ActuationResult;
import au.com.telstra.simcardactivator.entity.SimCard;
import au.com.telstra.simcardactivator.repositories.SimCardRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.assertj.core.error.ShouldBeEqual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {
    private static long val=1l;
    private Boolean success;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private SimCardRepository simcardRepo;

    @When("user sends iccid With {string}")
    public void userSendsIccidWith(String iccid) {
        String apiUrl = "http://localhost:8080/actuate";
        Map<String, String> request = new HashMap<>();
        request.put("iccid", iccid);  request.put("customerEmail", "amhakindu@gmail.com");

        restTemplate.postForObject(apiUrl, request, Boolean.class);
        SimCard sim = simcardRepo.findById(val).orElse(null);
        this.success = sim.getActive();
        val++;
    }

    @Then("sim activation success is {string}")
    public void simActivationSuccessIs(String arg0) throws Exception{
        if(this.success!=Boolean.valueOf(arg0))
            throw new Exception();
    }
    @Then("SimCard activation {string}")
    public void simcardActivation(String arg) throws Exception{
        if(this.success!=Objects.equals(arg, "successful"))
            throw new Exception();
    }
}