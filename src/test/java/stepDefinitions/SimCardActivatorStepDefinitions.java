package stepDefinitions;

import  au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.dto.SimCardDTO;
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
    private SimCardDTO simcardDto;
    private  final String apiUrl = "http://localhost:8080/actuate";

    @Given("a working sim-card")
    public void aWorkingSimCard() {
        this.simcardDto = new SimCardDTO("1255789453849037777", "amhaznif@gmail.com",false);
    }
    @Given("a broken sim-card")
    public void aBrokenSimCard() {
        this.simcardDto = new SimCardDTO("8944500102198304826", "amhakindu@gmail.com",false);
    }
    @When("user sends sim-card activation request")
    public void userSendsSimCardActivationRequest() {
        restTemplate.postForObject(apiUrl, this.simcardDto, Boolean.class);
    }
    @Then("sim-card is activated and stored in database")
    public void simCardIsActivatedAndStoredInDatabase() throws Exception{
        SimCardDTO simCard = restTemplate.getForObject("http://localhost:8080/simcard?id={id}", SimCardDTO.class, 1);
        if(!simCard.active)    throw new Exception();
    }
    @Then("sim-card is not activated")
    public void simCardIsNotActivated() throws Exception{
        SimCardDTO simCard = restTemplate.getForObject("http://localhost:8080/simcard?id={id}", SimCardDTO.class, 2);
        if(simCard.active)    throw new Exception();
    }
}