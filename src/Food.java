//菜品类：菜品id，菜品类型，菜品名称，菜品单价，菜品剩余分量

public class Food {
    //定义
    private int fid;             //菜品id
    private String ftype;        //菜品类型
    private String fname;        //菜品名称
    private double fprice;       //菜品单价

    //无参构造方法
    public Food() {
    }

    //有参构造方法
    public Food(int fid, String ftype, String fname, double fprice, int fnum) {
        this.fid = fid;
        this.ftype = ftype;
        this.fname = fname;
        this.fprice = fprice;

    }

    //get()和set()方法
    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public double getFprice() {
        return fprice;
    }

    public void setFprice(double fprice) {
        this.fprice = fprice;
    }


    //返回数据，toString方法
    @Override
    public String toString() {
        return "Food{" +
                "fid=" + fid +
                ", ftype='" + ftype + '\'' +
                ", fname='" + fname + '\'' +
                ", fprice=" + fprice + '}';
    }
}