public class Seller extends User{

    private String  city;
    private boolean verified = false;
    private int numofreserves, numofcancels;

    /**
     * Constructor of Seller
     */

    public Seller() {
        numofreserves = 0;
        numofcancels = 0;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNumofreserves() {
        return numofreserves;
    }

    public void setNumofreserves(int n) {this.numofreserves = n;}

    /**
     * This method is used to add one to the number of the Reservations that are made to the estates of the Seller
     */

    public void NumofreservesPlus() {
        numofreserves++;
    }

    /**
     * This method is used to subtract one to the number of the Reservations that are made to the estates of the Seller
     */
    public void NumofreservesMinus() {
        numofreserves--;
    }

    public int getNumofcancels() {
        return numofcancels;
    }

    public void setNumofcancels(int n) {this.numofcancels = n;}

    /**
     * This method is used to add one to the number of the Cancellations that are made to the estates of the Seller
     */
    public void NumofcancelsPlus() {
        numofcancels++;
    }

    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean b) {this.verified = b;}


    /**
     * Method to return the username of the Seller Object for use in SellerJList
     * @return username
     */

    @Override
    public String toString() {
        return username;
    }
}
