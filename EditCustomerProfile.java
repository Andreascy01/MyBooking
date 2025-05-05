import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EditCustomerProfile {
    private JPanel panel1;
    private JButton CANCELButton;
    private JLabel verified;
    private JButton APPLYButton;
    private JTextField username;
    private JTextField password;
    private JTextField name;
    private JTextField address;
    private JTextField email;
    private JTextField tel;
    private final JFrame frame;
    private final HashMap<String, Customer> RegistryCustomer1;
    String user, pass;

    public EditCustomerProfile()
    {


        RegistryCustomer1 = new HashMap<>();
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
                RegistryCustomer1.put(customer.getUsername(), customer);
            }
            fr.close();    //closes the stream and release the resources
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        try {
            File file = new File("src\\Login.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream

            user = br.readLine();
            pass = br.readLine();
            fr.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        username.setText(user);
        password.setText(pass);
        name.setText(RegistryCustomer1.get(user).getName());
        address.setText(RegistryCustomer1.get(user).getAddress());
        email.setText(RegistryCustomer1.get(user).getEmail());
        tel.setText(RegistryCustomer1.get(user).getTel());
        String ver=Boolean.toString(RegistryCustomer1.get(user).getVerified());
        verified.setText(ver);

        frame = new JFrame("EDIT CUSTOMER PROFILE");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 350));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        APPLYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistryCustomer1.remove(user);
                Customer customer = new Customer();
                customer.setUsername(username.getText());
                customer.setPassword(password.getText());
                customer.setName(name.getText());
                customer.setAddress(address.getText());
                customer.setEmail(email.getText());
                customer.setTel(tel.getText());
                customer.setVerified(true);
                RegistryCustomer1.put(customer.getUsername(), customer);

                File file = new File("src\\Login.txt");
                try {
                    PrintWriter writer = new PrintWriter(file);
                    writer.print("");
                    writer.close();
                } catch (IOException er) {
                    er.printStackTrace();
                }

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Login.txt"));
                    writer.write(username.getText() + "\n");
                    writer.write(password.getText() + "\n");
                    writer.close();
                }
                catch(IOException er) {
                    er.printStackTrace();
                }

                try {
                    file = new File("src\\Customers.txt");
                    PrintWriter writer = new PrintWriter(file);
                    writer.print("");
                    writer.close();
                } catch (IOException er) {
                    er.printStackTrace();
                }

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Customers.txt"));
                    for (Map.Entry<String, Customer> cust : RegistryCustomer1.entrySet()) {
                        writer.write(cust.getKey() + "\n");
                        writer.write(cust.getValue().getPassword() + "\n");
                        writer.write(cust.getValue().getName() + "\n");
                        writer.write(cust.getValue().getAddress() + "\n");
                        writer.write(cust.getValue().getEmail() + "\n");
                        writer.write(cust.getValue().getTel() + "\n");
                        writer.write(cust.getValue().getVerified() + "\n");
                    }
                    writer.close();
                }
                catch (IOException err) {
                    err.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Profile Edited Successfully");
                frame.dispose();
                new CustomerProfile();

            }
        });





        CANCELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CustomerProfile();
                }
            });
        }
    }
