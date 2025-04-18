package application;

import domain.Rating;

public interface RatingDBHandler {
    public void saveRating(Rating rating);
    public Rating loadRating(int ratingID);
    public void deleteRating(int ratingID);
    public void updateRating(Rating rating);
}
