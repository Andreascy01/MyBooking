import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CustomerEstates extends Site{
    private JPanel panel1;
    private JTextArea textArea1;
    private JList<Room> estateJlist;
    private JTextField search;
    private JButton CITYButton;
    private JButton MAXPRICEButton;
    private JButton SELLERButton;
    private JButton PEOPLEButton;
    private JTextField FromD;
    private JTextField FromM;
    private JButton ROOMSButton;
    private JTextField FromY;
    private JTextField UntilD;
    private JTextField UntilM;
    private JTextField UntilY;
    private JButton BACKButton;
    private JButton SEARCHBYDATESButton;
    private JButton RESERVEESTATEButton;
    private JScrollPane scrollpane;
    private JButton CLEARFILTERSButton;
    private JButton MINPRICEButton;
    private final JFrame frame;
    private DefaultListModel<Room> estatelist = new DefaultListModel<>();
    private Room p, p2;
    private final HashMap<String, Seller> RegistrySeller1;
    private String customer;

    public CustomerEstates() {

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

                estatelist.addElement(room);
            }
            fr.close();    //closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i=0; i<3; i++) {
            String ti, tj;
            for (i = 0; i < estatelist.size() - 1; i++) {
                ti = estatelist.get(i).getTitle();
                for (int j = i + 1; j < estatelist.size(); j++) {
                    tj = estatelist.get(j).getTitle();
                    if (tj.equals(ti)) {

                        if (estatelist.get(i).getDate1().DatetoString().equals(estatelist.get(j).getDate2().DatetoString()) ) {
                            estatelist.get(i).setDate1(estatelist.get(j).getDate1());
                            estatelist.remove(j);
                            j--;
                        }
                        else if (estatelist.get(j).getDate1().DatetoString().equals(estatelist.get(i).getDate2().DatetoString())) {
                            estatelist.get(i).setDate2(estatelist.get(j).getDate2());
                            estatelist.remove(j);
                            j--;
                        }
                    }
                }
            }

        }

        try {
            File file = new File("src\\Estates.txt");
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        } catch (IOException er) {
            er.printStackTrace();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Estates.txt"));
            for (int i = 0; i < estatelist.size(); i++) {
                writer.write(estatelist.get(i).getTitle() + "\n");
                writer.write(estatelist.get(i).getOwner() + "\n");
                writer.write(estatelist.get(i).getCity() + "\n");
                writer.write(estatelist.get(i).getPeople() + "\n");
                writer.write(estatelist.get(i).getRooms() + "\n");
                writer.write(estatelist.get(i).getDate1().getDay() + "\n");
                writer.write(estatelist.get(i).getDate1().getMonth() + "\n");
                writer.write(estatelist.get(i).getDate1().getYear() + "\n");
                writer.write(estatelist.get(i).getDate2().getDay() + "\n");
                writer.write(estatelist.get(i).getDate2().getMonth() + "\n");
                writer.write(estatelist.get(i).getDate2().getYear() + "\n");
                writer.write(estatelist.get(i).getPrice() + "\n");
            }
            writer.close();
        } catch (IOException err) {
            err.printStackTrace();
        }




        estateJlist.getSelectionModel().addListSelectionListener(e -> {

            p = estateJlist.getSelectedValue();
            if (p!=null) {
                textArea1.setText("Title:\t" + p.getTitle() + "\nCity:\t" + p.getCity() + "\nPeople:\t" + p.getPeople()
                        + "\nRooms:\t" + p.getRooms() + "\nFrom:\t" + p.Date1toString() + "\nUntil:\t" + p.Date2toString()
                        + "\nPrice:\t" + p.getPrice() + "\nOwner:\t" + p.getOwner());
            }
        });



        textArea1.setEditable(false);

        estateJlist.setModel(estatelist);
        scrollpane.setViewportView(estateJlist);

        estateJlist.setVisibleRowCount(4);


        frame = new JFrame("ESTATES: SEARCH AND RESERVE");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 650));
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

        RESERVEESTATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (FromD.getText().length()>0 && FromM.getText().length()>0 && FromY.getText().length()>0 &&
                        UntilD.getText().length()>0 && UntilM.getText().length()>0 && UntilY.getText().length()>0) {
                    Date date1 = new Date(), date2 = new Date();
                    int d1 = Integer.parseInt(FromD.getText());
                    int m1 = Integer.parseInt(FromM.getText());
                    int y1 = Integer.parseInt(FromY.getText());
                    int d2 = Integer.parseInt(UntilD.getText());
                    int m2 = Integer.parseInt(UntilM.getText());
                    int y2 = Integer.parseInt(UntilY.getText());
                    date1.setDate(d1, m1, y1);
                    date2.setDate(d2, m2, y2);

                    if (DateCheck(d1, m1, y1) && DateCheck(d2, m2, y2) && (((Dateinto365(date1) < Dateinto365(date2)) && y1 == y2) || y1 < y2)) {

                        if (p != null) {

                            if (((Dateinto365(date1) >= Dateinto365(p.getDate1())) && (Dateinto365(date2) <= Dateinto365(p.getDate2())) && date1.getYear()==p.getDate1().getYear() && date2.getYear()==p.getDate2().getYear())
                                || ((Dateinto365(date1) >= Dateinto365(p.getDate1())) && (Dateinto365(date2) > Dateinto365(p.getDate2())) && date1.getYear()==p.getDate1().getYear() && date2.getYear()<p.getDate2().getYear())
                                    || (date1.getYear()>p.getDate1().getYear()) && (date2.getYear()==p.getDate2().getYear()) && (Dateinto365(date1) < Dateinto365(p.getDate1())) && (Dateinto365(date2) <= Dateinto365(p.getDate2()))) {
                                int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to Reserve this Estate?", "RESERVATION WARNING",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE);

                                p2 = p;
                                if (result == JOptionPane.YES_OPTION) {
                                    for (int i = 0; i < estatelist.size(); i++) {
                                        if (estatelist.get(i).getTitle().equals(p.getTitle()) && estatelist.get(i).getDate1() == p.getDate1()) {
                                            estatelist.remove(i);
                                            break;
                                        }
                                    }


                                    RegistrySeller1.get(p2.getOwner()).NumofreservesPlus();


                                    try {
                                        File file = new File("src\\Sellers.txt");
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
                                    } catch (IOException err) {
                                        err.printStackTrace();
                                    }

                                    try (FileWriter fw = new FileWriter("src\\Reservations.txt", true);
                                         BufferedWriter bw = new BufferedWriter(fw);
                                         PrintWriter out = new PrintWriter(bw)) {
                                        out.println(p2.getTitle());
                                        out.println(p2.getOwner());
                                        out.println(customer);
                                        out.println(p2.getCity());
                                        out.println(p2.getPeople());
                                        out.println(p2.getRooms());
                                        out.println(FromD.getText());
                                        out.println(FromM.getText());
                                        out.println(FromY.getText());
                                        out.println(UntilD.getText());
                                        out.println(UntilM.getText());
                                        out.println(UntilY.getText());
                                        out.println(p2.getPrice());
                                    } catch (IOException err) {
                                        err.printStackTrace();
                                    }


                                    if (date1.getDay()==p2.getDate1().getDay() && date2.getDay()!=p2.getDate2().getDay()) {
                                        Room tempr = new Room();
                                        tempr.setTitle(p2.getTitle());
                                        tempr.setOwner(p2.getOwner());
                                        tempr.setCity(p2.getCity());
                                        tempr.setPeople(p2.getPeople());
                                        tempr.setRooms(p2.getRooms());
                                        tempr.setDate1(date2);
                                        tempr.setDate2(p2.getDate2());
                                        tempr.setPrice(p2.getPrice());
                                        estatelist.addElement(tempr);
                                    }
                                    else if (date1.getDay()!=p2.getDate1().getDay() && date2.getDay()==p2.getDate2().getDay()) {
                                        Room tempr = new Room();
                                        tempr.setTitle(p2.getTitle());
                                        tempr.setOwner(p2.getOwner());
                                        tempr.setCity(p2.getCity());
                                        tempr.setPeople(p2.getPeople());
                                        tempr.setRooms(p2.getRooms());
                                        tempr.setDate1(p2.getDate1());
                                        tempr.setDate2(date1);
                                        tempr.setPrice(p2.getPrice());
                                        estatelist.addElement(tempr);
                                    }
                                    else if (date1.getDay()!=p2.getDate1().getDay() && date2.getDay()!=p2.getDate2().getDay()) {
                                        Room tempr1 = new Room(), tempr2 = new Room();
                                        tempr1.setTitle(p2.getTitle());
                                        tempr1.setOwner(p2.getOwner());
                                        tempr1.setCity(p2.getCity());
                                        tempr1.setPeople(p2.getPeople());
                                        tempr1.setRooms(p2.getRooms());
                                        tempr1.setDate1(p2.getDate1());
                                        tempr1.setDate2(date1);
                                        tempr1.setPrice(p2.getPrice());
                                        estatelist.addElement(tempr1);

                                        tempr2.setTitle(p2.getTitle());
                                        tempr2.setOwner(p2.getOwner());
                                        tempr2.setCity(p2.getCity());
                                        tempr2.setPeople(p2.getPeople());
                                        tempr2.setRooms(p2.getRooms());
                                        tempr2.setDate1(date2);
                                        tempr2.setDate2(p2.getDate2());
                                        tempr2.setPrice(p2.getPrice());
                                        estatelist.addElement(tempr2);
                                    }


                                    try {
                                        File file = new File("src\\Estates.txt");
                                        PrintWriter writer = new PrintWriter(file);
                                        writer.print("");
                                        writer.close();
                                    } catch (IOException er) {
                                        er.printStackTrace();
                                    }

                                    try {
                                        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Estates.txt"));
                                        for (int i = 0; i < estatelist.size(); i++) {
                                            writer.write(estatelist.get(i).getTitle() + "\n");
                                            writer.write(estatelist.get(i).getOwner() + "\n");
                                            writer.write(estatelist.get(i).getCity() + "\n");
                                            writer.write(estatelist.get(i).getPeople() + "\n");
                                            writer.write(estatelist.get(i).getRooms() + "\n");
                                            writer.write(estatelist.get(i).getDate1().getDay() + "\n");
                                            writer.write(estatelist.get(i).getDate1().getMonth() + "\n");
                                            writer.write(estatelist.get(i).getDate1().getYear() + "\n");
                                            writer.write(estatelist.get(i).getDate2().getDay() + "\n");
                                            writer.write(estatelist.get(i).getDate2().getMonth() + "\n");
                                            writer.write(estatelist.get(i).getDate2().getYear() + "\n");
                                            writer.write(estatelist.get(i).getPrice() + "\n");
                                        }
                                        writer.close();
                                    } catch (IOException err) {
                                        err.printStackTrace();
                                    }


                                    try(FileWriter fw = new FileWriter("src\\Messages.txt", true);
                                        BufferedWriter bw = new BufferedWriter(fw);
                                        PrintWriter out = new PrintWriter(bw))
                                    {
                                        out.println(customer);
                                        out.println(p2.getOwner());
                                        out.println(customer + " has Reserved your Estate " + p2.getTitle() + " from " + FromD.getText() + "/" + FromM.getText() +
                                                "/" + FromY.getText() + " until " + UntilD.getText() + "/" + UntilM.getText() + "/" + UntilY.getText());
                                        out.println("");
                                        out.println(p2.getOwner());
                                        out.println(customer);
                                        out.println("Your Reservation for " + p2.getTitle() + " from " + FromD.getText() + "/" + FromM.getText() +
                                                "/" + FromY.getText() + " until " + UntilD.getText() + "/" + UntilM.getText() + "/" + UntilY.getText() +
                                                " is Successfull.");
                                        out.println("");
                                    }
                                    catch (IOException err) {
                                        err.printStackTrace();
                                    }



                                    JOptionPane.showMessageDialog(null, "Estate has been Reserved Successfully!");
                                    frame.dispose();
                                    new CustomerEstates();

                                }



                            }

                            else {
                                JOptionPane.showMessageDialog(null, "Specific Dates are not Available for this Estate.");
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Nothing is Selected");
                        }
                    }

                    else {
                        JOptionPane.showMessageDialog(null, "Dates are Incorrect.");
                    }
                }

                else {
                    JOptionPane.showMessageDialog(null, "Fill Date Bars to fulfill Reservation.");
                }
            }
        });

        CITYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (search.getText().length()>0) {
                    for (int i = 0; i < estatelist.size(); i++) {
                        if (!estatelist.get(i).getCity().equals(search.getText())) {
                            estatelist.remove(i);
                            i--;
                        }
                    }

                    if (estatelist.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "There is no Estate at this City.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Search by City: " + search.getText());
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Search Bar is Empty.");
                }
            }
        });

        CLEARFILTERSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                estatelist = new DefaultListModel<>();
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

                        estatelist.addElement(room);
                    }
                    fr.close();    //closes the stream and release the resources
                } catch (IOException er) {
                    er.printStackTrace();
                }

                estateJlist.setModel(estatelist);

                JOptionPane.showMessageDialog(null, "Search Filters Cleared.");
            }
        });

        MAXPRICEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double price = Double.parseDouble(search.getText());

                if (search.getText().length()>0) {
                    for (int i = 0; i < estatelist.size(); i++) {
                        if (estatelist.get(i).getPrice() > price) {
                            estatelist.remove(i);
                            i--;
                        }
                    }

                    if (estatelist.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "There is no Estate below this Max Price.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Search by Max Price: " + search.getText());
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Search Bar is Empty.");
                }
            }
        });

        MINPRICEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double price = Double.parseDouble(search.getText());

                if (search.getText().length()>0) {
                    for (int i = 0; i < estatelist.size(); i++) {
                        if (estatelist.get(i).getPrice() < price) {
                            estatelist.remove(i);
                            i--;
                        }
                    }

                    if (estatelist.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "There is no Estate above this Min Price.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Search by Min Price: " + search.getText());
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Search Bar is Empty.");
                }
            }
        });

        SELLERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (search.getText().length()>0) {
                    for (int i = 0; i < estatelist.size(); i++) {
                        if (!estatelist.get(i).getOwner().equals(search.getText())) {
                            estatelist.remove(i);
                            i--;
                        }
                    }

                    if (estatelist.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "There is no Estate from this Seller.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Search by Seller: " + search.getText());
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Search Bar is Empty.");
                }
            }
        });

        PEOPLEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int people = Integer.parseInt(search.getText());

                if (search.getText().length()>0) {
                    for (int i = 0; i < estatelist.size(); i++) {
                        if (estatelist.get(i).getPeople() != people) {
                            estatelist.remove(i);
                            i--;
                        }
                    }

                    if (estatelist.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "There is no Estate with this number of People.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Search by People: " + search.getText());
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Search Bar is Empty.");
                }
            }
        });

        ROOMSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int rooms = Integer.parseInt(search.getText());

                if (search.getText().length()>0) {
                    for (int i = 0; i < estatelist.size(); i++) {
                        if (estatelist.get(i).getRooms() != rooms) {
                            estatelist.remove(i);
                            i--;
                        }
                    }

                    if (estatelist.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "There is no Estate with this number of Rooms.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Search by Rooms: " + search.getText());
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Search Bar is Empty.");
                }
            }
        });

        SEARCHBYDATESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (FromD.getText().length()>0 && FromM.getText().length()>0 && FromY.getText().length()>0 &&
                        UntilD.getText().length()>0 && UntilM.getText().length()>0 && UntilY.getText().length()>0) {
                    Date date1 = new Date(), date2 = new Date();
                    int d1 = Integer.parseInt(FromD.getText());
                    int m1 = Integer.parseInt(FromM.getText());
                    int y1 = Integer.parseInt(FromY.getText());
                    int d2 = Integer.parseInt(UntilD.getText());
                    int m2 = Integer.parseInt(UntilM.getText());
                    int y2 = Integer.parseInt(UntilY.getText());
                    date1.setDate(d1, m1, y1);
                    date2.setDate(d2, m2, y2);

                    if (DateCheck(d1, m1, y1) && DateCheck(d2, m2, y2) && (((Dateinto365(date1) < Dateinto365(date2)) && y1 == y2) || y1 < y2)) {

                        boolean yearchange = Dateinto365(date1) > Dateinto365(date2);
                        for (int i = 0; i < estatelist.size(); i++) {
                            if (Dateinto365(estatelist.get(i).getDate1()) > Dateinto365(estatelist.get(i).getDate2()) && yearchange) {
                                if (Dateinto365(date1) < Dateinto365(estatelist.get(i).getDate1()) || Dateinto365(date2) > Dateinto365(estatelist.get(i).getDate2())) {
                                    estatelist.remove(i);
                                    i--;
                                }
                            } else if (Dateinto365(estatelist.get(i).getDate1()) < Dateinto365(estatelist.get(i).getDate2()) && yearchange) {
                                estatelist.remove(i);
                                i--;
                            } else {
                                if (Dateinto365(date1) < Dateinto365(estatelist.get(i).getDate1()) || Dateinto365(date2) > Dateinto365(estatelist.get(i).getDate2())) {
                                    estatelist.remove(i);
                                    i--;
                                }
                            }
                        }

                        if (estatelist.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "There is no Estate Available these Dates.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Search by dates: " + date1.DatetoString() + " to " + date2.DatetoString());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Dates are Incorrect!");
                    }
                }

                else {
                    JOptionPane.showMessageDialog(null, "Date Search Bars are not filled properly.");
                }
            }
        });
    }
}
