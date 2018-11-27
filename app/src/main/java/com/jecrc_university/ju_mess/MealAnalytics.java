package com.jecrc_university.ju_mess;

public class MealAnalytics {

    String mealName;
    int totalRating,totalUsers;

    public MealAnalytics() {
    }

    public MealAnalytics(String mealName, int totalRating, int totalUsers) {
        this.mealName = mealName;
        this.totalRating = totalRating;
        this.totalUsers = totalUsers;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
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
