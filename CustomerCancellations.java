import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CustomerCancellations {
    private JPanel panel1;
    private JTextArea textArea1;
    private JList<Reserve> cancelJlist;
    private JButton BACKButton;
    private JScrollPane scrollpane;
    private String customer;
    private Reserve p;
    private final JFrame frame;

    public CustomerCancellations() {
        try {
            File file = new File("src\\Login.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            customer = br.readLine();
            fr.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        DefaultListModel<Reserve> cancellist = new DefaultListModel<>();
        try {
            File file = new File("src\\Cancellations.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;

            line = br.readLine();
            while (line != null) {
                Reserve reserve = new Reserve();
                reserve.setTitle(line);
                line = br.readLine();
                reserve.setOwner(line);
                line = br.readLine();
                reserve.setCustomer(line);
                line = br.readLine();
                reserve.setCity(line);
                line = br.readLine();
                int ppl=Integer.parseInt(line);
                reserve.setPeople(ppl);
                line = br.readLine();
                int r=Integer.parseInt(line);
                reserve.setRooms(r);
                line = br.readLine();
                int d1=Integer.parseInt(line);
                line = br.readLine();
                int m1=Integer.parseInt(line);
                line = br.readLine();
                int y1=Integer.parseInt(line);
                Date date1 = new Date();
                date1.setDate(d1, m1, y1);
                reserve.setDate1(date1);
                line = br.readLine();
                int d2=Integer.parseInt(line);
                line = br.readLine();
                int m2=Integer.parseInt(line);
                line = br.readLine();
                int y2=Integer.parseInt(line);
                Date date2 = new Date();
                date2.setDate(d2, m2, y2);
                reserve.setDate2(date2);
                line = br.readLine();
                double pr=Double.parseDouble(line);
                reserve.setPrice(pr);
                line = br.readLine();

                if (reserve.getCustomer().equals(customer)) {
                    cancellist.addElement(reserve);
                }
            }
            fr.close();    //closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();
        }


        cancelJlist.getSelectionModel().addListSelectionListener(e -> {

            p = cancelJlist.getSelectedValue();
            if (p!=null) {
                textArea1.setText("Title:\t" + p.getTitle() + "\nOwner:\t" + p.getOwner() + "\nCity:\t" + p.getCity()
                        + "\nPeople:\t" + p.getPeople()
                        + "\nRooms:\t" + p.getRooms() + "\nFrom:\t" + p.Date1toString() + "\nUntil:\t" + p.Date2toString()
                        + "\nPrice:\t" + p.getPrice());
            }
        });



        textArea1.setEditable(false);

        cancelJlist.setModel(cancellist);
        scrollpane.setViewportView(cancelJlist);

        cancelJlist.setVisibleRowCount(4);


        frame = new JFrame("YOUR CANCELLATIONS");
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
                new CustomerActions();
            }
        });
    }
}
