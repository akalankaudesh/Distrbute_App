package nsbm.plymouth.distributed_app.DAO;

public class Purchased_Item {

    private String item_code;
    private String description;
    private String unit_price;
    private String qty;

    public Purchased_Item(String item_code, String description, String unit_price, String qty) {
        this.item_code = item_code;
        this.description = description;
        this.unit_price = unit_price;
        this.qty = qty;
    }

    public Purchased_Item() {
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Purchased_Item{" +
                "item_code='" + item_code + '\'' +
                ", description='" + description + '\'' +
                ", unit_price=" + unit_price +
                ", qty=" + qty +
                '}';
    }
}
