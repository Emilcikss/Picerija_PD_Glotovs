package lvt_pd;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static class PicasVeids {
        String nosaukums;
        double bāzesCena; // M izmēram

        PicasVeids(String nosaukums, double bāzesCena) {
            this.nosaukums = nosaukums;
            this.bāzesCena = bāzesCena;
        }

        @Override public String toString() {
            return nosaukums + " (" + String.format("%.2f", bāzesCena) + "€)";
        }
    }

    private static final PicasVeids[] PICA_SARAKSTS = {
            new PicasVeids("Margarita", 6.00),
            new PicasVeids("Pepperoni", 7.50),
            new PicasVeids("4 Sieri", 8.00),
            new PicasVeids("BBQ Vistas", 8.50),
            new PicasVeids("Veģetārā", 7.00)
    };

    private static final String[] MERCES = {"Tomātu", "Ķiploku", "BBQ", "Asā", "Siera"};
    private static final String[] PIEDEVAS = {"Papildus siers", "Šķiņķis", "Sēnes", "Olīvas", "Jalapeño", "Bekons"};

    // Cena noteikumi (vienkārši)
    private static final double[] IZMERA_KOEF = {0.85, 1.0, 1.25}; // S, M, L
    private static final double PIEDEVAS_CENA = 0.75;
    private static final double MERCES_CENA = 0.50;

    public static void main(String[] args) {
        Picerija picerija = new Picerija();

        while (true) {
            String[] izvelne = {
                    "1) Pasūtījuma reģistrēšana",
                    "2) Skatīt aktīvos pasūtījumus",
                    "3) Skatīt nodotos pasūtījumus",
                    "4) Mainīt pasūtījuma statusu",
                    "5) Saglabāt pasūtījumus failā",
                    "6) Ielādēt pasūtījumus no faila",
                    "0) Iziet"
            };

            String izvele = (String) JOptionPane.showInputDialog(
                    null,
                    "IZVĒLE:",
                    "PICĒRIJA",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    izvelne,
                    izvelne[0]
            );

            if (izvele == null || izvele.startsWith("0")) break;

            try {
                if (izvele.startsWith("1")) {
                    pasutijumaRegistrēšana(picerija);
                } else if (izvele.startsWith("2")) {
                    paraditSarakstu("Aktīvie pasūtījumi", picerija.iegutAktivosPasutijumus());
                } else if (izvele.startsWith("3")) {
                    paraditSarakstu("Nodotie pasūtījumi", picerija.iegutNodotosPasutijumus());
                } else if (izvele.startsWith("4")) {
                    mainitStatusu(picerija);
                } else if (izvele.startsWith("5")) {
                    String f = JOptionPane.showInputDialog("Faila nosaukums (piem. pasutijumi.txt):");
                    if (f != null && !f.isBlank()) {
                        picerija.saglabatPasutijumuFaila(f.trim());
                        JOptionPane.showMessageDialog(null, "Saglabāts!");
                    }
                } else if (izvele.startsWith("6")) {
                    String f = JOptionPane.showInputDialog("Faila nosaukums (piem. pasutijumi.txt):");
                    if (f != null && !f.isBlank()) {
                        picerija.ieladetPasutijumuNoFaila(f.trim());
                        JOptionPane.showMessageDialog(null, "Ielādēts!");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Kļūda: " + ex.getMessage());
            }
        }
    }

// pasūtījuma reģistrēšana 
    private static void pasutijumaRegistrēšana(Picerija picerija) {
        String vards = JOptionPane.showInputDialog("Ievadiet vārdu:");
        if (vards == null || vards.isBlank()) return;

        String numurs = JOptionPane.showInputDialog("Ievadiet numuru:");
        if (numurs == null || numurs.isBlank()) return;

        String[] piegadeIzv = {"UZ_VIETAS", "PIEGADE"};
        String piegade = (String) JOptionPane.showInputDialog(null, "Ievadiet piegādes veidu:", "Piegāde",
                JOptionPane.QUESTION_MESSAGE, null, piegadeIzv, piegadeIzv[0]);
        if (piegade == null) return;

        String adrese = "-";
        if ("PIEGADE".equals(piegade)) {
            adrese = JOptionPane.showInputDialog("Ievadiet adresi:");
            if (adrese == null || adrese.isBlank()) return;
        }

        Klients klients = new Klients(vards.trim(), numurs.trim(), adrese.trim());
        Pasutijums pas = picerija.izveidotPasutijumu(klients, piegade);

 // cikls: izveidot vēl vienu picu?
        while (true) {
            Pica pica = izveidotPicu();
            if (pica == null) break;
            pas.pievienotPreci(pica);

            int ok = JOptionPane.showConfirmDialog(null, "Izveidot vēl vienu picu?", "Vēl viena?",
                    JOptionPane.YES_NO_OPTION);
            if (ok != JOptionPane.YES_OPTION) break;
        }

        pas.aprekinatKopSummu();

        int apst = JOptionPane.showConfirmDialog(null,
                pas.fullInfo() + "\nAPSTIPRINĀT?",
                "Apstiprināt",
                JOptionPane.YES_NO_OPTION);

        if (apst != JOptionPane.YES_OPTION) {
            pas.setStatuss(Pasutijums.PasutijumaStatus.ATCELTS);
            JOptionPane.showMessageDialog(null, "Pasūtījums atcelts.");
        } else {
            JOptionPane.showMessageDialog(null, "Pasūtījums saglabāts!\n" + pas.IsaInfo());
        }
    }

    // izveidot picu
    private static Pica izveidotPicu() {
        PicasVeids veids = (PicasVeids) JOptionPane.showInputDialog(
                null,
                "Izvēlieties picu:",
                "Picas izvēle",
                JOptionPane.QUESTION_MESSAGE,
                null,
                PICA_SARAKSTS,
                PICA_SARAKSTS[0]
        );
        if (veids == null) return null;

        String[] izm = {"S", "M", "L"};
        String izmIzv = (String) JOptionPane.showInputDialog(
                null, "Picas izmērs:", "Izmērs",
                JOptionPane.QUESTION_MESSAGE, null, izm, izm[1]
        );
        if (izmIzv == null) return null;

        int izIndex = switch (izmIzv) {
            case "S" -> 0;
            case "M" -> 1;
            case "L" -> 2;
            default -> 1;
        };

        Pica p = new Pica(veids.nosaukums);
        p.setIzmers(izIndex);

        List<String> merces = multiIzvele("Picas mērces (var vairākas):", MERCES);
        for (String m : merces) p.pievienotMerci(m);

        List<String> piedevas = multiIzvele("Picas piedevas (var vairākas):", PIEDEVAS);
        for (String pd : piedevas) p.pievienotPiedavu(pd);

        p.aprekinatCenu(veids.bāzesCena, IZMERA_KOEF[izIndex], PIEDEVAS_CENA, MERCES_CENA);

        JOptionPane.showMessageDialog(null, "Izveidota pica:\n\n" + p.IsaInfo());
        return p;
    }

    // statusa maiņa
    private static void mainitStatusu(Picerija picerija) {
        String s = JOptionPane.showInputDialog("Ievadi pasūtījuma ID:");
        if (s == null || s.isBlank()) return;

        int id = Integer.parseInt(s.trim());
        Pasutijums pas = picerija.atrastPasutijumu(id);
        if (pas == null) {
            JOptionPane.showMessageDialog(null, "Pasūtījums nav atrasts!");
            return;
        }

        Pasutijums.PasutijumaStatus[] statusi = Pasutijums.PasutijumaStatus.values();
        Pasutijums.PasutijumaStatus jauns = (Pasutijums.PasutijumaStatus) JOptionPane.showInputDialog(
                null,
                "Izvēlies jaunu statusu:\n\n" + pas.IsaInfo(),
                "Statuss",
                JOptionPane.QUESTION_MESSAGE,
                null,
                statusi,
                pas.getStatuss()
        );

        if (jauns == null) return;
        pas.setStatuss(jauns);
        JOptionPane.showMessageDialog(null, "Atjaunināts:\n" + pas.IsaInfo());
    }

    // saraksta parādīšana
    private static void paraditSarakstu(String title, List<Pasutijums> saraksts) {
        if (saraksts == null || saraksts.isEmpty()) {
            JOptionPane.showMessageDialog(null, title + ": nav.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Pasutijums p : saraksts) sb.append(p.IsaInfo()).append("\n");
        JOptionPane.showMessageDialog(null, sb.toString(), title, JOptionPane.INFORMATION_MESSAGE);
    }

    // multi izvēle 
    private static List<String> multiIzvele(String title, String[] opcijas) {
        List<String> izveletie = new ArrayList<>();
        while (true) {
            String[] menu = new String[opcijaSaraksts(opcijas).length + 1];
            System.arraycopy(opcijaSaraksts(opcijas), 0, menu, 0, opcijaSaraksts(opcijas).length);
            menu[menu.length - 1] = "Gatavs";

            String izvēle = (String) JOptionPane.showInputDialog(
                    null,
                    title + "\nIzvēlētie: " + (izveletie.isEmpty() ? "-" : String.join(", ", izveletie)),
                    "Izvēle",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    menu,
                    menu[0]
            );

            if (izvēle == null || "Gatavs".equals(izvēle)) break;

            if (!izveletie.contains(izvēle)) izveletie.add(izvēle);

            int turp = JOptionPane.showConfirmDialog(null, "Pievienot vēl?", "Turpināt", JOptionPane.YES_NO_OPTION);
            if (turp != JOptionPane.YES_OPTION) break;
        }
        return izveletie;
    }

    private static String[] opcijaSaraksts(String[] a) {
    	return a;
    	}
}