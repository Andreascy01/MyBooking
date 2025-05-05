import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminViewRC {
    private JPanel panel1;
    private JButton RESERVATIONSButton;
    private JButton CANCELLATIONSButton;
    private JButton BACKButton;
    private final JFrame frame;

    public AdminViewRC() {


        frame = new JFrame("ADMIN ACTIONS");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 350));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

            RESERVATIONSButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        new AdminReserves();
                }
            });


        CANCELLATIONSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminCancels();
            }
        });
        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminActions();
            }
        });
    }
    }

