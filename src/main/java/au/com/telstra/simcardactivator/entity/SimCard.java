package au.com.telstra.simcardactivator.entity;


import au.com.telstra.simcardactivator.dto.SimCardDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String iccid;
    private String customerEmail;
    private boolean active;

    public Long getId() {   return id;  }
    public void setId(Long id) {    this.id = id;   }

    public SimCard() {}
    public SimCard(SimCardDTO dto){
        this(dto.getIccid(), dto.getCustomerEmail(), dto.getActive());
    }
    public SimCard(String iccid, String customerEmail, boolean active) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
        this.active = active;
    }
    public SimCardDTO dto(){
        return new SimCardDTO(this.iccid, this.customerEmail, this.active);
    }
    public String getIccid() {  return iccid;   }

    public void setIccid(String iccid) {    this.iccid = iccid;}

    public String getCustomerEmail() {  return customerEmail;   }

    public void setCustomerEmail(String customerEmail) {    this.customerEmail = customerEmail; }

    public boolean getActive() {    return active;  }

    public void setActive(boolean active) { this.active = active;   }

    @Override
    public String toString() {
        return "SimCard {iccid=" + iccid + ", customerEmail=" + customerEmail + ", active=" + active + "}";
    }
}