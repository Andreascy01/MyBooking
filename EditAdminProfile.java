import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EditAdminProfile {
    private JPanel panel1;
    private JButton CANCELButton;
    private JButton APPLYButton;
    private JTextField username;
    private JTextField password;
    private JTextField name;
    private JTextField email;
    private JTextField tel;
    private final JFrame frame;
    private final HashMap<String, Admin> RegistryAdmin1;
    String user, pass;

    public EditAdminProfile()
    {
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
        name.setText(RegistryAdmin1.get(user).getName());
        email.setText(RegistryAdmin1.get(user).getEmail());
        tel.setText(RegistryAdmin1.get(user).getTel());






        frame = new JFrame("EDIT ADMIN PROFILE");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 350));
        frame.setResizable(false);




        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        CANCELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminProfile();
            }
        });

        APPLYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistryAdmin1.remove(user);
                Admin admin = new Admin();
                admin.setUsername(username.getText());
                admin.setPassword(password.getText());
                admin.setName(name.getText());
                admin.setEmail(email.getText());
                admin.setTel(tel.getText());
                RegistryAdmin1.put(admin.getUsername(), admin);

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
                    file = new File("src\\Admins.txt");
                    PrintWriter writer = new PrintWriter(file);
                    writer.print("");
                    writer.close();
                } catch (IOException er) {
                    er.printStackTrace();
                }

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Admins.txt"));
                    for (Map.Entry<String, Admin> adm : RegistryAdmin1.entrySet()) {
                        writer.write(adm.getKey() + "\n");
                        writer.write(adm.getValue().getPassword() + "\n");
                        writer.write(adm.getValue().getName() + "\n");
                        writer.write(adm.getValue().getEmail() + "\n");
                        writer.write(adm.getValue().getTel() + "\n");

                    }
                    writer.close();
                }
                catch (IOException err) {
                    err.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Profile Edited Successfully");
                frame.dispose();
                new AdminProfile();

            }
        });
    }
}
