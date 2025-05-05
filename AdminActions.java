import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminActions extends Site {
    private JButton PROFILEButton;
    private JPanel panel1;
    private JButton LOGOUTButton;
    private JButton MESSAGESButton;
    private JButton RESERVATIONSButton;
    private JButton VIEWUSERSButton;
    private final JFrame frame;


    public AdminActions() {

        frame = new JFrame("ADMIN ACTIONS");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 350));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        PROFILEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminProfile();
                frame.dispose();
            }
        });

        LOGOUTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Logged Out");
                frame.dispose();
                new BeginSite();
            }
        });

        VIEWUSERSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminsViewUsers();
            }
        });

        MESSAGESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminMessages();
            }
        });

        RESERVATIONSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminViewRC();
            }
        });
    }
}