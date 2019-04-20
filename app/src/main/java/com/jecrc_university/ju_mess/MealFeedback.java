package com.jecrc_university.ju_mess;

public class MealFeedback {
    int totalRating,totalUsers;
    public MealFeedback() {
    }
    public MealFeedback(int totalRating, int totalUsers) {
        this.totalRating = totalRating;
        this.totalUsers = totalUsers;
    }
    public int getTotalRating() {
        return totalRating;
    }
    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }
    public int getTotalUsers() {
        return totalUsers;
    }
    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }
}
