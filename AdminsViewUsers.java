import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class AdminsViewUsers {
    private JButton SELLERSButton;
    private JPanel panel1;
    private JButton CUSTOMERSButton;
    private JButton BACKButton;
    private JButton SEARCHButton;
    private JTextField textField1;
    private final JFrame frame;
    private final HashMap<String,Seller> RegistrySeller1;
    private final HashMap<String,Customer> RegistryCustomer1;

    public AdminsViewUsers() {


        RegistrySeller1 = new HashMap<>();
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


        frame = new JFrame("USERS");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 350));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        SELLERSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminsViewSellers();
            }
        });

        CUSTOMERSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminsViewCustomers();
            }
        });

        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminActions();
            }
        });

        SEARCHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (RegistrySeller1.containsKey(textField1.getText())) {
                    new AdminSearchSeller(RegistrySeller1.get(textField1.getText()));
                    frame.dispose();

                }
                else if (RegistryCustomer1.containsKey(textField1.getText())) {
                    frame.dispose();
                    new AdminSearchCustomer(RegistryCustomer1.get(textField1.getText()));
                }
                else {
                    frame.dispose();
                    JOptionPane.showMessageDialog(null, "User Does Not Exist, Try Again!");
                    new AdminsViewUsers();
                }
            }
        });
    }
}
