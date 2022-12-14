package APPLICATION;

import DB.ReviewDAO;

import java.time.LocalDate;

public class Review {

    // instance variables
    private int reviewNumber;
    private int eventNumber;
    private boolean providerReview;         // is het een provider review?
    private int userNumber;
    private int providerNumber;
    private String subject;
    private int scoreOn10;
    private String description;
    private java.sql.Date dateOfReview;


    // constructor for ReviewDAO
    public Review(int reviewNumber, int eventNumber, boolean providerReview, int userNumber, int providerNumber, String subject, int scoreOn10, String description, java.sql.Date dateOfReviews) {
        this.reviewNumber = reviewNumber;
        this.eventNumber = eventNumber;
        this.providerReview = providerReview;
        this.userNumber = userNumber;
        this.providerNumber = providerNumber;
        this.subject = subject;
        this.scoreOn10 = scoreOn10;
        this.description = description;
        this.dateOfReview = dateOfReviews;
    }

    // constructor for GUI
    public Review(int eventNumber, boolean providerReview, int userNumber, int providerNumber, String subject, int scoreOn10, String description) {
        this.reviewNumber = ReviewDAO.getReviews().size() + 1;
        this.eventNumber = eventNumber;
        this.providerReview = providerReview;
        this.userNumber = userNumber;
        this.providerNumber = providerNumber;
        this.subject = subject;
        this.scoreOn10 = scoreOn10;
        this.description = description;
        this.dateOfReview = java.sql.Date.valueOf(LocalDate.now());
    }

    //getters and setters
    public int getReviewNumber() {
        return reviewNumber;
    }

    public void setReviewNumber(int reviewNumber) {
        this.reviewNumber = reviewNumber;
    }

    public int getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(int eventNumber) {
        this.eventNumber = eventNumber;
    }

    public boolean isProviderReview() {
        return providerReview;
    }

    public void setProviderReview(boolean providerReview) {
        this.providerReview = providerReview;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getProviderNumber() {
        return providerNumber;
    }

    public void setProviderNumber(int providerNumber) {
        this.providerNumber = providerNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getScoreOn10() {
        return scoreOn10;
    }

    public void setScoreOn10(int scoreOn10) {
        this.scoreOn10 = scoreOn10;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Date getDateOfReviews() {
        return dateOfReview;
    }

    public void setDateOfReviews(java.sql.Date dateOfReviews) {
        this.dateOfReview = dateOfReviews;
    }

    @Override
    public String toString() {
        return subject;
    }

}
