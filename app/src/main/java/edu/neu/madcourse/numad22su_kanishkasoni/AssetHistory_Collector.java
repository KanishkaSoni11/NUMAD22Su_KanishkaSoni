package edu.neu.madcourse.numad22su_kanishkasoni;

public class AssetHistory_Collector {
    private final String price;
    private final String date;

    public AssetHistory_Collector(String price, String date) {
        this.date = date;
        this.price = price;
    }

    @Override
    public String toString() {
        return "AssetHistory_Collector{" +
                "price='" + price + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }


}
