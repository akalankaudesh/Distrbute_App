package nsbm.plymouth.distributed_app.DTO;

public class Customer {
    private String cusid;
    private String cusname;
    private String address;

    public Customer(String cusid, String cusname, String address) {
        this.cusid = cusid;
        this.cusname = cusname;
        this.address = address;
    }

    public Customer() {
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusid='" + cusid + '\'' +
                ", cusname='" + cusname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
