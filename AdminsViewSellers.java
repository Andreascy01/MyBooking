import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AdminsViewSellers  {

    private final DefaultListModel<Seller> sellerlist = new DefaultListModel<>();
    private JList<Seller> sellerJlist;
    private JPanel panel1;
    private JScrollPane scrollpane;
    private JSplitPane splitpane;
    private JPanel panel;
    private JLabel label;
    private JButton VERIFYButton;
    private JButton BACKButton;
    private JTextArea textArea1;

    public AdminsViewSellers() {

        try
        {
            File file=new File("src\\Sellers.txt");    //creates a new file instance
            FileReader fr=new FileReader(file);   //reads the file
            BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
            String line;

            line = br.readLine();
            while (line != null) {
                Seller seller = new Seller();
                seller.setUsername(line);
                line = br.readLine();
                seller.setPassword(line);
                line = br.readLine();
                seller.setName(line);
                line = br.readLine();
                seller.setCity(line);
                line = br.readLine();
                seller.setEmail(line);
                line = br.readLine();
                seller.setTel(line);
                line = br.readLine();
                boolean ver=Boolean.parseBoolean(line);
                seller.setVerified(ver);
                line = br.readLine();
                int rev=Integer.parseInt(line);
                seller.setNumofreserves(rev);
                line = br.readLine();
                int canc=Integer.parseInt(line);
                seller.setNumofcancels(canc);
                line = br.readLine();
                sellerlist.addElement(seller);
            }
            fr.close();    //closes the stream and release the resources
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        sellerJlist.getSelectionModel().addListSelectionListener(e -> {
            Seller p = sellerJlist.getSelectedValue();
            textArea1.setText("Name:\t" + p.getName() + "\nUsername:\t" + p.getUsername() + "\nPassword:\t" + p.getPassword()
                    + "\nCity:\t" + p.getCity() + "\nEmail:\t" + p.getEmail() + "\nTel:\t" + p.getTel() + "\nVerified:\t" + p.getVerified()
                    + "\nReservations:\t" + p.getNumofreserves() + "\nCancellations:\t" + p.getNumofcancels());
        });

        textArea1.setEditable(false);

        sellerJlist.setModel(sellerlist);
        scrollpane.setViewportView(sellerJlist);

        sellerJlist.setVisibleRowCount(4);

        JFrame frame = new JFrame("SELLERS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400,350));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminsViewUsers();
            }
        });

        VERIFYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sellerJlist.getSelectionModel().addListSelectionListener(q -> {
                    Seller p = sellerJlist.getSelectedValue();
                    String puser = p.getUsername();


                    for (int i=0; i<sellerlist.size(); i++) {
                        if (sellerlist.get(i).getUsername().equals(puser)) {
                            sellerlist.get(i).setVerified(true);
                            break;
                        }
                    }
                });

                File file = new File("src\\Sellers.txt");
                try {
                    PrintWriter writer = new PrintWriter(file);
                    writer.print("");
                    writer.close();
                } catch (IOException er) {
                    er.printStackTrace();
                }

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Sellers.txt"));
                    for (int i=0; i<sellerlist.size(); i++) {
                        writer.write(sellerlist.get(i).getUsername() + "\n");
                        writer.write(sellerlist.get(i).getPassword() + "\n");
                        writer.write(sellerlist.get(i).getName() + "\n");
                        writer.write(sellerlist.get(i).getCity() + "\n");
                        writer.write(sellerlist.get(i).getEmail() + "\n");
                        writer.write(sellerlist.get(i).getTel() + "\n");
                        writer.write("true\n");
                        writer.write(sellerlist.get(i).getNumofreserves() + "\n");
                        writer.write(sellerlist.get(i).getNumofcancels() + "\n");
                    }
                    writer.close();
                }
                catch (IOException err) {
                    err.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "User Verified");
                frame.dispose();
                new AdminsViewSellers();
            }
        });
    }

}
