package test;

import FuYunWeather.FuYun;
import FuYunWeather.utilsToBuildFuYunWeatherDB;
import download.WeatherIntoDB;

//如跟换主机/数据库请在 main//resources//db.properties更改账户信息。

public class FuYunTest
{
    public static void main(String[] args)
    {
//        该注释部分是建立数据库个表格和对其初始化的过程。
//        buildFuYunWeatherDB.createCityTable("E:\\java idea\\FUYUNWeather\\src\\citiesId.txt");//建立城市表格，城市名与接口查询码一一对应。
//        buildFuYunWeatherDB.createDateTable();//建立日期表格，实际日期与数字1,2,3,4，即未来4天天气。
//        buildFuYunWeatherDB.createWeatherTable();//建立天气表格，所有对应城市ID，天气ID，日期ID等信息一一对应
//        buildFuYunWeatherDB.createWeatherEventsTable("E:\\java idea\\FUYUNWeather\\src\\information\\weather events.txt");//建立天气事件表格，减少冗余
//        buildFuYunWeatherDB.createWindTable("src\\information\\wind.txt");//风向表格。

        FuYun fuyun=new FuYun();
        utilsToBuildFuYunWeatherDB buildFuYunWeatherDB=new utilsToBuildFuYunWeatherDB();
        WeatherIntoDB url=new WeatherIntoDB();
        fuyun.show();
        fuyun.forecast();

//        url.getWeather();
//        fuyun.buildDB("E:\\java idea\\FUYUNWeather\\fuyunWeather - 副本.sql");
    }
}
