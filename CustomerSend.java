import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CustomerSend {
    private JTextArea textArea1;
    private JPanel panel1;
    private JTextField Receiver;
    private JButton SENDButton;
    private JButton BACKButton;
    private JLabel Label;
    private final JFrame frame;
    private String user;

    public CustomerSend()
    {

        frame = new JFrame("SEND MESSAGE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 350));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        try {
            File file = new File("src\\Login.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;

            line = br.readLine();
            user = line;
            fr.close();    //closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();
        }

        SENDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(FileWriter fw = new FileWriter("src\\Messages.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                    out.println(user);
                    out.println(Receiver.getText());
                    out.println(textArea1.getText());
                    out.println("");
                }
                catch (IOException err) {
                    err.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Messsage sent to " + Receiver.getText());
                frame.dispose();
                new CustomerMessages();
            }
        });

        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CustomerMessages();
            }
        });
    }
}
