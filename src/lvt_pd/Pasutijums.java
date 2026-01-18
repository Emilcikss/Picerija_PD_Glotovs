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
    private String piegadesVeids; // "UZ_VIETAS" vai "PIEGADE"
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
    	}

    public String getPiegadesVeids() {
    	return piegadesVeids; 
    	}
    public void setPiegadesVeids(String piegadesVeids) {
    	this.piegadesVeids = piegadesVeids; 
    	}

    public List<Pica> getPicas() {
    	return picas; 
    	}
    public void setPicas(List<Pica> picas) {
    	this.picas = picas; 
    	}

    public void pievienotPreci(Pica pica) {
        if (pica != null) {
            picas.add(pica);
            aprekinatKopSummu();
        }
    }

    public void nonemtPreci(int index) {
        if (index >= 0 && index < picas.size()) {
            picas.remove(index);
            aprekinatKopSummu();
        }
    }

    public void aprekinatKopSummu() {
        double sum = 0;
        for (Pica p : picas) sum += p.iegutCenu();
        // piegādes maksa 
        if ("PIEGADE".equals(piegadesVeids)) sum += 2.50;
        this.kopsumma = sum;
    }

    public boolean irAktivs() {
        switch (statuss) {
            case PIEŅEMTS:
            case GATAVOJAS:
            case GATAVS:
                return true;
            default:
                return false;
        }
    }

    public String IsaInfo() {
        return "ID: " + pasutijumaID
                + " | " + statuss
                + " | " + String.format("%.2f", kopsumma) + "€"
                + " | Klients: " + klients.getVards();
    }

    public String fullInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pasūtījums ID: ").append(pasutijumaID).append("\n");
        sb.append("Laiks: ").append(pasutijumaLaiks).append("\n");
        sb.append("Statuss: ").append(statuss).append("\n");
        sb.append("Piegāde: ").append(piegadesVeids).append("\n");
        sb.append("Klients: ").append(klients.IsaInfo()).append("\n\n");

        if (picas.isEmpty()) sb.append("Picas: -\n");
        else {
            sb.append("Picas:\n");
            for (int i = 0; i < picas.size(); i++) {
                sb.append("[").append(i + 1).append("] ").append("\n").append(picas.get(i).IsaInfo()).append("\n\n");
            }
        }
        sb.append("Kopsumma: ").append(String.format("%.2f", kopsumma)).append("€\n");
        return sb.toString();
    }

    public String ceks() {
        StringBuilder sb = new StringBuilder();

        sb.append("PICĒRIJA\n");
        sb.append("--------------------\n");
        sb.append("Pasūtījums ID: ").append(pasutijumaID).append("\n");
        sb.append("Laiks: ").append(pasutijumaLaiks).append("\n");
        sb.append("Klients: ").append(klients.IsaInfo()).append("\n");
        sb.append("Piegāde: ").append(piegadesVeids).append("\n");
        sb.append("--------------------\n");

        if (picas.isEmpty()) sb.append("Picas: -\n");
        else {
            for (int i = 0; i < picas.size(); i++) {
                sb.append(i + 1).append(") ").append(picas.get(i).getNosaukums())
                  .append(" ").append(picas.get(i).izmersTeksts())
                  .append(" - ").append(String.format("%.2f", picas.get(i).iegutCenu())).append("€\n");
            }
        }

        if ("PIEGADE".equals(piegadesVeids)) sb.append("Piegāde: 2.50€\n");

        sb.append("--------------------\n");
        sb.append("KOPĀ: ").append(String.format("%.2f", kopsumma)).append("€\n");

        return sb.toString();
    }
}