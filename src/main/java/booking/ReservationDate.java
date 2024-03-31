package booking;

import java.util.Date;

public class ReservationDate {
    private Date start;
    private Date end;

    public ReservationDate(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "ReservationDate{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
