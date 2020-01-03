package FuYunWeather;

import myUtils.JdbcCURD;
import myUtils.JdbcUtils;
import java.io.*;
import java.sql.*;


public class utilsToBuildFuYunWeatherDB
{

    final static private int weatherNum=200;
    //传入城市编码文件，创建数据库表。

    /*
 参数：城市名id文件路径。
 功能：通过该文件建立数据库城市id表
 返回值：无
*/
    static void createCityTable(String txtPath)
    {
        Connection conn=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        File file=new File(txtPath);
        if (file.isFile()&&file.exists())
        {
            try
            {
                conn = JdbcUtils.getConnection();
                String cityId=null,city=null,province=null;
                FileInputStream fileInputStream=new FileInputStream(file);
                InputStreamReader inputStream=new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStream);

                while ((cityId=bufferedReader.readLine())!=null&&
                        (city=bufferedReader.readLine())!=null&&
                        (province=bufferedReader.readLine())!=null)
                {

                    JdbcCURD crud=new JdbcCURD();
                    crud.insertCityTable(cityId,city,province);
                }

            } catch (SQLException | FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("路径错误");
        }
    }


    /*
 参数：天气事件文件路径。
 功能：通过该文件建立数据库城市id表
 返回值：无
*/
    static void createWeatherEventsTable(String txtPath)
    {
        Connection conn=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        File file=new File(txtPath);
        if (file.isFile()&&file.exists())
        {
            try
            {
                conn = JdbcUtils.getConnection();
                String weatherEvent=null;
                FileInputStream fileInputStream=new FileInputStream(file);
                InputStreamReader inputStream=new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStream);

                for  (int i=1;(weatherEvent=bufferedReader.readLine())!=null;i++)
                {

                    JdbcCURD crud=new JdbcCURD();
                    String sql="insert into weatherEvents(weatherEventId,name) " +
                            "values("+i+","+"'"+weatherEvent+"'"+")";
                    crud.insertTable(sql);
                }

            } catch (SQLException | FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("路径错误");
        }
    }

/*
    参数：无
    功能：创建风向表
    返回值：无
*/
    static void createWindTable(String txtPath)
    {
        Connection conn=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        File file=new File(txtPath);
        if (file.isFile()&&file.exists())
        {
            try
            {
                conn = JdbcUtils.getConnection();
                String wind=null;
                FileInputStream fileInputStream=new FileInputStream(file);
                InputStreamReader inputStream=new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStream);

                for  (int i=1;(wind=bufferedReader.readLine())!=null;i++)
                {

                    JdbcCURD crud=new JdbcCURD();
                    String sql="insert into wind(windId,name) " +
                            "values("+i+","+"'"+wind+"'"+")";
                    crud.insertTable(sql);
                }

            } catch (SQLException | FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("路径错误");
        }
    }

/*
    参数：无
    功能：初始化日期表，用日期与日期id建立表，减少冗余 ,获取5天预报故日期id:1-5
    返回值：无
 */
    static void createDateTable()
    {
        Connection conn=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        try
        {
            conn = JdbcUtils.getConnection();
            String wind=null;

            for  (int i=1;i<=4;i++)
            {

                JdbcCURD crud=new JdbcCURD();
                String sql="INSERT INTO DATE(dateId,NAME)" +
                        "VALUES("+i+",NULL)";
                crud.insertTable(sql);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /*
    参数：无
    功能：初始化日期表，用日期与日期id建立表，减少冗余 ,获取5天预报故日期id:1-5
    返回值：无
 */
    static void createWeatherTable()
    {
        Connection conn=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        try
        {
            conn = JdbcUtils.getConnection();
            String wind=null;

            for  (int i=1;i<=weatherNum;i++)
            {
                JdbcCURD crud=new JdbcCURD();
                String sql="INSERT INTO weather(id)" +
                        "VALUES("+i+")";
                crud.insertTable(sql);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
/*
    参数：sql 查询语句
    功能：通过关键字查找Id,或通过Id查找关键字
    返回值：查到的关键字。
 */
    static public String find(String sql,String aimName)
    {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        String result=null;
        try
        {
            conn=JdbcUtils.getConnection();

//            String sql="select * from users where id=3";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
//            System.out.println(sql);
            if (rs.next())
            {
//                System.out.println(rs.getString("name"));
                  result=rs.getString(aimName);
//                System.out.println(aimName);
//                System.out.println(result);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            JdbcUtils.release(conn,st,rs);
        }
        return result;
    }

    public void createDB(String sql)
    {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            conn=JdbcUtils.getConnection();
            st=conn.createStatement();
//            String sql="update users set name='曹鑫',email='gacl@sina.com' where id=3";
//            System.out.println("#####"+sql+"#####");
            int num=st.executeUpdate(sql);
            if (num>0)
            {
                System.out.println("执行成功");
            }
            else
            {
                System.out.println("执行失败");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            JdbcUtils.release(conn,st,rs);
        }
    }

    public void createDB(String sql,String url)
    {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            conn=JdbcUtils.getConnection(url);
            st=conn.createStatement();
//            String sql="update users set name='曹鑫',email='gacl@sina.com' where id=3";
//            System.out.println("#####"+sql+"#####");
            int num=st.executeUpdate(sql);
            if (num>0)
            {
                System.out.println("执行成功");
            }
            else
            {
                System.out.println("执行失败");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            JdbcUtils.release(conn,st,rs);
        }
    }

    /*
参数：sql语句
功能：传入sql语句进行数据库的操作实际上不仅仅是数据库的更新。
返回值：无
*/
    public void updateTable(String sql)
    {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            conn=JdbcUtils.getConnection();
            st=conn.createStatement();
//            String sql="update users set name='曹鑫',email='gacl@sina.com' where id=3";
            int num=st.executeUpdate(sql);
            if (num>0)
            {
                System.out.println("更新成功");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            JdbcUtils.release(conn,st,rs);
        }
    }

//    public static void main(String[] args)
//    {
//        utilsToBuildFuYunWeatherDB test=new utilsToBuildFuYunWeatherDB();
////        System.out.println(test.find("select weatherEventId from weatherevents where name="+"'晴'","weatherEventId"));
//        test.createDB("create ");
//    }
}
