//用户类：用户id，用户姓名，用户密码，用户的菜品，消费金额
public class User {
    private int uid;                   //用户id
    private String uname;              //用户姓名
    private String passwd;             //用户密码

    //无参构造方法
    public User() {
    }

    //有参构造方法
    public void setvalues(int uid, String uname, String passwd) {
        this.uid = uid;
        this.uname = uname;
        this.passwd = passwd;

    }

    //get()和set()方法
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }


    //返回数据，toString方法
    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", passwd='" + passwd + '\'' + '}';
    }
}