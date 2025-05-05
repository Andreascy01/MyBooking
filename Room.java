public class Room {


    private    int people, rooms;
    private boolean available;
    private double price;
    private    String title, owner, city;
    private    Date date1, date2;


    /**
     * Method to return if the Room is Available
     * @return available
     */

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


    /**
     * Method to print date1 correctly with slashes in between, as a String
     * @return this.date1.getDay() + "/" + this.date1.getMonth() + "/" + this.date1.getYear()
     */

    public String Date1toString () {
        return this.date1.getDay() + "/" + this.date1.getMonth() + "/" + this.date1.getYear();
    }

    /**
     * Method to print date2 correctly with slashes in between, as a String
     * @return this.date2.getDay() + "/" + this.date2.getMonth() + "/" + this.date2.getYear()
     */

    public String Date2toString () {
        return this.date2.getDay() + "/" + this.date2.getMonth() + "/" + this.date2.getYear();
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    /**
     * Method to return the title of the Room Object for use in EstateJlist
     * @return title
     */

    @Override
    public String toString() {
        return title;
    }
}
