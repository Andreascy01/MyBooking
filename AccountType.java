import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountType extends JFrame{
    private JButton CUSTOMERButton;
    private JPanel panel1;
    private JButton SELLERButton;
    private final JFrame frame;

    public AccountType(){
        frame=new JFrame("CHOOSE YOUR ACCOUNT TYPE");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400,350));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        CUSTOMERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new RegisterCustomer();
            }
        });


        SELLERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new RegisterSeller();
            }
        });
    }
}
