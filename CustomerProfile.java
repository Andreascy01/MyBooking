import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CustomerProfile extends Site {
    private JPanel panel1;
    private JLabel username;
    private JLabel password;
    private JLabel name;
    private JLabel address;
    private JLabel email;
    private JLabel tel;
    private JLabel verified;
    private JButton BACKButton;
    private JButton EDITButton;
    private final JFrame frame;
    private String user, pass;

    public CustomerProfile() {
        frame = new JFrame("CUSTOMER PROFILE");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 350));
        frame.setResizable(false);

        HashMap<String, Customer> registryCustomer1 = new HashMap<>();
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
                registryCustomer1.put(customer.getUsername(), customer);
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
        name.setText(registryCustomer1.get(user).getName());
        address.setText(registryCustomer1.get(user).getAddress());
        email.setText(registryCustomer1.get(user).getEmail());
        tel.setText(registryCustomer1.get(user).getTel());
        String ver=Boolean.toString(registryCustomer1.get(user).getVerified());
        verified.setText(ver);


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

        EDITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new EditCustomerProfile();
            }
        });
    }
}
