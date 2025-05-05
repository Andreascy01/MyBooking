import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CustomerReservations {
    private JPanel panel1;
    private JTextArea textArea1;
    private JList<Reserve> ReserveJList;
    private JButton BACKButton;
    private JButton CANCELRESERVATIONButton;
    private JScrollPane scrollpane;
    private final JFrame frame;
    private final DefaultListModel<Reserve> reservelist = new DefaultListModel<>();
    private String customer;
    private Reserve p, p2;
    private final HashMap<String, Seller> RegistrySeller1;
    private final DefaultListModel<Room> estatelist = new DefaultListModel<>();

    public CustomerReservations() {

        RegistrySeller1 = new HashMap<>();
        File file=new File("src\\Sellers.txt");    //creates a new file instance
        try
        {
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
            file = new File("src\\Login.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            customer = br.readLine();
            fr.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            file = new File("src\\Reservations.txt");    //creates a new file instance
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
                reserve.setAvailable(false);

                if (reserve.getCustomer().equals(customer)) {
                    reservelist.addElement(reserve);
                }
            }
            fr.close();    //closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();
        }

        ReserveJList.getSelectionModel().addListSelectionListener(e -> {

            p = ReserveJList.getSelectedValue();
            if (p!=null) {
                textArea1.setText("Title:\t" + p.getTitle() + "\nOwner:\t" + p.getOwner() + "\nCity:\t" + p.getCity()
                        + "\nPeople:\t" + p.getPeople()
                        + "\nRooms:\t" + p.getRooms() + "\nFrom:\t" + p.Date1toString() + "\nUntil:\t" + p.Date2toString()
                        + "\nPrice:\t" + p.getPrice());
            }
        });



        textArea1.setEditable(false);

        ReserveJList.setModel(reservelist);
        scrollpane.setViewportView(ReserveJList);

        ReserveJList.setVisibleRowCount(4);



        frame = new JFrame("YOUR RESERVATIONS");
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

        CANCELRESERVATIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (p != null) {
                    int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to Cancel this Reservation?", "CANCEL WARNING",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    p2 = p;
                    if (result == JOptionPane.YES_OPTION) {
                        for (int i = 0; i < reservelist.size(); i++) {
                            if (reservelist.get(i).getTitle().equals(p.getTitle()) && reservelist.get(i).getDate1() == p.getDate1()) {
                                reservelist.remove(i);
                                break;
                            }
                        }


                        RegistrySeller1.get(p2.getOwner()).NumofcancelsPlus();
                        RegistrySeller1.get(p2.getOwner()).NumofreservesMinus();


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


                        try {
                            File file = new File("src\\Reservations.txt");
                            PrintWriter writer = new PrintWriter(file);
                            writer.print("");
                            writer.close();
                        } catch (IOException er) {
                            er.printStackTrace();
                        }

                        try {
                            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Reservations.txt"));
                            for (int i = 0; i < reservelist.size(); i++) {
                                writer.write(reservelist.get(i).getTitle() + "\n");
                                writer.write(reservelist.get(i).getOwner() + "\n");
                                writer.write(reservelist.get(i).getCustomer() + "\n");
                                writer.write(reservelist.get(i).getCity() + "\n");
                                writer.write(reservelist.get(i).getPeople() + "\n");
                                writer.write(reservelist.get(i).getRooms() + "\n");
                                writer.write(reservelist.get(i).getDate1().getDay() + "\n");
                                writer.write(reservelist.get(i).getDate1().getMonth() + "\n");
                                writer.write(reservelist.get(i).getDate1().getYear() + "\n");
                                writer.write(reservelist.get(i).getDate2().getDay() + "\n");
                                writer.write(reservelist.get(i).getDate2().getMonth() + "\n");
                                writer.write(reservelist.get(i).getDate2().getYear() + "\n");
                                writer.write(reservelist.get(i).getPrice() + "\n");
                            }
                            writer.close();
                        } catch (IOException err) {
                            err.printStackTrace();
                        }


                        try (FileWriter fw = new FileWriter("src\\Estates.txt", true);
                             BufferedWriter bw = new BufferedWriter(fw);
                             PrintWriter out = new PrintWriter(bw)) {
                            out.println(p2.getTitle());
                            out.println(p2.getOwner());
                            out.println(p2.getCity());
                            out.println(p2.getPeople());
                            out.println(p2.getRooms());
                            out.println(p2.getDate1().getDay());
                            out.println(p2.getDate1().getMonth());
                            out.println(p2.getDate1().getYear());
                            out.println(p2.getDate2().getDay());
                            out.println(p2.getDate2().getMonth());
                            out.println(p2.getDate2().getYear());
                            out.println(p2.getPrice());
                        } catch (IOException err) {
                            err.printStackTrace();
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
                        } catch (IOException er) {
                            er.printStackTrace();
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





                        try (FileWriter fw = new FileWriter("src\\Cancellations.txt", true);
                             BufferedWriter bw = new BufferedWriter(fw);
                             PrintWriter out = new PrintWriter(bw)) {
                            out.println(p2.getTitle());
                            out.println(p2.getOwner());
                            out.println(p2.getCustomer());
                            out.println(p2.getCity());
                            out.println(p2.getPeople());
                            out.println(p2.getRooms());
                            out.println(p2.getDate1().getDay());
                            out.println(p2.getDate1().getMonth());
                            out.println(p2.getDate1().getYear());
                            out.println(p2.getDate2().getDay());
                            out.println(p2.getDate2().getMonth());
                            out.println(p2.getDate2().getYear());
                            out.println(p2.getPrice());
                        } catch (IOException err) {
                            err.printStackTrace();
                        }


                        try(FileWriter fw = new FileWriter("src\\Messages.txt", true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            PrintWriter out = new PrintWriter(bw))
                        {
                            out.println(customer);
                            out.println(p2.getOwner());
                            out.println(customer + " has Cancelled his/her Reservation for your Estate " + p2.getTitle() + " from " + p2.Date1toString() +
                                    " until " + p2.Date2toString());
                            out.println("");
                            out.println(p2.getOwner());
                            out.println(customer);
                            out.println("Your Reservation for " + p2.getTitle() + " from " + p2.Date1toString() + " until " + p2.Date2toString() +
                                    " has been Cancelled Successfully.");
                            out.println("");
                        }
                        catch (IOException err) {
                            err.printStackTrace();
                        }








                        JOptionPane.showMessageDialog(null, "Reservation has been Canceled Successfully.");
                        frame.dispose();
                        new CustomerReservations();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Nothing is Selected to Cancel.");
                }
            }
        });


    }
}
