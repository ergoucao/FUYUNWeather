package myUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils
{
    private static String driver=null;
    private static String url=null;
    private static String username=null;
    private static String password=null;

    static
    {
        try
        {
            //读取db.properties文件的数据库连接信息
            InputStream in= JdbcUtils.class.getClassLoader().getResourceAsStream("informationFile/db.properties");
            Properties prop=new Properties();
            prop.load(in);

            driver=prop.getProperty("driver");
            url=prop.getProperty("url");
            username=prop.getProperty("username");
            password=prop.getProperty("password");

            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /*
    返回数据库连接对象
     */
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url,username,password);
    }

    public static Connection getConnection(String url) throws SQLException
    {
        return DriverManager.getConnection(url,username,password);
    }

    //释放资源
    public static void release(Connection conn, Statement st, ResultSet rs)
    {
        if (rs!=null)
        {
            try
            {
                rs.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        if (st!=null)
        {
            try
            {
                st.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        if (conn!=null)
        {
            try
            {
                conn.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
