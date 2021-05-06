package BuisnnesLayer;

import java.util.Date;

public class Item {
    private final Integer item_id;             //item id
    private final Integer product_id;          //product id
    private String location;                   //item location in store
    private final Date supplied_date;          //the date which the item was supplied
    private final Date creation_date;         //item creation date and time
    private Date expiration_date;               //item expiration date and time of defected date
//    private Double selling_price;               //item price tag in store

    public Item(Integer item_id, Integer product_id, String location, Date supplied_date, Date creation_date, Date expiration_date) {
        this.item_id = item_id;
        this.product_id = product_id;
        this.location = location;
        this.supplied_date = supplied_date;
        this.creation_date = creation_date;
        this.expiration_date = expiration_date;
//        this.selling_price = selling_price;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getSupplied_date() {
        return supplied_date;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

//    public Double getSelling_price() {
//        return selling_price;
//    }
//
//    public void setSelling_price(Double selling_price) {
//        this.selling_price = selling_price;
//    }

    @Override
    public String toString() {
        return "{" +
                "item_id=" + item_id +
                ", product_id=" + product_id +
                ", location=" + location +
                ", supplied_date=" + supplied_date +
                ", creation_date=" + creation_date +
                ", expiration_date=" + expiration_date +
                '}';
    }
}
