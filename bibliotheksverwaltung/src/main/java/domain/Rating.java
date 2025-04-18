package domain;

public class Rating {
    private User user;
    private int ratingID;
    private String comment;
    private int stars;

    public Rating(User user, int ratingID, String comment, int stars) {
        this.user = user;
        this.ratingID = ratingID;
        this.comment = comment;
        this.stars = stars;
    }

}