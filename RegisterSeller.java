import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RegisterSeller extends Site{
    private JPanel panel1;
    private JLabel Username;
    private JTextField username;
    private JLabel Password;
    private JTextField password;
    private JLabel Name;
    private JTextField name;
    private JLabel City;
    private JTextField city;
    private JLabel Email;
    private JLabel Tel;
    private JTextField email;
    private JTextField tel;
    private JButton CANCELButton;
    private JButton REGISTERButton;
    private final JFrame frame;
    private final HashMap<String, Customer> RegistryCustomer1;
    private final HashMap<String, Admin> RegistryAdmin1;
    private final HashMap<String, Seller> RegistrySeller1;

    public RegisterSeller(){

        RegistryAdmin1 = new HashMap<>();
        try
        {
            File file=new File("src\\Admins.txt");    //creates a new file instance
            FileReader fr=new FileReader(file);   //reads the file
            BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
            String line;

            line = br.readLine();
            while (line != null) {
                Admin admin = new Admin();
                admin.setUsername(line);
                line = br.readLine();
                admin.setPassword(line);
                line = br.readLine();
                admin.setName(line);
                line = br.readLine();
                admin.setEmail(line);
                line = br.readLine();
                admin.setTel(line);
                line = br.readLine();
                RegistryAdmin1.put(admin.getUsername(), admin);
            }
            fr.close();    //closes the stream and release the resources
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


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


        RegistrySeller1 = new HashMap<>();
        File file=new File("src\\Sellers.txt");    //creates a new file instance
        try
        {
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
                int rev1=Integer.parseInt(line);
                seller.setNumofreserves(rev1);
                line = br.readLine();
                int rev2=Integer.parseInt(line);
                seller.setNumofcancels(rev2);
                line = br.readLine();
                RegistrySeller1.put(seller.getUsername(), seller);
            }
            fr.close();    //closes the stream and release the resources
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


        frame=new JFrame("REGISTER (SELLER)");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400,350));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        CANCELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new BeginSite();
            }
        });

        REGISTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Seller seller = sellerRegister(username.getText(), password.getText(), name.getText(), city.getText(), email.getText(), tel.getText());
                if (RegistrySeller1.containsKey(seller.getUsername()) || RegistryAdmin1.containsKey(seller.getUsername()) || RegistryCustomer1.containsKey(seller.getUsername())) {
                    JOptionPane.showMessageDialog(null, "Username Already Exists, Try Again!");
                }
                else {
                    RegistrySeller1.put(seller.getUsername(), seller);
                    try {
                        PrintWriter writer = new PrintWriter(file);
                        writer.print("");
                        writer.close();
                    } catch (IOException er) {
                        er.printStackTrace();
                    }

                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Sellers.txt"));
                        for (Map.Entry<String, Seller> sell : RegistrySeller1.entrySet()) {
                            writer.write(sell.getKey() + "\n");
                            writer.write(sell.getValue().getPassword() + "\n");
                            writer.write(sell.getValue().getName() + "\n");
                            writer.write(sell.getValue().getCity() + "\n");
                            writer.write(sell.getValue().getEmail() + "\n");
                            writer.write(sell.getValue().getTel() + "\n");
                            writer.write(sell.getValue().getVerified() + "\n");
                            writer.write(sell.getValue().getNumofreserves() + "\n");
                            writer.write(sell.getValue().getNumofcancels() + "\n");
                        }
                        writer.close();
                    }
                    catch (IOException err) {
                        err.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, "Register Successful, Wait for Account Verification");
                    frame.dispose();
                    new BeginSite();
                }
            }
        });
    }
}

