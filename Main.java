public class Main {

    /**
     * DECLARED ADMINS
     *
     * Username: Stefanos                          Katsos
     * Password: bestvolieune                      akshantop
     * Name:     Stefanos Anastasiades             Antreas Katsonouris
     * Email:    stefanosa@csd.auth.gr             kantreas@csd.auth.gr
     * Tel:      96843842                          99974784
     *
     * DECLARED SELLERS
     *
     * Username: qwer                              tyui
     * Password: asdf                              asdf
     * Name:     Kostis                            Petros
     * City:     Pafos                             Athens
     * Email:    asd@gmail.com                     tyui@gmail.com
     * Tel:      99374232                          99332211
     * Verified: true                              false
     * NumOfReserves: 1                            0
     * NumOfCancels: 2                             0
     *
     * DECLARED CUSTOMERS
     *
     * Username: zxcv                              vbnm
     * Password: asdf                              asdf
     * Name:     Giannis                           Nikos
     * Address:  Agias Lavras 21                   Navarinou 7
     * Email:    zxcv@gmail.com                    vbnm@gmail.com
     * Tel:      98989876                          97789056
     * Verified: true                              false
     *
     *
     * DECLARED ESTATES
     *
     * Title:  Heraklio Villa           Patra Apartments            Nicosia Park            Pafos Apartments
     * Owner:  qwer                     qwer                        qwer                    qwer
     * City:   Heraklio                 Patra                       Nicosia                 Pafos
     * People: 3                        5                           6                       7
     * Rooms:  2                        3                           4                       3
     * From:   13/12/2001               2/3/2021                    3/3/2001                12/11/2021
     * Until:  5/1/2002                 5/3/2021                    6/3/2001                19/11/2021
     * Price:  3467                     2356                        4567                    800
     *
     * DECLARED RESERVATIONS
     *
     * Title:    Nicosia Villa
     * Owner:    qwer
     * Customer: zxcv
     * City:     Nicosia
     * People:   8
     * Rooms:    4
     * From:     12/6/2021
     * Until:    25/6/2021
     * Price:    3455
     *
     * DECLARED CANCELLATIONS
     *
     * Title:    NICOSIA APPS           Athens Apartments
     * Owner:    qwer                   qwer
     * Customer: zxcv                   zxcv
     * City:     Nicosia                Athens
     * People:   4                      5
     * Rooms:    1                      2
     * From:     23/5/2021              2/3/2021
     * Until:    2/6/2021               5/3/2021
     * Price:    900                    2356
     */



    public static void main(String[] args) {
        Site site=new Site();
        System.out.println("SITE OPENED");
        System.out.println(site.start());
    }
}