package au.com.telstra.simcardactivator.dto;

public class SimCardDTO {
    private final String iccid;
    private final String customerEmail;
    private final boolean active;

    public SimCardDTO(String iccid, String customerEmail, boolean active) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
        this.active = active;
    }

    public String getIccid() {  return iccid;   }
    public String getCustomerEmail() {  return customerEmail;   }
    public boolean getActive() {    return active;  }
}
