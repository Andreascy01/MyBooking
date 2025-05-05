public class Reserve extends Room{

    private String customer;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * Method to return the Title of the Reservation for use in ReserveJList
     * @return getTitle()
     */
    @Override
    public String toString() {
        return getTitle();
    }
}
