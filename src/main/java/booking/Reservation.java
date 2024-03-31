package booking;

public class Reservation {
    private Accommodation accommodation;
    private Tenant tenant;
    private Integer numOfPeople;
    private ReservationDate date;

    public Reservation() {
    }
    public Reservation(Accommodation accommodation, Tenant tenant, Integer numOfPeople, ReservationDate date) {
        this.accommodation = accommodation;
        this.tenant = tenant;
        this.numOfPeople = numOfPeople;
        this.date = date;
    }

    public Accommodation getAccomodation() {
        return accommodation;
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

    public void setAccomodation(Accommodation accommodation) {
        this.accommodation = accommodation;
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
                "accomodation=" + accommodation +
                ", tenant=" + tenant +
                ", numOfPeople=" + numOfPeople +
                ", date=" + date +
                '}';
    }
}
