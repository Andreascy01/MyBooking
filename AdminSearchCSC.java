import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminSearchCSC {
    private JPanel panel1;
    private JButton SELLERButton;
    private JButton CUSTOMERButton;
    private JButton BACKButton;
    private JTextField textField1;
    private JTextField textField2;
    private final JFrame frame;

    public AdminSearchCSC() {

        frame = new JFrame("SEARCH BY SELLER OR CUSTOMER");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 350));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminCancels();
            }
        });

        SELLERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminSearchCS(textField1.getText());
            }
        });

        CUSTOMERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminSearchCC(textField2.getText());
            }
        });
    }
}
