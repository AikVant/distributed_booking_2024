package booking;

public class Reservation {
    private Accomodation accomodation;
    private Tenant tenant;
    private Integer numOfPeople;
    private ReservationDate date;

    public Reservation() {
    }
    public Reservation(Accomodation accomodation, Tenant tenant, Integer numOfPeople, ReservationDate date) {
        this.accomodation = accomodation;
        this.tenant = tenant;
        this.numOfPeople = numOfPeople;
        this.date = date;
    }

    public Accomodation getAccomodation() {
        return accomodation;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public Integer getNumOfPeople() {
        return numOfPeople;
    }

    public ReservationDate getDate() {
        return date;
    }

    public void setAccomodation(Accomodation accomodation) {
        this.accomodation = accomodation;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public void setNumOfPeople(Integer numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public void setDate(ReservationDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "accomodation=" + accomodation +
                ", tenant=" + tenant +
                ", numOfPeople=" + numOfPeople +
                ", date=" + date +
                '}';
    }
}
