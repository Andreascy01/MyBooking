import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellerActions {
    private JPanel panel1;
    private JButton MESSAGESButton;
    private JButton ESTATESButton;
    private JButton PROFILEButton;
    private JButton LOGOUTButton;
    private final JFrame frame;

    public SellerActions()
    {
        frame = new JFrame("SELLER ACTIONS");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 350));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        LOGOUTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Logged Out");
                frame.dispose();
                new BeginSite();
            }
        });

        PROFILEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SellerProfile();
            }
        });

        MESSAGESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SellerMessages();
            }
        });
        ESTATESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SellerEstates();
            }
        });
    }




}
