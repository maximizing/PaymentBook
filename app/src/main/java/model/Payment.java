package model;

/**
 * Created by maximize on 12/18/2016 AD.
 */

public class Payment {

    private String describtion,price, type, datetime ;



    public String getDescribtion(){return describtion;}

    public void setDescribtion(String d){this.describtion = d;}

    public String getPrice(){return price;}

    public void setPrice(String p){this.price = p;}

    public String getType() {
        return type;
    }

    public void setType(String t) {
        this.type = t;
    }

    public String getDateTime() {
        return datetime;
    }

    public void setDateTime(String dt) {
        this.datetime = dt;
    }


}
