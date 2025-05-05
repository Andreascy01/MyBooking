import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EditSellerProfile {
    private JPanel panel1;
    private JButton CANCELButton;
    private JLabel verified;
    private JButton APPLYButton;
    private JLabel Reservations;
    private JLabel Cancellations;
    private JTextField username;
    private JTextField password;
    private JTextField name;
    private JTextField city;
    private JTextField email;
    private JTextField tel;
    private final JFrame frame;
    private final HashMap<String, Seller> RegistrySeller1;
    String user, pass;

    public EditSellerProfile()
    {
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
        name.setText(RegistrySeller1.get(user).getName());
        city.setText(RegistrySeller1.get(user).getCity());
        email.setText(RegistrySeller1.get(user).getEmail());
        tel.setText(RegistrySeller1.get(user).getTel());
        String ver=Boolean.toString(RegistrySeller1.get(user).getVerified());
        verified.setText(ver);
        String res=Integer.toString(RegistrySeller1.get(user).getNumofreserves());
        Reservations.setText(res);
        String canc=Integer.toString(RegistrySeller1.get(user).getNumofcancels());
        Cancellations.setText(canc);

        frame = new JFrame("EDIT SELLER PROFILE");
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
                RegistrySeller1.remove(user);
               Seller seller = new Seller();
                seller.setUsername(username.getText());
                seller.setPassword(password.getText());
                seller.setName(name.getText());
                seller.setCity(city.getText());
                seller.setEmail(email.getText());
                seller.setTel(tel.getText());
                seller.setVerified(true);
                int res1=Integer.parseInt(Reservations.getText());
                seller.setNumofreserves(res1);
                int canc2=Integer.parseInt(Cancellations.getText());
                seller.setNumofcancels(canc2);
                RegistrySeller1.put(seller.getUsername(), seller);

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
                    file = new File("src\\Sellers.txt");
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

                JOptionPane.showMessageDialog(null, "Profile Edited Successfully");
                frame.dispose();
                new SellerProfile();

            }
        });


        CANCELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SellerProfile();
            }
        });
    }

}
