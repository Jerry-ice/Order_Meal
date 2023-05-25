import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminDaoImpl implements AdminDao {

    Food food = new Food();
    Scanner scanner = new Scanner(System.in);
    //添加菜品
    @Override
    public void addFood() throws Exception {
        Connection conn=null;
        int num=0;
        String sql;
        boolean flag=true;
        System.out.println("菜品编号：");
        food.setFid(scanner.nextInt());
        System.out.println("菜品类型：");
        food.setFtype(scanner.next());
        System.out.println("菜品名称：");
        food.setFname(scanner.next());
        System.out.println("菜品单价");
        food.setFprice(scanner.nextDouble());

        try {
            //创建一个新的数据库连接
            conn = JDBCUtilss.getConnection();
            //编写sql代码
            sql="insert into rms.rfood(fid,ftype,fname,fprice) values(?,?,?,?)";
            //调用更新数据库的方法
            num=JDBCUtilss.executeUpdate(sql,food.getFid(),food.getFtype(),food.getFname(),food.getFprice());
            //判断是否添加成功
            if (num == 1) {
                System.out.println("添加菜品成功....");
            }else {
                System.out.println("添加菜品失败....");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sql=null;
            JDBCUtilss.closeAll(conn,null,null);
        }

    }
    //删除菜品
    @Override
    public void delFood() throws Exception {
        Connection conn=null;
        int rs;
        String sql;

        System.out.println("删除的菜品编号：");
        int num = scanner.nextInt();

        try {
            conn=JDBCUtilss.getConnection();
            sql="delete from rms.rfood where fid = ?";
            rs=JDBCUtilss.executeUpdate(sql,num);
            if (rs!=1) {
                System.out.println("删除失败.....");
            } else {
                System.out.println("删除成功.....");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sql=null;
            JDBCUtilss.closeAll(conn,null,null);
        }
    }

    //修改单价
    @Override
    public void modFoodPrice() throws Exception {
        Connection conn=null;
        int rs;
        String sql;
        System.out.println("菜品编号：");
        int num = scanner.nextInt();
        System.out.println("单价：");
        double price = scanner.nextDouble();
        try {
            conn=JDBCUtilss.getConnection();
            sql="update rms.rfood set fprice = ?  where fid = ?";
            rs=JDBCUtilss.executeUpdate(sql,price,num);

            if (rs!=1) {
                System.out.println("修改单价失败.....");
            } else {
                System.out.println("修改单价成功.....");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sql=null;
            JDBCUtilss.closeAll(conn,null,null);
        }
    }


    //查看所有订单
    @Override
    public void showOrder() throws Exception {
        Connection conn=null;
        ResultSet rs=null;
        String sql;

        List<Order> list=new ArrayList<Order>();
        try {
            conn= JDBCUtilss.getConnection();
            sql="select * from rms.rorder";

            rs=JDBCUtilss.executeQuery(sql);

            while(rs.next()){
                Order order = new Order();
                order.setOid(rs.getInt("uid"));
                order.setOname(rs.getString("uname"));
                order.setOcontent(rs.getString("ocontent"));
                order.setOamount(rs.getDouble("oamount"));
                order.setPayway(rs.getString("payway"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sql=null;
            JDBCUtilss.closeAll(conn,null,rs);
        }
        for (Order tmp : list) {
            System.out.println(tmp);
        }
    }


}