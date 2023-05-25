import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodImpl implements FoodDao {

    Scanner scanner = new Scanner(System.in);

    //查看菜品
    @Override
    public void showFood() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        String sql;

        List<Food> list = new ArrayList<Food>();
        try {
            conn = JDBCUtilss.getConnection();
            sql = "select * from rms.rfood";

            rs = JDBCUtilss.executeQuery(sql);

            while (rs.next()) {
                Food food = new Food();
                food.setFid(rs.getInt("fid"));
                food.setFtype(rs.getString("ftype"));
                food.setFname(rs.getString("fname"));
                food.setFprice(rs.getDouble("fprice"));
                list.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sql = null;
            JDBCUtilss.closeAll(conn, null, rs);
        }
        for (Food tmp : list) {
            System.out.println(tmp);
        }

    }

    //分类查看菜品
    @Override
    public void typeShow() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        String sql;

        List<Food> list = new ArrayList<Food>();
        System.out.println("请输入菜品类型：（包括凉菜、热菜、鲜汤、米饭）");
        String type = scanner.next();
        try {
            conn = JDBCUtilss.getConnection();
            sql = "select * from rms.rfood where  ftype = ?";

            rs = JDBCUtilss.executeQuery(sql, type);

            while (rs.next()) {
                Food food = new Food();
                food.setFid(rs.getInt("fid"));
                food.setFtype(rs.getString("ftype"));
                food.setFname(rs.getString("fname"));
                food.setFprice(rs.getDouble("fprice"));

                list.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sql = null;
            JDBCUtilss.closeAll(conn, null, rs);
        }
        for (Food tmp : list) {
            System.out.println(tmp);
        }
    }

    //搜索菜品
    @Override
    public void search() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        String sql;

        List<Food> list = new ArrayList<Food>();
        System.out.println("请输入菜品名称");
        String type = scanner.next();
        try {
            conn = JDBCUtilss.getConnection();
            sql = "select * from rms.rfood where  fname like \"%\"?\"%\"";

            rs = JDBCUtilss.executeQuery(sql, type);

            while (rs.next()) {
                Food food = new Food();
                food.setFid(rs.getInt("fid"));
                food.setFtype(rs.getString("ftype"));
                food.setFname(rs.getString("fname"));
                food.setFprice(rs.getDouble("fprice"));

                list.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sql = null;
            JDBCUtilss.closeAll(conn, null, rs);
        }
        for (Food tmp : list) {
            System.out.println(tmp);
        }
    }
}