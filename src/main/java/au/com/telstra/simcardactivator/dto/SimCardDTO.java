package au.com.telstra.simcardactivator.dto;

public class SimCardDTO {
    public final String iccid;
    public final String customerEmail;
    public final boolean active;

    public SimCardDTO(String iccid, String customerEmail, boolean active) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
        this.active = active;
    }
}
