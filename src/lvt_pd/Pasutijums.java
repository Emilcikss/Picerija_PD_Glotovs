package lvt_pd;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pasutijums {

    public enum PasutijumaStatus {
        PIEŅEMTS, GATAVOJAS, GATAVS, NODOTS, ATCELTS
    }

    private int pasutijumaID;
    private LocalDateTime pasutijumaLaiks;
    private Klients klients;
    private PasutijumaStatus statuss;
    private double kopsumma; 
    private String piegadesVeids;
    private List<Pica> picas = new ArrayList<>();

    public Pasutijums(int pasutijumaID, Klients klients, String piegadesVeids) {
        this.pasutijumaID = pasutijumaID;
        this.klients = klients;
        this.piegadesVeids = piegadesVeids;
        this.pasutijumaLaiks = LocalDateTime.now();
        this.statuss = PasutijumaStatus.PIEŅEMTS;
        this.kopsumma = 0;
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

    public double getKopsumma() {
    	return kopsumma; 
    	} // pagaidām vienmēr 0

    public String getPiegadesVeids() { return piegadesVeids; }
    public void setPiegadesVeids(String piegadesVeids) { this.piegadesVeids = piegadesVeids; }

    public List<Pica> getPicas() { return picas; }

    public void pievienotPreci(Pica pica) {
        if (pica != null) {
            picas.add(pica);
            
        }
    }

    public void nonemtPreci(int index) {
        if (index >= 0 && index < picas.size()) {
            picas.remove(index);
            
        }
    }

    public String IsaInfo() {
        return "ID: " + pasutijumaID
                + " | " + statuss
                + " | Picas: " + picas.size()
                + " | Klients: " + klients.getVards();
    }
}
