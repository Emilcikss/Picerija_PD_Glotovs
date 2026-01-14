package lvt_pd;

import java.time.LocalDateTime;

public class Pasutijums {

    public enum PasutijumaStatus {
        PIEŅEMTS, GATAVOJAS, GATAVS, NODOTS, ATCELTS
    }

    private int pasutijumaID;
    private LocalDateTime pasutijumaLaiks;
    private Klients klients;
    private PasutijumaStatus statuss;
    private String piegadesVeids; // "UZ_VIETAS" vai "PIEGADE"

    public Pasutijums(int pasutijumaID, Klients klients, String piegadesVeids) {

        this.pasutijumaID = pasutijumaID;
        this.klients = klients;
        this.piegadesVeids = piegadesVeids;
        this.pasutijumaLaiks = LocalDateTime.now();
        this.statuss = PasutijumaStatus.PIEŅEMTS;
    }

    public int getPasutijumaID() {
return pasutijumaID;
}
    public LocalDateTime getPasutijumaLaiks() {
 return pasutijumaLaiks;
 }
    public Klients getKlients() { 
return klients; 
}

    public PasutijumaStatus getStatuss() {
 return statuss;
 }
    public void setStatuss(PasutijumaStatus statuss) { 
this.statuss = statuss; 
}

    public String getPiegadesVeids() { 
return piegadesVeids;
 }
    public void setPiegadesVeids(String piegadesVeids) { 
this.piegadesVeids = piegadesVeids;
 }

    public String IsaInfo() {
        return "ID: " + pasutijumaID
                + " | " + statuss
                + " | Klients: " + klients.getVards();
    }
}