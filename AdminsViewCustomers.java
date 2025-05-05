import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AdminsViewCustomers  {

    private final DefaultListModel<Customer> customerlist = new DefaultListModel<>();
    private JList<Customer> customerJlist;
    private JPanel panel1;
    private JScrollPane scrollpane;
    private JSplitPane splitpane;
    private JPanel panel;
    private JLabel label;
    private JButton VERIFYButton;
    private JButton BACKButton;
    private JTextArea textArea1;

    public AdminsViewCustomers() {

        try
        {
            File file=new File("src\\Customers.txt");    //creates a new file instance
            FileReader fr=new FileReader(file);   //reads the file
            BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
            String line;

            line = br.readLine();
            while (line != null) {
                Customer customer = new Customer();
                customer.setUsername(line);
                line = br.readLine();
                customer.setPassword(line);
                line = br.readLine();
                customer.setName(line);
                line = br.readLine();
                customer.setAddress(line);
                line = br.readLine();
                customer.setEmail(line);
                line = br.readLine();
                customer.setTel(line);
                line = br.readLine();
                boolean ver=Boolean.parseBoolean(line);
                customer.setVerified(ver);
                line = br.readLine();
                customerlist.addElement(customer);
            }
            fr.close();    //closes the stream and release the resources
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        customerJlist.getSelectionModel().addListSelectionListener(e -> {
            Customer p = customerJlist.getSelectedValue();
            textArea1.setText("Name:\t" + p.getName() + "\nUsername:\t" + p.getUsername() + "\nPassword:\t" + p.getPassword()
            + "\nAddress:\t" + p.getAddress() + "\nEmail:\t" + p.getEmail() + "\nTel:\t" + p.getTel() + "\nVerified:\t" + p.getVerified());
        });

        textArea1.setEditable(false);

        customerJlist.setModel(customerlist);
        scrollpane.setViewportView(customerJlist);

        customerJlist.setVisibleRowCount(4);

        JFrame frame = new JFrame("CUSTOMERS");
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

                customerJlist.getSelectionModel().addListSelectionListener(q -> {
                    Customer p = customerJlist.getSelectedValue();
                    String puser = p.getUsername();


                    for (int i=0; i<customerlist.size(); i++) {
                        if (customerlist.get(i).getUsername().equals(puser)) {
                            customerlist.get(i).setVerified(true);
                            break;
                        }
                    }
                });

                File file = new File("src\\Customers.txt");
                try {
                    PrintWriter writer = new PrintWriter(file);
                    writer.print("");
                    writer.close();
                } catch (IOException er) {
                    er.printStackTrace();
                }

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Customers.txt"));
                    for (int i=0; i<customerlist.size(); i++) {
                        writer.write(customerlist.get(i).getUsername() + "\n");
                        writer.write(customerlist.get(i).getPassword() + "\n");
                        writer.write(customerlist.get(i).getName() + "\n");
                        writer.write(customerlist.get(i).getAddress() + "\n");
                        writer.write(customerlist.get(i).getEmail() + "\n");
                        writer.write(customerlist.get(i).getTel() + "\n");
                        writer.write("true\n");
                    }
                    writer.close();
                }
                catch (IOException err) {
                    err.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "User Verified");
                frame.dispose();
                new AdminsViewCustomers();
            }
        });
    }

}
