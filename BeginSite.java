import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeginSite extends JFrame {

    private JPanel panel1;
    private JButton REGISTERButton;
    private JButton LOGINButton;
    private final JFrame frame;


    public BeginSite(){





        frame=new JFrame("WELCOME TO OUR SITE");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400,350));

        frame.setResizable(false);



        frame.add(panel1);

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginGUI();
            }
        });

        REGISTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AccountType();
            }
        });
    }
}
