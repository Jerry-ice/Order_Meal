import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * jdbc工具类
 * 1.数据库连接的获取
 * 2.数据库资源的关闭
 * 3.insert/update/delete方法的封装
 * 4.select方法的封装
 *
 */
public class JDBCUtilss {
    private static Connection conn;
    private static PreparedStatement pstmt;
    private static ResultSet rs;
    //执行一次
    static{

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败！检查mysql驱动包是否存在");

        }
    }
    /**
     * 获取数据库连接
     * @return conn
     */
    public static Connection getConnection(){
        try {
            if(conn == null || conn.isClosed()){
                conn = DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/rms?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC",
                                "root", "123456");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * 关闭数据库连接
     * @param conn
     * @param pstmt
     * @param rs
     */
    public static void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs){
        try {
            if(rs != null){rs.close();}
            if(pstmt != null){pstmt.close();}
            if(conn != null){conn.close();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 关闭查询的数据库连接
     *
     */
    public static void closeAll(){
        try {
            if(rs != null){rs.close();}
            if(pstmt != null){pstmt.close();}
            if(conn != null){conn.close();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 处理insert/update/delete操作
     * @param sql
     * @param params
     * @return  num
     */
    public static int executeUpdate(String sql,Object... params){
        int num = 0;
        try {
            conn = getConnection();
            //获取预处理对象
            pstmt =conn.prepareStatement(sql);
            //传参数
            if(params != null){
                for(int i=0;i<params.length;i++){
                    pstmt.setObject(i+1, params[i]);
                }
            }
            //执行sql
            num = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * 处理select操作
     */
    public static ResultSet executeQuery(String sql,Object... params){
        try{
            //1.获取连接
            conn = getConnection();
            //2.预编译sql
            pstmt = conn.prepareStatement(sql);
            //3.传参数
            if(params != null){
                for(int i=0;i<params.length;i++){
                    pstmt.setObject((i+1), params[i]);
                }
            }
            //4.执行sql
            rs = pstmt.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }
}