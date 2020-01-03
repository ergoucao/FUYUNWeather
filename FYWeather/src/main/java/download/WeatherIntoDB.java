package download;

import FuYunWeather.utilsToBuildFuYunWeatherDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import myUtils.JdbcCURD;

/*
    功能：是从接口获得天气预报json字符串，解析，并存入数据库。
    版本：1.0
    author:cxx.
*/
public class WeatherIntoDB
{
    //只更新一次日期，标记日期是否更新。
    private boolean flag=true;
    //计算插入城市Id
    private int cnt=1;
    private utilsToBuildFuYunWeatherDB util=new utilsToBuildFuYunWeatherDB();

/*
 参数：无
 功能：获取的天气预报json字符串,并调用anaylyzeJson存入数据库。
 返回值：无
*/
    public void getWeather()
    {
        System.out.println("天气信息导入中......");
        try
        {
            final int cityNum=35;
            cnt=1;
            utilsToBuildFuYunWeatherDB  utils=new utilsToBuildFuYunWeatherDB();
            for (int i=1;i<=cityNum;i++)
            {
                String sql="select cityId from cities where id="+i;
                //获得城市码以通过接口查询天气。
                String acode=utils.find(sql,"cityId");
                URL url=new URL("http://restapi.amap.com/v3/weather/weatherInfo?extensions=all&city="+acode+"&key=71dd9fc159e2ddaa27c68c3911640590");
//                System.out.println("URL为+"+url.toString());
                URLConnection urlConnection = url.openConnection();
                HttpURLConnection connection = null;
                if(urlConnection instanceof HttpURLConnection)
                {
                    connection = (HttpURLConnection) urlConnection;
                }
                else
                {
                        System.out.println("请输入 URL 地址");
    //                return "获取失败";
                }
                //可提高字符流读写的效率
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String urlString = new String(in.readLine());
//                System.out.println(urlString);
                this.anaylyzeJson(urlString,acode);
            }
        }catch (JSONException | IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("天气信息导入完成......");
    }

/*
     参数：预报json字符串，城市编码。
     功能：通过获取的json解析天气并存入数据库
     返回值：无
*/
    void anaylyzeJson(String urlString,String acode) throws JSONException
    {
        JSONObject jsonObject=new JSONObject(urlString);
        JSONArray forecasts=jsonObject.getJSONArray("forecasts");
//        JSONArray casts=jsonObject.getJSONArray("casts");

        for (int i=0;i<forecasts.length();i++)
        {
            String city=forecasts.getJSONObject(i).getString("city");
            String cityId=forecasts.getJSONObject(i).getString("adcode");
//            System.out.println(city+"  "+cityId);
            JSONArray casts=forecasts.getJSONObject(i).getJSONArray("casts");
            for (int j=0;j<casts.length();j++)
            {
                String date=casts.getJSONObject(j).getString("date");
                JdbcCURD jdbcCURD=new JdbcCURD();
                String sql="update date set name="+"'"+date+"'"+" where dateId="+(j+1);
//                System.out.println(sql);
                jdbcCURD.updateTable(sql);
                String dayweather=casts.getJSONObject(j).getString("dayweather");
                String nightweather=casts.getJSONObject(j).getString("nightweather");
                String daytemp=casts.getJSONObject(j).getString("daytemp");
                String nighttemp=casts.getJSONObject(j).getString("nighttemp");
                String nightwind=casts.getJSONObject(j).getString("daywind");
                String daywind=casts.getJSONObject(j).getString("nightwind");
                String daypower=casts.getJSONObject(j).getString("daypower");
                String nightpower=casts.getJSONObject(j).getString("nightpower");

                int dateId=((j)%5+1);
//                "select * from users where id=3"
                String dayweatherEventId=util.find("select weatherEventId from weatherevents where name='"+dayweather+"'","weatherEventId");
//                System.out.println();
                String nightweatherEventId=util.find("select weatherEventId from weatherevents where name='"+nightweather+"'","weatherEventId");
                String daywindId=util.find("select windId from wind where name="+"'"+daywind+"'","windId");
//                System.out.println("select windId from wind where name="+daywind);
                String nightwindId=util.find("select windId from wind where name="+"'"+nightwind+"'","windId");


                sql="update weather set cityId="+acode+", dateId="+dateId+", dayweatherEventId="+dayweatherEventId+
                        ", nightweatherEventId="+nightweatherEventId+", daytemp="+daytemp+", nighttemp="+nighttemp+
                        ", daywindId="+daywindId+", nightwindId="+nightwindId+", daypower='"+daypower+"', nightpower='"+
                        nightpower+"' where id="+cnt++;
//                System.out.println(sql);
                jdbcCURD.updateTable(sql);
//                System.out.println(date+" "+dayweather+" "+nightweather+" "+daytemp+" "+nighttemp+" "+nightwind+" "+daypower+" "+nightpower);
            }
        }
    }

//    public static void main(String[] args) throws IOException,JSONException
//    {
//        WeatherIntoDB url=new WeatherIntoDB();
//        url.getWeather();
//    }
}