//订单类： 用户id，用户名，点菜列表，总金额，支付方式
public class Order {

    private int oid;
    private String oname;
    private String ocontent;
    private double oamount;
    private String payway;

    //无参构造方法
    public Order() {
    }

    //get()和set()方法


    public Order(int oid, String oname, String ocontent, double oamount, String payway) {
        this.oid = oid;
        this.oname = oname;
        this.ocontent = ocontent;
        this.oamount = oamount;
        this.payway = payway;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getOcontent() {
        return ocontent;
    }

    public void setOcontent(String ocontent) {
        this.ocontent = ocontent;
    }

    public double getOamount() {
        return oamount;
    }

    public void setOamount(double oamount) {
        this.oamount = oamount;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    //返回数据，toString方法


    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", oname='" + oname + '\'' +
                ", ocontent='" + ocontent + '\'' +
                ", oamount=" + oamount +
                ", payway='" + payway + '\'' +
                '}';
    }
}
