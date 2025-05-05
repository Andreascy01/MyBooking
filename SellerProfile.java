import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class SellerProfile {
    private JPanel panel1;
    private JLabel username;
    private JLabel password;
    private JLabel name;
    private JLabel email;
    private JLabel tel;
    private JButton BACKButton;
    private JLabel verified;
    private JLabel city;
    private JButton EDITButton;
    private JLabel Reservations;
    private JLabel Cancellations;
    private final JFrame frame;
    private String user, pass;


    public SellerProfile()
    {


        HashMap<String, Seller> registrySeller1 = new HashMap<>();
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
                registrySeller1.put(seller.getUsername(), seller);
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
        name.setText(registrySeller1.get(user).getName());
        city.setText(registrySeller1.get(user).getCity());
        email.setText(registrySeller1.get(user).getEmail());
        tel.setText(registrySeller1.get(user).getTel());
        String ver=Boolean.toString(registrySeller1.get(user).getVerified());
        verified.setText(ver);
        String res=Integer.toString(registrySeller1.get(user).getNumofreserves());
        Reservations.setText(res);
        String canc=Integer.toString(registrySeller1.get(user).getNumofcancels());
        Cancellations.setText(canc);


        frame = new JFrame("SELLER PROFILE");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 350));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SellerActions();
            }
        });


        EDITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new EditSellerProfile();
            }
        });
    }
}
