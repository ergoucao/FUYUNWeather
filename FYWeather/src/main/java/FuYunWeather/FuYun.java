package FuYunWeather;

import download.WeatherIntoDB;
import myUtils.JdbcUtils;
import org.json.JSONException;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


/*本程序使用mysql数据库，使用前请于src\\informationFile\\db.properties,配置用户名和密码。*/

public class FuYun
{
//    public static void main(String[] args)
//    {
////        该注释部分是建立数据库个表格和对其初始化的过程。
////        buildFuYunWeatherDB.createCityTable("E:\\java idea\\FUYUNWeather\\src\\citiesId.txt");//建立城市表格，城市名与接口查询码一一对应。
////        buildFuYunWeatherDB.createDateTable();//建立日期表格，实际日期与数字1,2,3,4，即未来4天天气。
////        buildFuYunWeatherDB.createWeatherTable();//建立天气表格，所有对应城市ID，天气ID，日期ID等信息一一对应
////        buildFuYunWeatherDB.createWeatherEventsTable("E:\\java idea\\FUYUNWeather\\src\\information\\weather events.txt");//建立天气事件表格，减少冗余
////        buildFuYunWeatherDB.createWindTable("src\\information\\wind.txt");//风向表格。
//
//        FuYun fuyun=new FuYun();
//        utilsToBuildFuYunWeatherDB buildFuYunWeatherDB=new utilsToBuildFuYunWeatherDB();
//        WeatherIntoDB url=new WeatherIntoDB();
//        fuyun.show();
////        url.getWeather();
//        fuyun.forecast();
//
////        fuyun.buildDB("E:\\java idea\\FUYUNWeather\\fuyunWeather - 副本.sql");
//    }

    /*
 参数：无
 功能：时间显示，问候语等。
 返回值：无。
*/
    public void show()
    {
        // 获取当前时间:
        Date date = new Date();
        var sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.print("尊敬的root先生您好\n现在是：");
        System.out.println(sdf.format(date));
        System.out.print("进行初始化，");
    }

    /*
 参数：无
 功能：功能菜单。
 返回值：无
*/
    public void forecast()
    {
        int choice;

        do
        {
            System.out.println("\n1.更新天气信息.\n" +
                    "2.查询城市天气\n" +
                    "3.查询极端天气\n"+
                    "4.退出");
            System.out.println("请输入您的选择：");
            Scanner s=new Scanner(System.in);
            choice=s.nextInt();
            if (choice==1)
            {
                System.out.print("正在更新，");
                WeatherIntoDB url=new WeatherIntoDB();
                url.getWeather();
            }
            else if (choice==2)
            {
                System.out.println("请输入各省会城市和直辖市全称(如福州市)，注意因台湾数据原因只能输入：台湾省，查询香港请输入：香港特别行政区\n请输入:");
                Scanner s1=new Scanner(System.in);
                String city=s1.nextLine();
                this.getWeatherFromDB(city);
            }
            else if (choice==3)
            {
                System.out.println("1.未来4天白天温度最高的城市\n" +
                        "2.未来4天白天温度最低的城市\n" +
                        "3查询某一城市未来4天最高气温\n" +
                        "其他有待更新。，请输入您的选择\n");
                Scanner s1=new Scanner(System.in);
                int ch=s1.nextInt();
                if (ch==1)
                {
                    String ans=this.getExtremeWeather("SELECT *,MAX(daytemp)" +"FROM weather","max(daytemp)");
                    System.out.println(ans);
                }
                else if (ch==2)
                {
                    String ans=this.getExtremeWeather("SELECT *,MIN(daytemp)" +"FROM weather","MIN(daytemp)");
                    System.out.println(ans);
                }
                else if (ch==3)
                {
                    System.out.println("请输入需要查询各省会城市和的直辖市全称(如福州市)，注意因台湾数据原因只能输入：台湾省，查询香港请输入：香港特别行政区\n请输入:");
                    Scanner s2=new Scanner(System.in);
                    String city=s2.nextLine();
                    utilsToBuildFuYunWeatherDB utils= new utilsToBuildFuYunWeatherDB();
                    String cityId=utils.find("select cityId from cities where city="+"'"+city+"'","cityId");
//                    System.out.println(cityId);
                    String ans=this.getExtremeWeather("SELECT MAX(daytemp),cityId" +
                            " FROM weather " +
                            " WHERE cityId="+cityId +
                            " GROUP BY cityId","cityId");
                    System.out.print("未来4天最高气温 ");
                    System.out.println(city+"  "+ "  "+ans);
                }
            }
        }
        while (choice!=4);
    }


    /*
     参数：城市名
     功能：通过城市名来输出该城市的天气信息。
     返回值：无
    */
    void getWeatherFromDB(String city)
    {
        utilsToBuildFuYunWeatherDB utils=new utilsToBuildFuYunWeatherDB();
        //通过城市名在城市表查找城市ID
        String cityId=utils.find("SELECT cityId FROM cities WHERE city="+"'"+city+"'","cityId");
        //通过城市Id输出未来4天天气预报,
        System.out.println("您查询的城市： "+city);
        for (int i=1;i<=4;i++)
        {

            String date=utils.find("SELECT NAME FROM DATE WHERE dateId="+i,"name");
            //weather表只有天气事件的ID，故需要用ID在天气事件中查看天气事件后才能输出。以下ID同理。
            String dayweatherEventId=utils.find("SELECT * FROM weather WHERE cityId="+cityId+" AND (id)%4+1="+i,"dayweatherEventId");
            String nightweatherEventId=utils.find("SELECT * FROM weather WHERE cityId="+cityId+" AND (id)%4+1="+i,"nightweatherEventId");
            String daywindId=utils.find("SELECT * FROM weather WHERE cityId="+cityId+" AND (id)%4+1="+i,"daywindId");
            String nightwindId=utils.find("SELECT * FROM weather WHERE cityId="+cityId+" AND (id)%4+1="+i,"daywindId");
            String dayweatherEvent=utils.find("SELECT NAME FROM weatherevents WHERE weatherEventId="+dayweatherEventId,"NAME");
            String nightweatherEvent=utils.find("SELECT NAME FROM weatherevents WHERE weatherEventId="+nightweatherEventId,"NAME");
            String daywind=utils.find("SELECT NAME FROM wind WHERE windId="+daywindId,"NAME");
            String nightwind=utils.find("SELECT NAME FROM wind WHERE windId="+nightwindId,"NAME");
            String daytemp=utils.find("SELECT * FROM weather WHERE cityId="+cityId+" AND (id)%4+1="+i,"daytemp");
            String nighttemp=utils.find("SELECT * FROM weather WHERE cityId="+cityId+" AND (id)%4+1="+i,"nighttemp");
            String daypower=utils.find("SELECT * FROM weather WHERE cityId="+cityId+" AND (id)%4+1="+i,"daypower");
            String nightpower=utils.find("SELECT * FROM weather WHERE cityId="+cityId+" AND (id)%4+1="+i,"nightpower");

            System.out.println("预报日期："+date);
            System.out.println("白天天气："+dayweatherEvent+",晚上天气："+nightweatherEvent);
            System.out.println("白天温度："+daytemp+",晚上温度："+nighttemp);
            System.out.println("白天风向："+daywind+",晚上风向："+nightwind);
            System.out.println("白天风力："+daypower+",晚上风力："+nightpower);
            System.out.println();
        }
    }

/*
 参数：sql语句,aim
 功能：运用数据库分组查询，获取极端天气。
 返回值：极端天气的信息。
*/
    String getExtremeWeather(String sql,String aim )
    {
//        System.out.println(sql+"  "+aim);
        utilsToBuildFuYunWeatherDB utils=new utilsToBuildFuYunWeatherDB();
        String temp=null,ans=null;
        if (aim.equals("cityId"))
        {
            temp=utils.find(sql,"MAX(daytemp)");
//            System.out.println(sql);
//            System.out.println(temp);
            return temp;
        }
        else
        {

            temp=utils.find(sql,aim);
//        System.out.println(temp);
            String cityId=utils.find("select * from weather where daytemp="+temp,"cityId");
//        System.out.println(cityId);
            ans=utils.find("select city from cities where cityId="+cityId,"city");
//        System.out.println(ans);
        }
        return ans+" "+temp;
    }


    /*
 参数：文件路径
 功能：运行sql脚本建立数据库。功能存在bug.
 返回值：无
*/
    void buildDB(String filePath)
    {
        File file=new File(filePath);
        int flag=0;
        FileInputStream fileInputStream= null;
        utilsToBuildFuYunWeatherDB utils=new utilsToBuildFuYunWeatherDB();
        try
        {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        InputStreamReader inputStream=new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader=new BufferedReader(inputStream);
        try
        {
            String sql=null;
            do
            {
                sql=null;
                while (sql==null||(sql.length()>0&&sql.charAt(sql.length()-1)!=';'))
                {
                    String temp=bufferedReader.readLine();
                    if (sql==null&&temp!=null&&!temp.equals("\n"))
                        sql=temp;
                    else if (temp!=null&&!temp.equals("\n"))
                        sql+=temp;
                 }
                System.out.println(sql);
                if (flag==1)
                   utils.createDB(sql);
                else
                {
                    utils.createDB(sql,"jdbc:mysql://localhost:3306/fuyun?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC ");
                }
                flag=1;
            }
            while (sql!=null);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}