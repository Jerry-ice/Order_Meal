import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserDaoImpl implements UserDao {

    Scanner scanner = new Scanner(System.in);
    User user = new User();
    Food food = new Food();

    //注册用户
    @Override
    public boolean register(User user) throws Exception {
        Connection conn=null;
        int num=0;
        String sql;
        boolean flag=true;
        try {
            //创建一个新的数据库连接
            conn = JDBCUtilss.getConnection();
            //编写sql代码
            sql="insert into rms.ruser(uid,uname,upasswd) values(?,?,?)";
            //调用更新数据库的方法
            num=JDBCUtilss.executeUpdate(sql,user.getUid(),user.getUname(),user.getPasswd());
            //判断是否注册成功
            if (num!=1) {
                flag=false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sql=null;
            JDBCUtilss.closeAll(conn,null,null);
        }
        return flag;
    }

    //登录用户
    @Override
    public boolean login(User user) throws Exception {
        Connection conn=null;  //链接
        ResultSet rs=null;   //查询的数据
        String sql=null;
        boolean flag=true;

        try {
            //创建一个新的数据库连接
            conn = JDBCUtilss.getConnection();
            //编写sql代码
            sql="SELECT uname,upasswd  FROM rms.ruser  where uid=?  and  upasswd=?";
            //查询数据库的方法

            rs=JDBCUtilss.executeQuery(sql, user.getUid(),user.getPasswd());

            User user1=new User();

            while(rs.next()){
                user1.setPasswd(rs.getString("upasswd"));
            }
            //判断是否登录成功
            if (user1.getPasswd()==null) {
                flag=false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sql=null;
            JDBCUtilss.closeAll(conn,null,rs);
        }
        return flag;
    }

    //修改账户或者密码
    @Override
    public void modify(User user) throws Exception {
        Connection conn=null;
        int rs;
        String sql;

        System.out.println("请输入新密码：必须6位");
        String  pw = scanner.next();
        if(pw.length()!=6) {
            System.out.println("修改密码失败......");
            return;
        }
        try {
            conn=JDBCUtilss.getConnection();
            sql="update rms.ruser set upasswd=?  where uid =?";
            rs=JDBCUtilss.executeUpdate(sql,pw,user.getUid());

            if (rs!=1) {
                System.out.println("修改密码失败......");
            } else {
                System.out.println("修改密码成功......");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sql=null;
            JDBCUtilss.closeAll(conn,null,null);
        }


    }
    //点菜
    @Override
    public void chooseOrder(User user) throws Exception {
        Connection conn=null;
        ResultSet rs=null;   //差寻的数据
        int num=0;
        String sql;
        String ocontent = "";
        double oamount = 0;
        //创建一个新的数据库连接

        try{
            conn = JDBCUtilss.getConnection();

            System.out.println("开始点菜.....");
            for (int i = 1; ; i++) {

                System.out.println("请输入菜品编号：");
                int fnum1 = scanner.nextInt();

                if (fnum1 == -1 ) {
                    System.out.println("点菜完成.....");
                    break;
                }
                System.out.println("请输入数量：");
                int fnum2 = scanner.nextInt();
                if ( fnum2 == -1) {
                    System.out.println("点菜完成.....菜品数量充足");
                    break;
                }
                if (fnum1 <=  0 && fnum2 >= 1) {
                    System.out.println("请输入有效菜品编号和数量或者菜品数量不足,请联系店小二");
                    continue;
                }

                //编写sql代码
                sql = "select  fname,fprice FROM rms.rfood where fid = ? ";
                rs = JDBCUtilss.executeQuery(sql, fnum1);
                while (rs.next()) {
                    ocontent = ocontent.concat(rs.getString("fname").concat(":").concat(rs.getString("fprice")).concat("元/份，").concat(String.valueOf(fnum2)).concat("份；"));
                    oamount = oamount + rs.getDouble("fprice") * fnum2;

                    System.out.println("已点菜品："+ocontent);
                    System.out.println("已点菜品总金额为："+oamount);
                }
                System.out.println("继续点菜，点菜完成输入-1");
            }
        } finally {
            sql=null;
            JDBCUtilss.closeAll(conn,null,rs);
        }

        try {
            //创建一个新的数据库连接
            conn = JDBCUtilss.getConnection();
            //编写sql代码
            sql="insert into rms.rorder(uid,uname,ocontent,oamount,payway) values(?,?,?,?,?)";
            //调用更新数据库的方法
            num=JDBCUtilss.executeUpdate(sql,user.getUid(),user.getUname(),ocontent,oamount,"null");
            //判断是否更新成功
            if (num!=1) {
                System.out.println("点菜失败.....请联系店小二");
            } else {
                System.out.println("点菜成功.....菜品数量充足");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sql=null;
            JDBCUtilss.closeAll(conn,null,null);
        }
    }

    //加菜
    @Override
    public void addChooseOrder(User user) throws Exception {
        Connection conn=null;
        ResultSet rs=null;   //查询的数据
        int num=0;
        String sql;
        String ocontent = "";
        double oamount = 0;
        //创建一个新的数据库连接

        try{
            conn = JDBCUtilss.getConnection();
            System.out.println("开始加菜......");
            for (int i = 1; ; i++) {

                System.out.println("请输入菜品编号：");
                int fnum1 = scanner.nextInt();
                if (fnum1 == -1 ) {
                    System.out.println("加菜完成.....库存充足");
                    break;
                }
                System.out.println("请输入数量：");
                int fnum2 = scanner.nextInt();
                if (fnum2 == -1) {
                    System.out.println("加菜完成.....库存充足");
                    break;
                }
                if (fnum1 > 51 && fnum2 >= 1) {
                    System.out.println("请输入有效菜品编号和数量或者菜品数量不足,请联系店小二");
                    continue;
                }

                //编写sql代码
                sql = "select  fname,fprice FROM rms.rfood where fid = ? ";
                rs = JDBCUtilss.executeQuery(sql, fnum1);
                while (rs.next()) {
                    ocontent = ocontent.concat(rs.getString("fname").concat(":").concat(rs.getString("fprice")).concat("元/份，").concat(String.valueOf(fnum2)).concat("份；"));
                    oamount = oamount + rs.getDouble("fprice") * fnum2;

                    System.out.println("已增加菜品："+ocontent);
                    System.out.println("已增加菜品总金额为："+oamount);
                }
                System.out.println("继续加菜，加菜完成输入-1");
            }
        } finally {
            sql=null;
            JDBCUtilss.closeAll(conn,null,rs);
        }

        try {
            //创建一个新的数据库连接
            conn = JDBCUtilss.getConnection();
            //编写sql代码
            sql="select ocontent,oamount FROM rms.rorder  where uid = ?";
            rs=JDBCUtilss.executeQuery(sql,user.getUid());
            while(rs.next()){
                ocontent = rs.getString("ocontent").concat(ocontent);
                oamount = rs.getDouble("oamount")+oamount;
            }

            sql="update rms.rorder set ocontent =  ?,oamount = ? where uid = ?";
            //调用更新数据库的方法
            num=JDBCUtilss.executeUpdate(sql,ocontent,oamount,user.getUid());
            //判断是否更新成功
            if (num!=1) {
                System.out.println("加菜失败.....请联系店小二");
            } else {
                System.out.println("加菜成功.....库存充足");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sql=null;
            JDBCUtilss.closeAll(conn,null,null);
        }

    }

    //买单
    @Override
    public boolean payment(User user) throws Exception {
        Connection conn=null;//链接
        ResultSet rs=null;   //差寻的数据
        String sql=null;
        int num=0;
        boolean flag=true;
        List<Order> list=new ArrayList<Order>();
        try {
            //创建一个新的数据库连接
            conn = JDBCUtilss.getConnection();
            //编写sql代码
            sql="SELECT uid,uname,ocontent,oamount  FROM rms.rorder  where uid= ?";
            //查询数据库的方法

            rs=JDBCUtilss.executeQuery(sql, user.getUid());
            Order order = new Order();
            while(rs.next()){
                order.setOid(rs.getInt("uid"));
                order.setOname(rs.getString("uname"));
                order.setOcontent(rs.getString("ocontent"));
                order.setOamount(rs.getDouble("oamount"));
                list.add(order);
            }

            if (order.getOamount() >0 && order.getOcontent() != null) {
                for (Order tmp: list) {
                    System.out.println(tmp);
                }
                System.out.println("正在出单....");
                System.out.println("您一共消费："+order.getOamount());
                System.out.println("请选择支付方式：1.微信；2支付宝；3银行卡");
                int n = scanner.nextInt();
                if (n == 1) {
                    order.setPayway("微信支付");
                }else if (n == 2){
                    order.setPayway("支付宝支付");
                } else if (n == 3){
                    order.setPayway("银行卡支付");
                }
                sql="update rms.rorder set payway=? where uid = ?";
                //调用更新数据库的方法
                num=JDBCUtilss.executeUpdate(sql,order.getPayway(),user.getUid());
                //判断是否更新成功
                if (num!=1) {
                    System.out.println(order.getPayway()+"买单失败......");
                } else {
                    System.out.println(order.getPayway()+"买单成功......");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sql=null;
            JDBCUtilss.closeAll(conn,null,rs);
        }
        return false;
    }
}