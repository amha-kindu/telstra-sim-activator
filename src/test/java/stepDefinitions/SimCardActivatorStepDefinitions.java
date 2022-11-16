package stepDefinitions;

import  au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.entity.SimCard;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;


@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {

    @Autowired
    private TestRestTemplate restTemplate;
    private SimCard simcard;
    private  final String apiUrl = "http://localhost:8080/actuate";

    @Given("a working sim-card")
    public void aWorkingSimCard() {
        this.simcard = new SimCard("1255789453849037777", "amhaznif@gmail.com",false);
    }
    @Given("a broken sim-card")
    public void aBrokenSimCard() {
        this.simcard = new SimCard("8944500102198304826", "amhakindu@gmail.com",false);
    }
    @When("user sends sim-card activation request")
    public void userSendsSimCardActivationRequest() {
        restTemplate.postForObject(apiUrl, this.simcard, Boolean.class);
    }
    @Then("sim-card is activated and stored in database")
    public void simCardIsActivatedAndStoredInDatabase() throws Exception{
        SimCard simCard = restTemplate.getForObject("http://localhost:8080/simcard?id={id}", SimCard.class, 1);
        if(!simCard.getActive())    throw new Exception();
    }
    @Then("sim-card is not activated")
    public void simCardIsNotActivated() throws Exception{
        SimCard simCard = restTemplate.getForObject("http://localhost:8080/simcard?id={id}", SimCard.class, 2);
        if(simCard.getActive())    throw new Exception();
    }
}