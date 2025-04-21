package domain;

import java.sql.Timestamp;

public class Membership {
    private Timestamp startDate;
    private Timestamp endDate;
    private int id;
    private int userID;

    public Membership(Timestamp startDate, Timestamp endDate, int userID) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.userID = userID;
    }
    
    public Timestamp getStartDate() {
    	return this.startDate;
    }
    
    public Timestamp getEndDate() {
    	return this.endDate;
    }
    
    public int getID() {
    	return this.id;
    }
    
    public int getUserID() {
    	return this.userID;
    }
    


}