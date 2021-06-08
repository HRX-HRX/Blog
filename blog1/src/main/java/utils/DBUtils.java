package utils;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;

/*
 * 通用数据库操作类
 * 1.对外提供 Connect 对象
 * 2.提供统一的关闭方法
*/
public class DBUtils {

    private static MysqlDataSource dataSource = null;

    private static String driverClass = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/myblog?useUnicode=true&characterEncoding=UTF-8";
    private static String username = "root";
    private static String password = "111111";

    public static Connection getConnect() throws SQLException {
        Connection conn=null;
        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url, username, password);//建立连接
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.print("数据库连接失败！");
        }
        return conn;
    }


    /*
     * 对外提供统一的 Connection 对象
     */

   /* public static Connection getConnect() throws SQLException {
        if(dataSource==null){
            // 首次调用，先初始化
            dataSource = new MysqlDataSource();
            // 1.设置连接的服务器地址
            dataSource.setURL("jdbc:mysql://location:3306/myblog?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
            // 2.设置用户名
            dataSource.setUser("root");
            // 3.设置密码【设置成你们自己的密码】
            dataSource.setPassword("111111");
        }
        return dataSource.getConnection();
    }*/

    /**
     * 统一的关闭方法
     * @param connection
     * @param statement
     * @param resultSet
     */
    public static void close(Connection connection,
                             PreparedStatement statement,
                             ResultSet resultSet) throws SQLException {
        if(resultSet!=null) resultSet.close();
        if(statement!=null) statement.close();
        if(connection!=null) connection.close();
    }

}
