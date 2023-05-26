import java.util.Random;
import java.util.Scanner;

public class Home {

    public static void main(String[] args) throws Exception {

        UserDao userDao = new UserDaoImpl();
        AdminDao adminDao = new AdminDaoImpl();
        FoodDao foodDao = new FoodImpl();
        User user = new User();
        Scanner scanner = new Scanner(System.in);

        System.out.println("----- 欢迎使用餐厅管理系统 ------");
        System.out.println("请选择： 1,普通用户;   2,管理员");
        int usertype = scanner.nextInt();

        if (usertype == 1) {
            System.out.println("您选择了普通用户....");

            System.out.println("注意：没有账号？先注册后登录....");
            System.out.println("请选择：1.注册用户;  2.登录用户");
            boolean flags1 = false;   //测试是否登录成功

            int u = scanner.nextInt();
            while (true) {
                System.out.println("请输入账号:");
                int id = scanner.nextInt();
                System.out.println("请输入姓名:");
                String name = scanner.next();
                System.out.println("请输入密码(必须为6个字符):");
                String passwd = scanner.next();
                if (passwd.length() == 6 && (name != null) && (id > 0)) {    //判断密码是否满足条件
                    user.setvalues(id, name, passwd);
                    flags1 = true;
                    break;
                } else {
                    System.out.println("输入错误，请重新输入,退出请输入-1....");
                    continue;
                }
            }
            boolean flags2 = false;      //判断是否登录成功
            if (u == 1) {
                System.out.println("---------注册用户------");
                if (userDao.register(user)) {
                    System.out.println("注册成功！");
                    System.out.println("---------登录用户------");
                    if (userDao.login(user)) {
                        System.out.println("登录成功!已为您分配"+(int)(Math.random()*10)  +"号餐桌，请入座!");
                        flags2 = true;
                    } else {
                        System.out.println("登录失败，退出.....");
                    }
                } else {
                    System.out.println("注册失败！");
                }
            } else if (u == 2) {
                System.out.println("---------登录用户------");
                if (userDao.login(user)) {
                    System.out.println("登录成功!已为您分配"+(int)(Math.random()*10) +"号餐桌，请入座!");
                    flags2 = true;
                } else {
                    System.out.println("账号密码有误，登录失败；退出.....");
                }
            } else {
                System.out.println("输入有误，退出.....");
            }

            if (flags2 == true) {
                while (true) {
                    System.out.println("------餐厅管理首页-------");
                    System.out.println("-----1：查看所有菜品-----");
                    System.out.println("-----2：分类查看菜品-----");
                    System.out.println("-----3：搜索菜品名称-----");
                    System.out.println("-----4：点菜 -----------");
                    System.out.println("-----5：加菜------------");
                    System.out.println("-----6：买单------------");
                    System.out.println("-----7：修改密码---------");
                    System.out.println("-----8：退出------------");
                    System.out.println("请选择：");
                    int n = scanner.nextInt();
                    if (n == 8) {
                        System.out.println("您已经退出.....");
                        break;
                    }
                    switch (n) {
                        case 1:
                            foodDao.showFood();
                            break;
                        case 2:
                            foodDao.typeShow();
                            break;
                        case 3:
                            foodDao.search();
                            break;
                        case 4:
                            userDao.chooseOrder(user);
                            break;
                        case 5:
                            userDao.addChooseOrder(user);
                            break;
                        case 6:
                            userDao.payment(user);
                            break;
                        case 7:
                            userDao.modify(user);
                            break;
                    }
                }
            }
        } else {
            boolean flags = false;
            System.out.println("您选择了管理员用户....");
            int i = 1;
            while (i <= 3) {
                System.out.println("请输入管理员账号:");
                int id = scanner.nextInt();
                System.out.println("请输入管理员名称:");
                String name = scanner.next();
                System.out.println("请输入管理员密码:");
                String passwd = scanner.next();
                if (passwd.equals("123456") && (name.equals("admin")) && (id == 1)) {   //判断是否满足条件
                    System.out.println("登录成功......");
                    flags = true;
                    break;
                } else {
                    if (i >= 3) {
                        System.out.println("输入错误三次，管理员账户已冻结.....");
                        break;
                    }
                    System.out.println("输入错误还剩" + (3 - i) + "次机会！请重新输入,退出请输入-1....");
                    i++;

                    continue;
                }

            }
            if (flags == true) {

                while (true) {
                    System.out.println("------ 餐厅后台管理系统 ------");
                    System.out.println("-----1：查看所有菜品-------------");
                    System.out.println("-----2：分类查看菜品-------------");
                    System.out.println("-----3：搜索菜品名称-------------");
                    System.out.println("-----4：添加菜品 ---------------");
                    System.out.println("-----5：删除菜品----------------");
                    System.out.println("-----6：修改菜品单价-------------");
                    System.out.println("-----7：查看订单----------------");
                    System.out.println("-----8：退出-------------------");
                    System.out.println("请选择：");
                    int n = scanner.nextInt();
                    if (n == 8) {
                        System.out.println("您已经退出.....");
                        break;
                    }
                    switch (n) {
                        case 1:
                            foodDao.showFood();
                            break;
                        case 2:
                            foodDao.typeShow();
                            break;
                        case 3:
                            foodDao.search();
                            break;
                        case 4:
                            adminDao.addFood();
                            break;
                        case 5:
                            adminDao.delFood();
                            break;
                        case 6:
                            adminDao.modFoodPrice();
                            break;
                        case 7:
                            adminDao.showOrder();
                            break;
                    }
                }
            }

        }

    }
}