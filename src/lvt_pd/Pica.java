package lvt_pd;

import java.util.ArrayList;
import java.util.List;

public class Pica {
    private String nosaukums;
    private int izmers; // 0=S,1=M,2=L
    private double cena; // gala cena (aprēķināta)
    private List<String> piedevas = new ArrayList<>();
    private List<String> merces = new ArrayList<>();

    public Pica(String nosaukums) {
        this.nosaukums = nosaukums;
        this.izmers = 1; // default M
        this.cena = 0;
    }

    public String getNosaukums() { 
    	return nosaukums; 
    	}

    public int getIzmers() { 
    	return izmers; 
    	}
    
    public void setIzmers(int izmers) { 
    	this.izmers = izmers;
    }
    
    public List<String> getPiedevas() {
    	return piedevas;
    	}
    
    public List<String> getMerces() { 
    	return merces; 
    	}

    public void pievienotPiedavu(String piedava) {
        if (piedava != null && !piedava.isBlank() && !piedevas.contains(piedava)) {
            piedevas.add(piedava);
        }
    }

    public void nonemtPiedavu(String piedava) {
        piedevas.remove(piedava);
    }

    public void pievienotMerci(String merce) { // JAPAPILDINA DIAGRAMMAS!
        if (merce != null && !merce.isBlank() && !merces.contains(merce)) {
            merces.add(merce);
        }
    }

    public void nonemtMerci(String merce) { // JAPAPILDINA DIAGRAMMAS!
        merces.remove(merce);
    }

    public double iegutCenu() {
    	return cena;
    	}

    public void aprekinatCenu(double bāzesCena, double izmēraKoef, double piedevasCena, double mercesCena) { // JAPAPILDINA DIAGRAMMAS!
        // vienkārši: baze*coef + piedevas*X + merces*Y
        this.cena = (bāzesCena * izmēraKoef) + (piedevas.size() * piedevasCena) + (merces.size() * mercesCena);
    }

    public String izmersTeksts() {
        switch (izmers) {
            case 0:
                return "S";
            case 1:
                return "M";
            case 2:
                return "L";
            default:
                return "M";
        }
    }

    public String IsaInfo() {
        return nosaukums + " (" + izmersTeksts() + ") - " + String.format("%.2f", cena) + "€"
                + "\nMērces: " + (merces.isEmpty() ? "-" : String.join(", ", merces))
                + "\nPiedevas: " + (piedevas.isEmpty() ? "-" : String.join(", ", piedevas));
    }
}
