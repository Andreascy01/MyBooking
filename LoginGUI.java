import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;

public class LoginGUI extends Site{
    private JPanel LoginWindow;
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton;
    private JButton cancelButton;
    private final JFrame frame;
    private final HashMap<String, Admin> RegistryAdmin1;
    private final HashMap<String, Seller> RegistrySeller1;
    private final HashMap<String, Customer> RegistryCustomer1;

    public LoginGUI() {

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




        frame=new JFrame("Login Frame");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400,350));
        frame.setResizable(false);

        frame.add(LoginWindow);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if(login(username.getText(), password.getText(), RegistryAdmin1, RegistrySeller1, RegistryCustomer1)){
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

                            JOptionPane.showMessageDialog(null,"Login Successful");
                            frame.dispose();
                            if(RegistryAdmin1.containsKey(username.getText()))
                                new AdminActions();
                            else if(RegistrySeller1.containsKey(username.getText()))
                                new SellerActions();
                            else if(RegistryCustomer1.containsKey(username.getText()))
                                new CustomerActions();
                    }
                    else {
                        if (RegistrySeller1.containsKey(username.getText()) && RegistrySeller1.get(username.getText()).getPassword().equals(password.getText()) && !RegistrySeller1.get(username.getText()).getVerified()) {
                            JOptionPane.showMessageDialog(null, "Account is Not Verified Yet, Try Again Later!");
                        }
                        else if (RegistryCustomer1.containsKey(username.getText()) && RegistryCustomer1.get(username.getText()).getPassword().equals(password.getText()) && !RegistryCustomer1.get(username.getText()).getVerified()) {
                            JOptionPane.showMessageDialog(null, "Account is Not Verified Yet, Try Again Later!");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Login Failed, Wrong Username or Password!");
                        }
                    }
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); //you can't see me!
                frame.dispose(); //Destroy the JFrame object
                new BeginSite();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
