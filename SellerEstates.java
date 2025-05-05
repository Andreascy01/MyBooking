import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SellerEstates {
    private JPanel panel1;
    private JTextArea textArea1;
    private final DefaultListModel<Room> estatelist = new DefaultListModel<>();
    private DefaultListModel<Room> estatelist2 = new DefaultListModel<>();
    private JList<Room> estateJlist;
    private JButton SEARCHButton;
    private JButton EDITButton;
    private JButton BACKButton;
    private JButton DELETEButton;
    private JButton ADDButton;
    private JScrollPane scrollpane;
    private JButton UNDOSEARCHButton;
    private JTextField textField1;
    private final JFrame frame;
    private String seller;
    Room p;
    private HashMap<String,Room> RegistryEstate;

    public SellerEstates() {


        try {
            File file = new File("src\\Login.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            seller = br.readLine();
            fr.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

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
                room.setAvailable(true);

                if (room.getOwner().equals(seller)) {
                    estatelist.addElement(room);
                }
            }
            fr.close();    //closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File file = new File("src\\Reservations.txt");    //creates a new file instance
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
                room.setAvailable(false);

                if (room.getOwner().equals(seller)) {
                    estatelist.addElement(room);
                }
            }
            fr.close();    //closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();
        }

        estateJlist.getSelectionModel().addListSelectionListener(e -> {

            p = estateJlist.getSelectedValue();
            if (p!=null) {
                textArea1.setText("Title:\t" + p.getTitle() + "\nCity:\t" + p.getCity() + "\nPeople:\t" + p.getPeople()
                        + "\nRooms:\t" + p.getRooms() + "\nFrom:\t" + p.Date1toString() + "\nUntil:\t" + p.Date2toString()
                        + "\nPrice:\t" + p.getPrice() + "\nAvailable:\t" + p.isAvailable());
            }
        });



        textArea1.setEditable(false);

        estateJlist.setModel(estatelist);
        scrollpane.setViewportView(estateJlist);

        estateJlist.setVisibleRowCount(4);




        frame = new JFrame("YOUR ESTATES");
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
                new SellerActions();
            }
        });

        SEARCHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Room SEstate = new Room();
                boolean found = false;
                for (int i=0; i< estatelist.size(); i++) {
                    if (estatelist.get(i).getTitle().equals(textField1.getText())) {
                        SEstate = estatelist.get(i);
                        found = true;
                        break;
                    }
                }

                if (found) {
                    estatelist2 = new DefaultListModel<>();
                    estatelist2.addElement(SEstate);
                    textArea1.setText("");
                    estateJlist.setModel(estatelist2);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Estate does not Exist or it's not Yours.");
                }
            }
        });

        UNDOSEARCHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
                estateJlist.setModel(estatelist);
            }
        });

        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SellerEstatesAdd();
            }
        });

        EDITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (p != null && p.isAvailable()) {
                    frame.dispose();
                    new SellerEstatesEdit(p);
                }
                else if(p!=null && !p.isAvailable()) {
                    JOptionPane.showMessageDialog(null, "You can't Edit an Already Reserved Estate.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Nothing is Selected to Edit.");
                }
            }
        });

        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (p != null && p.isAvailable()) {
                    RegistryEstate = new HashMap<>();
                    File file=new File("src\\Estates.txt");    //creates a new file instance
                    try
                    {
                        FileReader fr=new FileReader(file);   //reads the file
                        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
                        String line;

                        line = br.readLine();
                        while (line != null) {
                            Room rom = new Room();
                            rom.setTitle(line);
                            line = br.readLine();
                            rom.setOwner(line);
                            line = br.readLine();
                            rom.setCity(line);
                            line = br.readLine();
                            int ppl=Integer.parseInt(line);
                            rom.setPeople(ppl);
                            line = br.readLine();
                            int rooms=Integer.parseInt(line);
                            rom.setRooms(rooms);
                            line = br.readLine();
                            int fd = Integer.parseInt(line);
                            line = br.readLine();
                            int fm = Integer.parseInt(line);
                            line = br.readLine();
                            int fy = Integer.parseInt(line);
                            line = br.readLine();
                            int ud = Integer.parseInt(line);
                            line = br.readLine();
                            int um = Integer.parseInt(line);
                            line = br.readLine();
                            int uy = Integer.parseInt(line);
                            Date date1 = new Date(), date2 = new Date();
                            date1.setDate(fd, fm, fy);
                            date2.setDate(ud, um, uy);
                            rom.setDate1(date1);
                            rom.setDate2(date2);
                            line = br.readLine();
                            double pr = Double.parseDouble(line);
                            rom.setPrice(pr);
                            line = br.readLine();
                            RegistryEstate.put(rom.getTitle(),rom);
                        }
                        fr.close();    //closes the stream and release the resources
                    }
                    catch(IOException er)
                    {
                        er.printStackTrace();
                    }

                    int result = JOptionPane.showConfirmDialog(frame,"Are you sure you want to Delete this Estate?", "DELETE WARNING",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (result == JOptionPane.YES_OPTION) {
                        RegistryEstate.remove(p.getTitle());

                        File fil = new File("src\\Estates.txt");
                        try {
                            PrintWriter writer = new PrintWriter(fil);
                            writer.print("");
                            writer.close();
                        } catch (IOException er) {
                            er.printStackTrace();
                        }

                        try {
                            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Estates.txt"));
                            for (Map.Entry<String, Room> estate : RegistryEstate.entrySet()) {
                                writer.write(estate.getKey() + "\n");
                                writer.write(estate.getValue().getOwner() + "\n");
                                writer.write(estate.getValue().getCity() + "\n");
                                writer.write(estate.getValue().getPeople() + "\n");
                                writer.write(estate.getValue().getRooms() + "\n");
                                writer.write(estate.getValue().getDate1().getDay() + "\n");
                                writer.write(estate.getValue().getDate1().getMonth() + "\n");
                                writer.write(estate.getValue().getDate1().getYear() + "\n");
                                writer.write(estate.getValue().getDate2().getDay() + "\n");
                                writer.write(estate.getValue().getDate2().getMonth() + "\n");
                                writer.write(estate.getValue().getDate2().getYear() + "\n");
                                writer.write(estate.getValue().getPrice() + "\n");
                            }
                            writer.close();
                        }
                        catch (IOException err) {
                            err.printStackTrace();
                        }

                        JOptionPane.showMessageDialog(null, "Estate has been Deleted Successfully!");
                        frame.dispose();
                        new SellerEstates();
                    }
                }

                else if (p != null && !p.isAvailable()) {
                    JOptionPane.showMessageDialog(null, "Estate is Reserved so it cannot be Deleted.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Nothing is Selected to Delete.");
                }
            }
        });
    }
}
