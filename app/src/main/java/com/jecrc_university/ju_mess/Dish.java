package com.jecrc_university.ju_mess;

public class Dish {

    private String mName;
    private String mImageUrl;

    public Dish()
    {

    }

    public Dish(String mName, String mImageUrl) {
        this.setmName(mName);
        this.setmImageUrl(mImageUrl);
    }


    public String getmName() {
        return mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }


}
