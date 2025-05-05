import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CustomerMessages {

    private JList<Message> MessageJlist;
    private JPanel panel1;
    private JScrollPane scrollpane;
    private JSplitPane splitpane;
    private JPanel panel;
    private JLabel label;
    private JButton SENDMESSAGEButton;
    private JButton BACKButton;
    private JTextArea textArea1;
    String user;

    public CustomerMessages() {

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

        DefaultListModel<Message> messagelist = new DefaultListModel<>();
        try {
            File file = new File("src\\Messages.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line, temp = "";

            line = br.readLine();
            while (line != null) {
                Message message = new Message();
                message.setSender(line);
                line = br.readLine();
                message.setReceiver(line);
                line = br.readLine();
                temp = "";
                while (line != null && line.length() > 0) {
                    temp += line + "\n";
                    line = br.readLine();
                }
                message.setText(temp);
                line = br.readLine();

                if (message.getReceiver().equals(user)) {
                    messagelist.addElement(message);
                }
            }
            fr.close();    //closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();
        }

        MessageJlist.getSelectionModel().addListSelectionListener(e -> {
            Message p = MessageJlist.getSelectedValue();
            textArea1.setText("FROM :\t" + p.getSender() + "\n\n" + p.getText());
        });

        textArea1.setEditable(false);

        MessageJlist.setModel(messagelist);
        scrollpane.setViewportView(MessageJlist);

        MessageJlist.setVisibleRowCount(4);

        JFrame frame = new JFrame("MESSAGES");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 350));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CustomerActions();
            }
        });

        SENDMESSAGEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CustomerSend();
            }
        });


    }
}