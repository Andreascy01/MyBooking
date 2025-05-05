public class Date extends Room{
    private int day, month, year;


    /**
     * Method to set Day, Month and Year to this Date Object
     * @param day (DAY)
     * @param month (MONTH)
     * @param year (YEAR)
     */


    public void setDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }


    /**
     * Method to print the dates correctly with slashes in between, as a String
     * @return this.getDay() + "/" + this.getMonth() + "/" + this.getYear()
     */
    public String DatetoString () {
        return this.getDay() + "/" + this.getMonth() + "/" + this.getYear();
    }
}

