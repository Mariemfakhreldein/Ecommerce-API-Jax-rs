package gov.iti.jets.api.controllers.customs;

import java.time.LocalDate;

public class MyDateType {

    private int day;
    private int month;
    private int year;

    public MyDateType(){

    }

    public MyDateType( LocalDate date) {
        this.day = date.getDayOfMonth();
        this.month = date.getMonthValue();
        this.year = date.getYear();
    }

    public int getDay() {
        return day;
    }

    public void setDay( int day ) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth( int month ) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear( int year ) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "MyDateType{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

}
