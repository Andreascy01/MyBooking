import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerActions {
    private JButton LOGOUTButton;
    private JPanel panel1;
    private JButton MESSAGESButton;
    private JButton PROFILEButton;
    private JButton SEARCHESTATESButton;
    private JButton RESERVATIONSButton;
    private JButton CANCELLATIONSButton;
    private final JFrame frame;


    public CustomerActions()
    {
        frame = new JFrame("CUSTOMER ACTIONS");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 450));
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
                new CustomerProfile();

            }
        });

        MESSAGESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CustomerMessages();
            }
        });
        RESERVATIONSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CustomerReservations();
            }
        });

        SEARCHESTATESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CustomerEstates();
            }
        });

        CANCELLATIONSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CustomerCancellations();
            }
        });
    }

}
