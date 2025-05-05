import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class AdminProfile extends Site{
    private JPanel panel1;
    private JLabel username;
    private JLabel password;
    private JLabel name;
    private JLabel email;
    private JLabel tel;
    private JButton BACKButton;
    private JButton EDITButton;
    private final JFrame frame;
    private String user, pass;

    public AdminProfile()
    {
        frame = new JFrame("ADMIN PROFILE");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 350));
        frame.setResizable(false);

        HashMap<String, Admin> registryAdmin1 = new HashMap<>();

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
                registryAdmin1.put(admin.getUsername(), admin);
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
        name.setText(registryAdmin1.get(user).getName());
        email.setText(registryAdmin1.get(user).getEmail());
        tel.setText(registryAdmin1.get(user).getTel());


        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        EDITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new EditAdminProfile();
            }
        });

        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminActions();
            }
        });
    }
}
