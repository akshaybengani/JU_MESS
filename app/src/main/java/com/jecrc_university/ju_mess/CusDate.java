package com.jecrc_university.ju_mess;

class CusDate {

    String day,month,year;


    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public CusDate(int day1, int month1, int year1) {

        String day2,month2,year2;

        day2 = ""+day1;
        month2 = ""+month1;
        year2 = "" + year1;

        if (day1 < 10)
        {
            day2 = "0"+day1;
        }
        if (month1 < 10)
        {
            month2 = "0"+month1;
        }

        this.day = day2;
        this.month = month2;
        this.year = year2;
    }


}
