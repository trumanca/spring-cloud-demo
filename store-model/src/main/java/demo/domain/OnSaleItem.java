package demo.domain;

public class OnSaleItem {

    private String name;
    private double price;
    private double percent;

    public OnSaleItem(){
    }

    public OnSaleItem(String name, double price, double percent){
        this.name = name;
        this.price = price;
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
