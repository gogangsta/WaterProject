package File;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;



public class HttpRequest {
        /**
         * 向指定URL发送GET方法的请求
         *
         * @param url
         *            发送请求的URL
         *
         * @return URL 所代表远程资源的响应结果
         */
        public static String sendGet(String url, String appid, String secret) {
            String result = "";
            JSONObject jsStr = null ;  //token
            //
            String token =null;
            BufferedReader in = null;
            try {
                String urlNameString = url + "?" + appid+ "&" + secret;
                URL realUrl = new URL(urlNameString);
                // 打开和URL之间的连接
                URLConnection connection = realUrl.openConnection();
                // 设置通用的请求属性
                connection.setRequestProperty("accept", "*/*");
                connection.setRequestProperty("connection", "Keep-Alive");
                connection.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                // 建立实际的连接
                connection.connect();
                // 获取所有响应头字段
                Map<String, List<String>> map = connection.getHeaderFields();

                in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
                jsStr = JSONObject.parseObject(result);
                token = (String) jsStr.get("token");
            } catch (Exception e) {
                System.out.println("发送GET请求出现异常！" + e);
                e.printStackTrace();
            }
            // 使用finally块来关闭输入流
            finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return token;
        }


        public static String sendPost(String url, String page, String limit, String token) {
            PrintWriter out = null;
            BufferedReader in = null;
            JSONObject aStr = null ;
            String result = "";
            String a = "http://openapi.ecois.info/v3/device/16110900049544/latest";

            String param=page+"&"+limit;
            try {
                // URL realUrl = new URL(url);
                URL realUrl = new URL(a);
                // 打开和URL之间的连接
                URLConnection conn = realUrl.openConnection();
                // 设置通用的请求属性
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", token);
                // 发送POST请求必须设置如下两行
                conn.setDoOutput(true);
                conn.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                //out.print(param);
                // flush输出流的缓冲
                out.flush();
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
                result = new String(result.getBytes("gbk"),"utf-8");
                aStr = JSONObject.parseObject(result);
                System.out.println(result);
                DownstreamSoil soil=new DownstreamSoil();
                soil.soil(aStr);

            } catch (Exception e) {
                System.out.println("发送 1 请求出现异常！"+e);
                e.printStackTrace();
            }
            //使用finally块来关闭输出流、输入流
            finally{
                try{
                    if(out!=null){
                        out.close();
                    }
                    if(in!=null){
                        in.close();
                    }
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
            return result;
        }

        public static String sendPostt(String url, String page, String limit, String token) {
            PrintWriter out = null;
            BufferedReader in = null;
            JSONObject aStr = null ;
            JSONObject bStr = null ;
            String result = "";
            String a = "http://openapi.ecois.info/v3/device/18012200073711/latest";


            String param=page+"&"+limit;
            try {
                // URL realUrl = new URL(url);
                URL realUrl = new URL(a);
                // 打开和URL之间的连接
                URLConnection conn = realUrl.openConnection();
                // 设置通用的请求属性
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", token);
                // 发送POST请求必须设置如下两行
                conn.setDoOutput(true);
                conn.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                //out.print(param);
                // flush输出流的缓冲
                out.flush();
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
                result = new String(result.getBytes("gbk"),"utf-8");
                aStr = JSONObject.parseObject(result);
                Rainfall rainfall = new Rainfall();
                rainfall.rainfall(aStr);

            } catch (Exception e) {
                System.out.println("发送 2 请求出现异常！"+e);
                e.printStackTrace();
            }
            //使用finally块来关闭输出流、输入流
            finally{
                try{
                    if(out!=null){
                        out.close();
                    }
                    if(in!=null){
                        in.close();
                    }
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
            return result;
        }

}
