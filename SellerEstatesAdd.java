import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SellerEstatesAdd extends Site{
    private JPanel panel1;
    private JButton CANCELButton;
    private JButton ADDButton;
    private JTextField Title;
    private JTextField City;
    private JTextField People;
    private JTextField Rooms;
    private JTextField Price;
    private JTextField FromD;
    private JTextField FromM;
    private JTextField FromY;
    private JTextField UntilD;
    private JTextField UntilM;
    private JTextField UntilY;
    private final JFrame frame;
    private String user;

    public SellerEstatesAdd() {

        try {
            File file = new File("src\\Login.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;

            line = br.readLine();
            user = line;
            fr.close();    //closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame = new JFrame("ADD ESTATE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 350));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int fd = Integer.parseInt(FromD.getText());
                int fm = Integer.parseInt(FromM.getText());
                int fy = Integer.parseInt(FromY.getText());
                int ud = Integer.parseInt(UntilD.getText());
                int um = Integer.parseInt(UntilM.getText());
                int uy = Integer.parseInt(UntilY.getText());
                Date date1 = new Date(), date2 = new Date();
                date1.setDate(fd, fm, fy);
                date2.setDate(ud, um, uy);

                if (DateCheck(fd, fm, fy) && DateCheck(ud, um, uy) && (((Dateinto365(date1) < Dateinto365(date2)) && fy==uy) || fy<uy)) {


                    try (FileWriter fw = new FileWriter("src\\Estates.txt", true);
                         BufferedWriter bw = new BufferedWriter(fw);
                         PrintWriter out = new PrintWriter(bw)) {
                        out.println(Title.getText());
                        out.println(user);
                        out.println(City.getText());
                        out.println(People.getText());
                        out.println(Rooms.getText());
                        out.println(FromD.getText());
                        out.println(FromM.getText());
                        out.println(FromY.getText());
                        out.println(UntilD.getText());
                        out.println(UntilM.getText());
                        out.println(UntilY.getText());
                        out.println(Price.getText());
                    } catch (IOException err) {
                        err.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, "Estate Added Successfully");
                    frame.dispose();
                    new SellerEstates();
                }

                else {
                    JOptionPane.showMessageDialog(null, "Dates are not Viable, Try Again!");
                }
            }
        });

        CANCELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SellerEstates();
            }
        });
    }
}
