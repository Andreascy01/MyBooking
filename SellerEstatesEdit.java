import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SellerEstatesEdit extends Site{
    private JPanel panel1;
    private JButton CANCELButton;
    private JButton APPLYButton;
    private JTextField Title;
    private JTextField City;
    private JTextField People;
    private JTextField Rooms;
    private JTextField Available;
    private JTextField Price;
    private JTextField FromD;
    private JTextField FromM;
    private JTextField FromY;
    private JTextField UntilD;
    private JTextField UntilM;
    private JTextField UntilY;
    private final JFrame frame;
    private final HashMap<String, Room> RegistryEstate;


    public SellerEstatesEdit(Room room)
    {
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
        catch(IOException e)
        {
            e.printStackTrace();
        }

        Title.setText(room.getTitle());
        City.setText(room.getCity());
        String p=Integer.toString(room.getPeople());
        People.setText(p);
        String r=Integer.toString(room.getRooms());
        Rooms.setText(r);
        String fromd=Integer.toString(room.getDate1().getDay());
        FromD.setText(fromd);
        String fromm=Integer.toString(room.getDate1().getMonth());
        FromM.setText(fromm);
        String fromy=Integer.toString(room.getDate1().getYear());
        FromY.setText(fromy);
        String untild2=Integer.toString(room.getDate2().getDay());
        UntilD.setText(untild2);
        String untilm=Integer.toString(room.getDate2().getMonth());
        UntilM.setText(untilm);
        String untily=Integer.toString(room.getDate2().getYear());
        UntilY.setText(untily);
        String price=Double.toString(room.getPrice());
        Price.setText(price);
        String av=Boolean.toString(room.isAvailable());
        Available.setText(av);

        RegistryEstate.remove(room.getTitle());


        frame = new JFrame("EDIT ESTATE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                int fd = Integer.parseInt(FromD.getText());
                int fm = Integer.parseInt(FromM.getText());
                int fy = Integer.parseInt(FromY.getText());
                int ud = Integer.parseInt(UntilD.getText());
                int um = Integer.parseInt(UntilM.getText());
                int uy = Integer.parseInt(UntilY.getText());
                Date date1 = new Date(), date2 = new Date();
                date1.setDate(fd, fm, fy);
                date2.setDate(ud, um, uy);

                if (DateCheck(fd, fm, fy) && DateCheck(ud, um, uy) && ((Dateinto365(date1) < Dateinto365(date2)) && fy == uy) || fy < uy) {
                    Room r1 = new Room();
                    r1.setTitle(Title.getText());
                    r1.setOwner(room.getOwner());
                    r1.setCity(City.getText());
                    int p=Integer.parseInt(People.getText());
                    r1.setPeople(p);
                    int r=Integer.parseInt(Rooms.getText());
                    r1.setRooms(r);
                    int fromd=Integer.parseInt(FromD.getText());
                    int fromm=Integer.parseInt(FromM.getText());
                    int fromy=Integer.parseInt(FromY.getText());
                    int untild=Integer.parseInt(UntilD.getText());
                    int untilm=Integer.parseInt(UntilM.getText());
                    int untily=Integer.parseInt(UntilY.getText());
                    Date d1 =new Date(), d2 = new Date();
                    d1.setDate(fromd, fromm, fromy);
                    d2.setDate(untild, untilm, untily);
                    r1.setDate1(d1);
                    r1.setDate2(d2);
                    double price=Double.parseDouble(Price.getText());
                    r1.setPrice(price);
                    RegistryEstate.put(r1.getTitle(), r1);

                    File file = new File("src\\Estates.txt");
                    try {
                        PrintWriter writer = new PrintWriter(file);
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

                    JOptionPane.showMessageDialog(null, "Estate has been Edited Successfully!");
                    frame.dispose();
                    new SellerEstates();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Dates are Incorrect, Try Again!");
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
