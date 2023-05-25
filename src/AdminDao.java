import java.util.List;

public interface AdminDao {

    //菜品增加
    public void addFood() throws Exception;
    //菜品删除
    public void delFood() throws Exception;
    //修改菜品价格
    public void modFoodPrice() throws Exception;


    //查看所有订单
    public void showOrder() throws Exception;

}