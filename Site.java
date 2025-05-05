import java.util.HashMap;

public class Site {

    public int[] regyear = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public int[] leapyear = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};


    /**
     * Method to return true if the Login Credentials are Correct (found in the 3 HashMaps admins, sellers, customers)
     * or false if they are Wrong (they are not found in HashMaps)
     * @param user username (String)
     * @param pass password (String)
     * @param admins HashMap containing all Administrators
     * @param sellers HashMap containing all Sellers
     * @param customers HashMap containing all Customers
     * @return boolean
     */


    public boolean login(String user, String pass, HashMap<String, Admin> admins, HashMap<String, Seller> sellers, HashMap<String, Customer> customers) {
        if (admins.containsKey(user) && (admins.get(user).getPassword().equals(pass))) {
            return true;
        } else if (sellers.containsKey(user) && (sellers.get(user).getPassword().equals(pass)) && (sellers.get(user).getVerified())) {
            return true;
        } else return customers.containsKey(user) && (customers.get(user).getPassword().equals(pass)) && (customers.get(user).getVerified());
    }

    /**
     * This method creates, sets and returns a Seller Object
     * @param user username (String)
     * @param pass password (String)
     * @param name name (String)
     * @param city city (String)
     * @param email email (String)
     * @param tel tel (String)
     * @return seller
     */

    public Seller sellerRegister(String user, String pass, String name, String city, String email, String tel) {
        Seller seller = new Seller();
        seller.setUsername(user);
        seller.setPassword(pass);
        seller.setName(name);
        seller.setCity(city);
        seller.setEmail(email);
        seller.setTel(tel);
        return seller;
    }


    /**
     * This method creates, sets and returns a Customer Object
     * @param user username (String)
     * @param pass password (String)
     * @param name name (String)
     * @param address address (String)
     * @param email email (String)
     * @param tel tel (String)
     * @return customer
     */

    public Customer customerRegister(String user, String pass, String name, String address, String email, String tel) {
        Customer customer = new Customer();
        customer.setUsername(user);
        customer.setPassword(pass);
        customer.setName(name);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setTel(tel);
        return customer;
    }


    /**
     * This method is doing all the necessary checks to check if a given date is Valid
     * @param d Day (int)
     * @param m Month (int)
     * @param y Year (int)
     * @return boolean
     */

    public boolean DateCheck(int d, int m, int y) {

        if (m > 12 || m < 1) {
            return false;
        }

        if (y < 1000 || y > 9999) {
            return false;
        }

        if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) {
            return d >= 1 && d <= leapyear[m - 1];
        } else {
            return d >= 1 && d <= regyear[m - 1];
        }
    }


    /**
     * This method  is given a Date Object and returns a single number (1-365(366)) that represents that Date
     * @param k Date Object
     * @return days365 (int)
     */


    public int Dateinto365(Date k) {
        int days365 = 0;
        for (int i = 1; i < k.getMonth(); i++) {
            days365 += regyear[i];
        }
        if (((k.getYear() % 4 == 0 && k.getYear() % 100 != 0) || (k.getYear() % 400 == 0)) && k.getMonth() != 1) {
            days365 += 1;
        }
        days365 += k.getDay();
        return days365;
    }


    /**
     * This method opens the first JFrame of the Site
     */

    public Boolean start() {
        new BeginSite();
        return true;
    }


}