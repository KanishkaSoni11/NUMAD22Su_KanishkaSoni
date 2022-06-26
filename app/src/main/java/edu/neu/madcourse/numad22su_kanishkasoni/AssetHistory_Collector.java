package edu.neu.madcourse.numad22su_kanishkasoni;

public class AssetHistory_Collector {
    private final String price;
    private final String date;
    private final String time;

    public AssetHistory_Collector(String price, String date, String time) {
        this.date = date;
        this.price = price;
        this.time = time;
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

    public String getTime() {
        return time;
    }


}
