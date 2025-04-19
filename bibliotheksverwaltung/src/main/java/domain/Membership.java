package domain;

import java.sql.Timestamp;

public class Membership {
    private Timestamp startDate;
    private Timestamp endDate;
    private int id;

    public Membership(Timestamp startDate, Timestamp endDate, int id) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = id;
    }
    


}