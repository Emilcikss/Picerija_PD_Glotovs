package lvt_pd;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class dizains {

    public static String raditIzvelni(JFrame parent, String[] opcijas) {
        JDialog dialogs = new JDialog(parent, "PICĒRIJA", true);
        dialogs.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialogs.setLayout(new BorderLayout(12, 12));
        dialogs.getContentPane().setBackground(new Color(30, 30, 30));

        JPanel galvene = new JPanel(new BorderLayout());
        galvene.setOpaque(false);

        JLabel nosaukums = new JLabel("PICĒRIJAS KASE");
        nosaukums.setFont(new Font("SansSerif", Font.BOLD, 18));
        nosaukums.setForeground(new Color(245, 245, 245));

        String laiks = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        JLabel datums = new JLabel(laiks);
        datums.setFont(new Font("SansSerif", Font.PLAIN, 12));
        datums.setForeground(new Color(200, 200, 200));

        galvene.add(nosaukums, BorderLayout.WEST);
        galvene.add(datums, BorderLayout.EAST);
        dialogs.add(galvene, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(3, 3, 10, 10));
        grid.setOpaque(false);
        String[] kr = {
                "#4DA5D9", "#5BC0BE", "#F4D35E",
                "#9B5DE5", "#00BBF9", "#F15BB5",
                "#00F5D4", "#3A86FF", "#6D597A"
        };

        final String[] izvele = {null};

        for (int i = 0; i < opcijas.length; i++) {
            JButton poga = new JButton(opcijas[i]);
            poga.setFocusPainted(false);
            poga.setFont(new Font("SansSerif", Font.BOLD, 14));
            poga.setBackground(Color.decode(kr[i % kr.length]));
            poga.setForeground(Color.WHITE);
            poga.setBorder(BorderFactory.createLineBorder(new Color(25, 25, 25), 2));
            poga.setPreferredSize(new Dimension(160, 90));
            String vertiba = opcijas[i];
            poga.addActionListener(e -> {
                izvele[0] = vertiba;
                dialogs.dispose();
            });
            grid.add(poga);
        }

        dialogs.add(grid, BorderLayout.CENTER);
        dialogs.setSize(560, 420);
        dialogs.setLocationRelativeTo(parent);
        dialogs.setVisible(true);
        return izvele[0];
    }
}
