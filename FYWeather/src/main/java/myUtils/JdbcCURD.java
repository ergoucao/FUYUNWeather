package myUtils;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcCURD
{
    public static void main(String[] args)
    {
        JdbcCURD crud=new JdbcCURD();
//        crud.insert();
        crud.find("select * from users where id=3");
    }

    public void insertCityTable(String cityId,String city,String province)
    {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            conn= JdbcUtils.getConnection();
            st=conn.createStatement();
            String sql="insert into cities(cityId,city,province) " +
                    "values("+cityId+","+"'"+city+"'"+","+"'"+province+"'"+")";
            System.out.println(sql);
            int num=st.executeUpdate(sql);
            if (num>0)
            {
                System.out.println("插入成功");
            }
            else
            {
                System.out.println("失败");
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

    public void insertTable(String sql)
    {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            conn= JdbcUtils.getConnection();
            st=conn.createStatement();

            System.out.println(sql);
            int num=st.executeUpdate(sql);
            if (num>0)
            {
                System.out.println("插入成功");
            }
            else
            {
                System.out.println("失败");
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

    public void delete()
    {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            conn=JdbcUtils.getConnection();
            st=conn.createStatement();
            String sql="delete from users where id=3";
            int num=st.executeUpdate(sql);
            if (num>0)
            {
                System.out.println("删除成功");
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
    public void update(String sql)
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
//                System.out.println("更新成功");
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
//                System.out.println("更新成功");
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

    public void find(String sql)
    {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            conn=JdbcUtils.getConnection();

//            String sql="select * from users where id=3";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            if (rs.next())
            {
                System.out.println(rs.getString("name"));
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

}
