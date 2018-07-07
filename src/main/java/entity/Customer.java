package entity;

public class Customer {

    private int cid;
    private String cname;
    private String phone;
    private String address;

    public Customer() {
    }

    public Customer(int cid, String cname, String phone, String address) {
        this.cid = cid;
        this.cname = cname;
        this.phone = phone;
        this.address = address;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" + "cid=" + cid + ", cname=" + cname + ", phone=" + phone +", address=" + address + '}';
    }

}
