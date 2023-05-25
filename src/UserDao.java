public interface UserDao {

    //注册方法
    public boolean register(User user) throws Exception;

    //登录方法
    public boolean login(User user) throws Exception;

    //查看并修改密码
    public void modify(User user) throws Exception;


    //点菜
    public void chooseOrder(User user) throws Exception;

    //加菜
    public void addChooseOrder(User user) throws Exception;

    //买单
    public boolean payment(User user) throws Exception;

}