package nsbm.plymouth.distributed_app.DTO;

public class Purchase_Orders {

    private String order_id;
    private String customer_id;
    private String  item_id;
    private double  unit_price;
    private int qty;

    public Purchase_Orders(String order_id, String customer_id, String item_id, double unit_price, int qty) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.item_id = item_id;
        this.unit_price = unit_price;
        this.qty = qty;
    }

    public Purchase_Orders() {
    }


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Purchase_Orders{" +
                "order_id='" + order_id + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", item_id='" + item_id + '\'' +
                ", unit_price=" + unit_price +
                ", qty=" + qty +
                '}';
    }
}
