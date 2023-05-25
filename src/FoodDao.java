import java.util.List;

public interface FoodDao {
    //查看全部菜品
    public void showFood() throws Exception;

    //分类查看菜品
    public void  typeShow() throws Exception;

    //菜品搜索
    public void  search() throws Exception;
}