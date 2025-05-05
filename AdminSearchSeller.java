import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AdminSearchSeller {

    private JTextArea textArea1;
    private JTextArea textArea2;
    private JList<Room> EstateJList;
    private JScrollPane scrollpane;
    private JLabel label;
    private JPanel panel2;
    private JPanel panel1;
    private JButton BACKButton;
    private final JFrame frame;

    public AdminSearchSeller(Seller seller) {

        textArea1.setText("Username:\t" + seller.getUsername() + "\nName:\t" + seller.getName() + "\nCity\t"
                + seller.getCity() + "\nEmail:\t" + seller.getEmail() + "\nTel:\t" + seller.getTel() +
                "\nVerified:\t" + seller.getVerified() +"\nNumber of Reservations:\t" + seller.getNumofreserves()
                + "\nNumber of Cancellations\t" + seller.getNumofcancels());

        textArea1.setEditable(false);

        DefaultListModel<Room> estatelist = new DefaultListModel<>();
        try {
            File file = new File("src\\Estates.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;

            line = br.readLine();
            while (line != null) {
                Room room = new Room();
                room.setTitle(line);
                line = br.readLine();
                room.setOwner(line);
                line = br.readLine();
                room.setCity(line);
                line = br.readLine();
                int ppl=Integer.parseInt(line);
                room.setPeople(ppl);
                line = br.readLine();
                int r=Integer.parseInt(line);
                room.setRooms(r);
                line = br.readLine();
                int d1=Integer.parseInt(line);
                line = br.readLine();
                int m1=Integer.parseInt(line);
                line = br.readLine();
                int y1=Integer.parseInt(line);
                Date date1 = new Date();
                date1.setDate(d1, m1, y1);
                room.setDate1(date1);
                line = br.readLine();
                int d2=Integer.parseInt(line);
                line = br.readLine();
                int m2=Integer.parseInt(line);
                line = br.readLine();
                int y2=Integer.parseInt(line);
                Date date2 = new Date();
                date2.setDate(d2, m2, y2);
                room.setDate2(date2);
                line = br.readLine();
                double pr=Double.parseDouble(line);
                room.setPrice(pr);
                line = br.readLine();

                if (room.getOwner().equals(seller.getUsername())) {
                    estatelist.addElement(room);
                }
            }
            fr.close();    //closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();
        }


        EstateJList.getSelectionModel().addListSelectionListener(e -> {
            Room p = EstateJList.getSelectedValue();
            textArea2.setText("Title:\t" + p.getTitle() + "\nCity:\t" + p.getCity() + "\nPeople:\t" + p.getPeople()
                    + "\nRooms:\t" + p.getRooms() + "\nFrom:\t" + p.Date1toString() + "\nUntil:\t" + p.Date2toString()
                    + "\nPrice:\t" + p.getPrice());
        });

        textArea2.setEditable(false);

        EstateJList.setModel(estatelist);
        scrollpane.setViewportView(EstateJList);

        EstateJList.setVisibleRowCount(4);




        frame = new JFrame(seller.getUsername() + " (seller)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                new AdminsViewUsers();
            }
        });
    }
}
