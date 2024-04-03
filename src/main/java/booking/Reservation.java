package booking;

public class Reservation {
    private Accommodation accommodation;
    private Tenant tenant;
    private Integer numOfPeople;
    private ReservationDateRange date;

    public Reservation(String motel, String number, int numOfPersons, Area crete, int stars, int numOfReviews, Image motelImage, boolean available) {
    }
    public Reservation(Accommodation accommodation, Tenant tenant, Integer numOfPeople, ReservationDateRange date) {
        this.accommodation = accommodation;
        this.tenant = tenant;
        this.numOfPeople = numOfPeople;
        this.date = date;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public Integer getNumOfPeople() {
        return numOfPeople;
    }

    public ReservationDateRange getDate() {
        return date;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public void setNumOfPeople(Integer numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public void setDate(ReservationDateRange date) {
        this.date = date;
    }
    Accommodation a =  new Accommodation("Motel" , "31" , 3 , new Area("Crete"), 3 , 105 , new Image("motelImage") , true);
    Accommodation b =  new Accommodation("Resort" , "701" , 8 , new Area("Scorpios"), 5 , 1000 , new Image("resortImage") , false);
    Accommodation c =  new Accommodation("Cabin" , "5" , 4 , new Area("Ios"), 3 , 105 , new Image("motelImage") , true);
    Accommodation d =  new Accommodation("Apartment" , "22" , 2 , new Area("Acropolis"), 4 , 115 , new Image("apartmentImage") , false);
    Accommodation e =  new Accommodation("Bed and Breakfast" , "202" , 3 , new Area("Corfu"), 2 , 10 , new Image("BBImage") , true);


    @Override
    public String toString() {
        return "Reservation{" +
                "accommodation=" + accommodation +
                ", tenant=" + tenant +
                ", numOfPeople=" + numOfPeople +
                ", date=" + date +
                '}';
    }
}
