package lvt_pd;

public class Klients {
    private String vards;
    private String talrunis;
    private String adrese; // var būt "-" ja uz vietas

    public Klients(String vards, String talrunis, String adrese) {
        this.vards = vards;
        this.talrunis = talrunis;
        this.adrese = adrese;
    }

    public String getVards() {
    	return vards;
    	}
    public void setVards(String vards) {
    	this.vards = vards;
    	}

    public String getTalrunis() {
    	return talrunis;
    	}
    public void setTalrunis(String talrunis) {
    	this.talrunis = talrunis;
    	}

    public String getAdrese() {
    	return adrese;
    			}
    public void setAdrese(String adrese) {
    	this.adrese = adrese;
    	}

    public String IsaInfo() {
        return vards + ", " + talrunis + ", adrese: " + adrese;
    }
}
