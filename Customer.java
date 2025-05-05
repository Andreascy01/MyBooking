public class Customer extends User {

    private String address;
    private boolean verified = false;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean b) {this.verified = b;}

    /**
     * Method which is used for the CustomerJlist just to return only the username
     * @return username
     */
    @Override
    public String toString() {
        return username;
    }
}