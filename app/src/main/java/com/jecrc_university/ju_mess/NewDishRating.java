package com.jecrc_university.ju_mess;

class NewDishRating {

    int rateDishRating;
    String rateDishFeedback;

    public NewDishRating() {
    }

    public NewDishRating(int rateDishRating, String rateDishFeedback) {
        this.rateDishRating = rateDishRating;
        this.rateDishFeedback = rateDishFeedback;
    }

    public int getRateDishRating() {
        return rateDishRating;
    }

    public void setRateDishRating(int rateDishRating) {
        this.rateDishRating = rateDishRating;
    }

    public String getRateDishFeedback() {
        return rateDishFeedback;
    }

    public void setRateDishFeedback(String rateDishFeedback) {
        this.rateDishFeedback = rateDishFeedback;
    }
}
