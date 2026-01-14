package lvt_pd;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Picerija {

    private List<Pasutijums> pasutijumi = new ArrayList<>();
    private int nextId = 1;

    public List<Pasutijums> getPasutijumi() { return pasutijumi; }

    public Pasutijums izveidotPasutijumu(Klients klients, String piegadesVeids) {
        Pasutijums p = new Pasutijums(nextId++, klients, piegadesVeids);
        pasutijumi.add(p);
        return p;
    }

    public Pasutijums atrastPasutijumu(int id) {
        for (Pasutijums p : pasutijumi) {
            if (p.getPasutijumaID() == id) return p;
        }
        return null;
    }

    public List<Pasutijums> iegutAktivosPasutijumus() {
        List<Pasutijums> rez = new ArrayList<>();
        for (Pasutijums p : pasutijumi) if (p.irAktivs()) rez.add(p);
        return rez;
    }

    public List<Pasutijums> iegutNodotosPasutijumus() {
        List<Pasutijums> rez = new ArrayList<>();
        for (Pasutijums p : pasutijumi) if (p.getStatuss() == Pasutijums.PasutijumaStatus.NODOTS) rez.add(p);
        return rez;
    }

    public void saglabatPasutijumuFaila(String failaNosaukums) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(failaNosaukums))) {
            for (Pasutijums o : pasutijumi) {
                w.write("PASŪTĪJUMS|" + o.getPasutijumaID() + "|" + o.getStatuss() + "|" + o.getPiegadesVeids()
                        + "|" + sag(o.getKlients().getVards()) + "|" + sag(o.getKlients().getTalrunis()) + "|" + sag(o.getKlients().getAdrese()));
                w.newLine();

                for (Pica p : o.getPicas()) {
                    String merces = String.join(",", p.getMerces());
                    String piedevas = String.join(",", p.getPiedevas());
                    w.write("PICA|" + sag(p.getNosaukums()) + "|" + p.getIzmers() + "|" + p.iegutCenu()
                            + "|" + sag(merces) + "|" + sag(piedevas));
                    w.newLine();
                }
                w.write("END");
                w.newLine();
            }
        }
    }

    public void ieladetPasutijumuNoFaila(String failaNosaukums) throws IOException {
        pasutijumi.clear();
        nextId = 1;

        Pasutijums current = null;

        try (BufferedReader r = new BufferedReader(new FileReader(failaNosaukums))) {
            String line;
            while ((line = r.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] parts = line.split("\\|", -1);
                String type = parts[0];

                if ("PASŪTĪJUMS".equals(type)) {
                    int id = Integer.parseInt(parts[1]);
                    Pasutijums.PasutijumaStatus st = Pasutijums.PasutijumaStatus.valueOf(parts[2]);
                    String piegade = parts[3];
                    String vards = un(parts[4]);
                    String tel = un(parts[5]);
                    String adr = un(parts[6]);

                    Klients k = new Klients(vards, tel, adr);
                    current = new Pasutijums(id, k, piegade);
                    current.setStatuss(st);

                    pasutijumi.add(current);
                    if (id >= nextId) nextId = id + 1;

                } else if ("PICA".equals(type) && current != null) {
                    String nos = un(parts[1]);
                    int iz = Integer.parseInt(parts[2]);
                    double cena = Double.parseDouble(parts[3]);
                    String mercesCsv = un(parts[4]);
                    String piedevasCsv = un(parts[5]);

                    Pica p = new Pica(nos);
                    p.setIzmers(iz);

                    if (!mercesCsv.isBlank()) {
                        for (String m : mercesCsv.split(",")) p.pievienotMerci(m.trim());
                    }
                    if (!piedevasCsv.isBlank()) {
                        for (String pd : piedevasCsv.split(",")) p.pievienotPiedavu(pd.trim());
                    }

                   
                    p.aprekinatCenu(0, 0, 0, 0);

                } else if ("BEIGAS".equals(type)) {
                    if (current != null) current.aprekinatKopSummu();
                    current = null;
                }
            }
        }
    }

    private String sag(String s) {
        if (s == null) return "";
        return s.replace("\n", " ").replace("\r", " ").replace("|", "/");
    }

    private String un(String s) { return s == null ? "" : s; }
}