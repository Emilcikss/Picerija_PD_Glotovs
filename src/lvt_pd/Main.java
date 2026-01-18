package lvt_pd;

import javax.sound.sampled.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

//MUZIKA ATSK
	    private static Clip klips;
	    private static JFrame galvenaisLogs;
		private static void atskaņotSkanu(String soundFile) {
		    try {
		        java.io.File f = new java.io.File(soundFile);
		        javax.sound.sampled.AudioInputStream ais = javax.sound.sampled.AudioSystem.getAudioInputStream(f.toURI().toURL());
		        javax.sound.sampled.Clip c = javax.sound.sampled.AudioSystem.getClip();
		        c.open(ais);
		        c.start();
		    } catch (Exception e) {
		        System.out.println("Nevar atskaņot skaņu: " + soundFile);
		    }
		}
		

	    public static void apturetMuziku() {
	        if (klips != null && klips.isRunning()) {
	            klips.stop();
	        }
	    }
	

	 // TIKAI BURTI 
	    public static boolean irTikaiBurti(String teksts) {
	        return teksts != null
	                && !teksts.isBlank()
	                && teksts.matches("[a-zA-ZāčēģīķļņšūžĀČĒĢĪĶĻŅŠŪŽ ]+");
	    }

	    // TIKAI 8 CIPARI
	    public static boolean irDerigsNumurs(String teksts) {
	        return teksts != null
	                && teksts.matches("\\d{8}");
	    }

	    // Vienkāršs R-Keeper stila dizains
	    private static void iestatītDizainu() {
	        Font pamatFonts = new Font("SansSerif", Font.PLAIN, 14);
	        Font virsraksts = new Font("SansSerif", Font.BOLD, 18);
	        Color fons = new Color(28, 28, 28);
	        Color panelis = new Color(38, 38, 38);
	        Color akcents = new Color(0, 153, 102);
	        Color teksts = new Color(245, 245, 245);

	        UIManager.put("Panel.background", panelis);
	        UIManager.put("OptionPane.background", panelis);
	        UIManager.put("OptionPane.messageForeground", teksts);
	        UIManager.put("Label.foreground", teksts);
	        UIManager.put("Button.background", akcents);
	        UIManager.put("Button.foreground", Color.WHITE);
	        UIManager.put("TextField.background", Color.WHITE);
	        UIManager.put("TextField.foreground", Color.BLACK);
	        UIManager.put("ComboBox.background", Color.WHITE);
	        UIManager.put("ComboBox.foreground", Color.BLACK);
	        UIManager.put("List.background", Color.WHITE);
	        UIManager.put("List.foreground", Color.BLACK);
	        UIManager.put("Menu.background", panelis);
	        UIManager.put("Menu.foreground", teksts);
	        UIManager.put("MenuItem.background", panelis);
	        UIManager.put("MenuItem.foreground", teksts);
	        UIManager.put("OptionPane.font", pamatFonts);
	        UIManager.put("Label.font", pamatFonts);
	        UIManager.put("Button.font", pamatFonts);
	        UIManager.put("TextField.font", pamatFonts);
	        UIManager.put("ComboBox.font", pamatFonts);
	        UIManager.put("List.font", pamatFonts);
	        UIManager.put("OptionPane.messageFont", pamatFonts);
	        UIManager.put("OptionPane.buttonFont", pamatFonts);
	        UIManager.put("Panel.font", pamatFonts);
	        UIManager.put("TitledBorder.font", pamatFonts);
	        UIManager.put("ToolTip.background", panelis);
	        UIManager.put("ToolTip.foreground", teksts);
	        UIManager.put("ToolTip.font", pamatFonts);
	        UIManager.put("RootPane.background", fons);
	        UIManager.put("OptionPane.titleFont", virsraksts);
	    }

	
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
        iestatītDizainu();
        atskaņotSkanu("./audio/game.wav");
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

            String izvele = dizains.raditIzvelni(galvenaisLogs, izvelne);

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
                    String f = JOptionPane.showInputDialog(galvenaisLogs, "Faila nosaukums (piem. pasutijumi.txt):");
                    if (f != null && !f.isBlank()) {
                        picerija.saglabatPasutijumuFaila(f.trim());
                        JOptionPane.showMessageDialog(galvenaisLogs, "Saglabāts!");
                    }
                } else if (izvele.startsWith("6")) {
                    String f = JOptionPane.showInputDialog(galvenaisLogs, "Faila nosaukums (piem. pasutijumi.txt):");
                    if (f != null && !f.isBlank()) {
                        picerija.ieladetPasutijumuNoFaila(f.trim());
                        JOptionPane.showMessageDialog(galvenaisLogs, "Ielādēts!");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(galvenaisLogs, "Kļūda: " + ex.getMessage());
            }
        }
    }

// pasūtījuma reģistrēšana 
    private static void pasutijumaRegistrēšana(Picerija picerija) {
    	String vards;

    	while (true) {
    	    vards = JOptionPane.showInputDialog(galvenaisLogs, "Ievadiet vārdu:");
    	    if (vards == null) return;

    	    if (irTikaiBurti(vards)) {
    	        break;
    	    } else {
    	        JOptionPane.showMessageDialog(
    	                galvenaisLogs,
    	                "Vārds drīkst saturēt tikai burtus!",
    	                "Kļūda",
    	                JOptionPane.ERROR_MESSAGE
    	        );
    	    }
    	}


    	String numurs;

    	while (true) {
    	    numurs = JOptionPane.showInputDialog(galvenaisLogs, "Ievadiet telefona numuru (8 cipari):");
    	    if (numurs == null) return;

    	    if (irDerigsNumurs(numurs)) {
    	        break;
    	    } else {
    	        JOptionPane.showMessageDialog(
    	                galvenaisLogs,
    	                "Numuram jābūt tieši no 8 cipariem!",
    	                "Kļūda",
    	                JOptionPane.ERROR_MESSAGE
    	        );
    	    }
    	}


        String[] piegadeIzv = {"UZ_VIETAS", "PIEGADE"};
        String piegade = (String) JOptionPane.showInputDialog(galvenaisLogs, "Ievadiet piegādes veidu:", "Piegāde",
                JOptionPane.QUESTION_MESSAGE, null, piegadeIzv, piegadeIzv[0]);
        if (piegade == null) return;

        String adrese = "-";
        if ("PIEGADE".equals(piegade)) {
            adrese = JOptionPane.showInputDialog(galvenaisLogs, "Ievadiet adresi:");
            if (adrese == null || adrese.isBlank()) return;
        }

        Klients klients = new Klients(vards.trim(), numurs.trim(), adrese.trim());
        Pasutijums pas = picerija.izveidotPasutijumu(klients, piegade);

 // cikls: izveidot vēl vienu picu?
        while (true) {
            Pica pica = izveidotPicu();
            if (pica == null) break;
            pas.pievienotPreci(pica);

            int ok = JOptionPane.showConfirmDialog(galvenaisLogs, "Izveidot vēl vienu picu?", "Vēl viena?",
                    JOptionPane.YES_NO_OPTION);
            if (ok != JOptionPane.YES_OPTION) break;
        }

        pas.aprekinatKopSummu();

        int apst = JOptionPane.showConfirmDialog(galvenaisLogs,
                pas.fullInfo() + "\nAPSTIPRINĀT?",
                "Apstiprināt",
                JOptionPane.YES_NO_OPTION);

        if (apst != JOptionPane.YES_OPTION) {
            pas.setStatuss(Pasutijums.PasutijumaStatus.ATCELTS);
            JOptionPane.showMessageDialog(galvenaisLogs, "Pasūtījums atcelts.");
        } else {
            JOptionPane.showMessageDialog(galvenaisLogs, "Pasūtījums saglabāts!\n" + pas.IsaInfo());
        }
    }

    // izveidot picu
    private static Pica izveidotPicu() {
        PicasVeids veids = (PicasVeids) JOptionPane.showInputDialog(
                galvenaisLogs,
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
                galvenaisLogs, "Picas izmērs:", "Izmērs",
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

        JOptionPane.showMessageDialog(galvenaisLogs, "Izveidota pica:\n\n" + p.IsaInfo());
        return p;
       
    }

    // statusa maiņa
    private static void mainitStatusu(Picerija picerija) {
        String s = JOptionPane.showInputDialog(galvenaisLogs, "Ievadi pasūtījuma ID:");
        if (s == null || s.isBlank()) return;

        int id = Integer.parseInt(s.trim());
        Pasutijums pas = picerija.atrastPasutijumu(id);
        if (pas == null) {
            JOptionPane.showMessageDialog(galvenaisLogs, "Pasūtījums nav atrasts!");
            return;
        }

        Pasutijums.PasutijumaStatus[] statusi = Pasutijums.PasutijumaStatus.values();
        Pasutijums.PasutijumaStatus jauns = (Pasutijums.PasutijumaStatus) JOptionPane.showInputDialog(
                galvenaisLogs,
                "Izvēlies jaunu statusu:\n\n" + pas.IsaInfo(),
                "Statuss",
                JOptionPane.QUESTION_MESSAGE,
                null,
                statusi,
                pas.getStatuss()
        );

        if (jauns == null) return;
        pas.setStatuss(jauns);
        JOptionPane.showMessageDialog(galvenaisLogs, "Atjaunināts:\n" + pas.IsaInfo());
    }

    // saraksta parādīšana
    private static void paraditSarakstu(String title, List<Pasutijums> saraksts) {
        if (saraksts == null || saraksts.isEmpty()) {
            JOptionPane.showMessageDialog(galvenaisLogs, title + ": nav.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Pasutijums p : saraksts) sb.append(p.IsaInfo()).append("\n");
        JOptionPane.showMessageDialog(galvenaisLogs, sb.toString(), title, JOptionPane.INFORMATION_MESSAGE);
    }

    // multi izvēle 
    private static List<String> multiIzvele(String title, String[] opcijas) {
        List<String> izveletie = new ArrayList<>();
        while (true) {
            String[] menu = new String[opcijaSaraksts(opcijas).length + 1];
            System.arraycopy(opcijaSaraksts(opcijas), 0, menu, 0, opcijaSaraksts(opcijas).length);
            menu[menu.length - 1] = "Gatavs";

            String izvēle = (String) JOptionPane.showInputDialog(
                    galvenaisLogs,
                    title + "\nIzvēlētie: " + (izveletie.isEmpty() ? "-" : String.join(", ", izveletie)),
                    "Izvēle",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    menu,
                    menu[0]
            );

            if (izvēle == null || "Gatavs".equals(izvēle)) break;

            if (!izveletie.contains(izvēle)) izveletie.add(izvēle);

            int turp = JOptionPane.showConfirmDialog(galvenaisLogs, "Pievienot vēl?", "Turpināt", JOptionPane.YES_NO_OPTION);
            if (turp != JOptionPane.YES_OPTION) break;
        }
        return izveletie;
    }

    private static String[] opcijaSaraksts(String[] a) {
    	return a;
    	}
}
}
