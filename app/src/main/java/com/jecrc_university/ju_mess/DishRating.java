package com.jecrc_university.ju_mess;

public class DishRating {

    private String rateDishName,rateDishImage,rateDishFeedback;
    private int rateDishRating;

    public DishRating(String rateDishName, String rateDishImage, String rateDishFeedback, int rateDishRating) {
        this.rateDishName = rateDishName;
        this.rateDishImage = rateDishImage;
        this.rateDishFeedback = rateDishFeedback;
        this.rateDishRating = rateDishRating;
    }

    public String getRateDishName() {

        return rateDishName;
    }

    public void setRateDishName(String rateDishName) {
        this.rateDishName = rateDishName;
    }

    public String getRateDishImage() {
        return rateDishImage;
    }

    public void setRateDishImage(String rateDishImage) {
        this.rateDishImage = rateDishImage;
    }

    public String getRateDishFeedback() {
        return rateDishFeedback;
    }

    public void setRateDishFeedback(String rateDishFeedback) {
        this.rateDishFeedback = rateDishFeedback;
    }

    public int getRateDishRating() {
        return rateDishRating;
    }

    public void setRateDishRating(int rateDishRating) {
        this.rateDishRating = rateDishRating;
    }
}
