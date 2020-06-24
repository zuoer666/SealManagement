package com.liaohongwang.xuxingzuo.fengyinguanli.service;

/**
 * Created by xuxingzuo on 2017/11/2.
 */


import android.support.annotation.Nullable;

import com.liaohongwang.xuxingzuo.fengyinguanli.utils.TimeUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.IP;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.banzu;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jiluusername;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.name;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.quanxian;

public class WebService {

    public static String xiugaimima(String username, String password) {

        HttpURLConnection conn = null;
        InputStream is = null;

        try {
            // 用户名 密码
            // URL 地址
            String path = "http://" + IP + "/fengyinguanli/XiuGaiMiMa";
            path = path + "?username=" + username + "&newpassword=" + password;

            conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(3000); // 设置超时时间
            conn.setReadTimeout(3000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                System.out.println(is);
                return parseInfo(is);
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
    public static String xunjian(String fengyinid, String dianbiaoid,String dianbiaoxiangid,String xunjianrenrenid, String dili) {
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            // 用户名 密码
            // URL 地址
            String time = TimeUtils.getTime();
            try {
                time = URLEncoder.encode(time, "utf-8");

            } catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            String path = "http://" + IP + "/fengyinguanli/XunJian";
            //path = path + "?fabu_user="+jiluusername+"&fabu_title=" + title + "&fabu_content=" + content + "&fabu_money=" + money+"&fabu_time="+time;

            conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(10000); // 设置超时时间
            conn.setReadTimeout(3000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST"); // 设置获取信息方式
            // conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            String params = "time=" + time +"&fengyinid=" + fengyinid + "&dianbiaoid=" + dianbiaoid + "&dianbiaoxiangid=" + dianbiaoxiangid + "&xunjianrenrenid=" + xunjianrenrenid +"&dili=" + dili+"&name=" + name;
            System.out.println(params);
            OutputStream os = conn.getOutputStream();
            os.write(params.getBytes());
            os.flush();
            os.close();
            if (conn.getResponseCode() == 200) {

                is = conn.getInputStream();
                StringBuilder sb = new StringBuilder();
                byte[] bytes = new byte[1024];
                int i = 0;
                while ((i = is.read(bytes)) != -1) {
                    sb.append(new String(bytes, 0, i, "utf-8"));
                }
                System.out.println(sb);

                String c;
                c = new String(sb);
                is.close();
                return c;
            }
//            if (conn.getResponseCode() == 200) {
//                is = conn.getInputStream();
//                System.out.println(is);
//                return parseInfo(is);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;

    }
    @Nullable

    public static String fengyinchaichu(String fengyinid, String dianbiaoid,String chaichurenrenid,String dianbiaoxiangyuanlai,String dianbiaoxiangxianzai) {
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            // 用户名 密码
            // URL 地址
            String time = TimeUtils.getTime();
            try {
                time = URLEncoder.encode(time, "utf-8");

            } catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            String path = "http://" + IP + "/fengyinguanli/FengYinChaiChu";
            //path = path + "?fabu_user="+jiluusername+"&fabu_title=" + title + "&fabu_content=" + content + "&fabu_money=" + money+"&fabu_time="+time;

            conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(10000); // 设置超时时间
            conn.setReadTimeout(3000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST"); // 设置获取信息方式
            // conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            String params = "fengyinid=" + fengyinid + "&dianbiaoid=" + dianbiaoid + "&chaichurenrenid=" + chaichurenrenid + "&chaichushijian=" + time + "&dianbiaoxiangyuanlai=" + dianbiaoxiangyuanlai + "&dianbiaoxiangxianzai=" + dianbiaoxiangxianzai+ "&chainame=" + name;

            OutputStream os = conn.getOutputStream();
            os.write(params.getBytes());
            os.flush();
            os.close();
            if (conn.getResponseCode() == 200) {

                is = conn.getInputStream();
                StringBuilder sb = new StringBuilder();
                byte[] bytes = new byte[1024];
                int i = 0;
                while ((i = is.read(bytes)) != -1) {
                    sb.append(new String(bytes, 0, i, "utf-8"));
                }
                System.out.println(sb);

                String c;
                c = new String(sb);
                is.close();
                return c;
            }
//            if (conn.getResponseCode() == 200) {
//                is = conn.getInputStream();
//                System.out.println(is);
//                return parseInfo(is);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;

    }
    public static String fengyinanzhuang(String fengyinid, String dianbiaoid,String dianbiaoxiangid,String anzhuangrenid,String dili,String banzu,String yuanlai) {
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            // 用户名 密码
            // URL 地址
            String time = TimeUtils.getTime();
            try {
                time = URLEncoder.encode(time, "utf-8");
                dili = URLEncoder.encode(dili, "utf-8");

            } catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            String path = "http://" + IP + "/fengyinguanli/FengYinAnZhuang";
            //path = path + "?fabu_user="+jiluusername+"&fabu_title=" + title + "&fabu_content=" + content + "&fabu_money=" + money+"&fabu_time="+time;

            conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(10000); // 设置超时时间
            conn.setReadTimeout(3000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST"); // 设置获取信息方式
            // conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            String params = "fengyinid=" + fengyinid + "&dianbiaoid=" + dianbiaoid + "&dianbiaoxiangid=" + dianbiaoxiangid + "&anzhuangrenid=" + anzhuangrenid + "&anzhuangshijian=" + time + "&dili=" + dili+ "&banzu=" + banzu+ "&anname=" + name+ "&quanxian=" + quanxian+ "&yuanlaibiaoxiang=" + yuanlai;

            OutputStream os = conn.getOutputStream();
            os.write(params.getBytes());
            os.flush();
            os.close();
            if (conn.getResponseCode() == 200) {

                is = conn.getInputStream();
                StringBuilder sb = new StringBuilder();
                byte[] bytes = new byte[1024];
                int i = 0;
                while ((i = is.read(bytes)) != -1) {
                    sb.append(new String(bytes, 0, i, "utf-8"));
                }
                System.out.println(sb);

                String c;
                c = new String(sb);
                is.close();
                return c;
            }
//            if (conn.getResponseCode() == 200) {
//                is = conn.getInputStream();
//                System.out.println(is);
//                return parseInfo(is);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;

    }
        @Nullable
    public static String pilianglingyong(String fengyinid1,String fengyinid2, String username) {
        String time = TimeUtils.getTime();
        HttpURLConnection conn = null;
        InputStream is = null;
            try {
                // 用户名 密码
                // URL 地址
                try {
                    time = URLEncoder.encode(time, "utf-8");

                } catch (UnsupportedEncodingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                String path = "http://" + IP + "/fengyinguanli/PiLiangLingQu";
                //path = path + "?fabu_user="+jiluusername+"&fabu_title=" + title + "&fabu_content=" + content + "&fabu_money=" + money+"&fabu_time="+time;

                conn = (HttpURLConnection) new URL(path).openConnection();
                conn.setConnectTimeout(10000); // 设置超时时间
                conn.setReadTimeout(3000);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setRequestMethod("POST"); // 设置获取信息方式
                // conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式
                conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
                String params = "fengyinid1="+fengyinid1+"&fengyinid2=" + fengyinid2 + "&username=" + username +"&time=" + time+ "&banzu=" + banzu+ "&quanxian=" + quanxian+ "&name=" + name;

                OutputStream os = conn.getOutputStream();
                os.write(params.getBytes());
                os.flush();
                os.close();
                if (conn.getResponseCode() == 200) {

                    is = conn.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    byte[] bytes = new byte[1024];
                    int i = 0;
                    while ((i = is.read(bytes)) != -1) {
                        sb.append(new String(bytes, 0, i, "utf-8"));
                    }
                    System.out.println(sb);

                    String c;
                    c = new String(sb);
                    is.close();
                    return c;
                }
//            if (conn.getResponseCode() == 200) {
//                is = conn.getInputStream();
//                System.out.println(is);
//                return parseInfo(is);
//            }

            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 意外退出时进行连接关闭保护
                if (conn != null) {
                    conn.disconnect();
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            return null;

    }
    @Nullable
    public static String dangelingyong(String fengyinid, String username) {
        String time = TimeUtils.getTime();
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            // 用户名 密码
            // URL 地址
            try {
                time = URLEncoder.encode(time, "utf-8");

            } catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            String path = "http://" + IP + "/fengyinguanli/DanGeLingQu";
            //path = path + "?fabu_user="+jiluusername+"&fabu_title=" + title + "&fabu_content=" + content + "&fabu_money=" + money+"&fabu_time="+time;

            conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(10000); // 设置超时时间
            conn.setReadTimeout(3000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST"); // 设置获取信息方式
            // conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            String params = "fengyinid="+fengyinid+"&username=" + username + "&time=" + time+ "&banzu=" + banzu+ "&quanxian=" + quanxian+ "&name=" + name;

            OutputStream os = conn.getOutputStream();
            os.write(params.getBytes());
            os.flush();
            os.close();
            if (conn.getResponseCode() == 200) {

                is = conn.getInputStream();
                StringBuilder sb = new StringBuilder();
                byte[] bytes = new byte[1024];
                int i = 0;
                while ((i = is.read(bytes)) != -1) {
                    sb.append(new String(bytes, 0, i, "utf-8"));
                }
                System.out.println(sb);

                String c;
                c = new String(sb);
                is.close();
                return c;
            }
//            if (conn.getResponseCode() == 200) {
//                is = conn.getInputStream();
//                System.out.println(is);
//                return parseInfo(is);
//            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;


    }
    // 通过Get方式获取HTTP服务器数据
    @Nullable
    public static String executeHttpGet(String username, String password) {

        HttpURLConnection conn = null;
        InputStream is = null;

        try {
            // 用户名 密码
            // URL 地址
            String path = "http://" + IP + "/fengyinguanli/LogLet";
            path = path + "?username=" + username + "&password=" + password;

            conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(3000); // 设置超时时间
            conn.setReadTimeout(3000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                System.out.println(is);
                return parseInfo(is);
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
    public static String zuceexecuteHttpGet(String loginName, String name,String zhiwu,String shangji,String pwd,String quanxian) {
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            // 用户名 密码
            // URL 地址
            String time = TimeUtils.getTime();
            try {
                zhiwu = URLEncoder.encode(zhiwu, "utf-8");
                name = URLEncoder.encode(name, "utf-8");
                shangji = URLEncoder.encode(shangji, "utf-8");

            } catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            String path = "http://" + IP + "/fengyinguanli/RegLet";
            //path = path + "?fabu_user="+jiluusername+"&fabu_title=" + title + "&fabu_content=" + content + "&fabu_money=" + money+"&fabu_time="+time;

            conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(10000); // 设置超时时间
            conn.setReadTimeout(3000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST"); // 设置获取信息方式
            // conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            String params = "r_username="+loginName+"&r_name=" + name + "&r_zhiwu=" + zhiwu + "&r_shangji=" + shangji+"&r_pwd="+pwd+"&r_quanxian="+quanxian;

            OutputStream os = conn.getOutputStream();
            os.write(params.getBytes());
            os.flush();
            os.close();
            if (conn.getResponseCode() == 200) {

                is = conn.getInputStream();
                StringBuilder sb = new StringBuilder();
                byte[] bytes = new byte[1024];
                int i = 0;
                while ((i = is.read(bytes)) != -1) {
                    sb.append(new String(bytes, 0, i, "utf-8"));
                }
                System.out.println(sb);

                String c;
                c = new String(sb);
                is.close();
                return c;
            }
//            if (conn.getResponseCode() == 200) {
//                is = conn.getInputStream();
//                System.out.println(is);
//                return parseInfo(is);
//            }

    }catch (Exception e) {
        e.printStackTrace();
    } finally {
        // 意外退出时进行连接关闭保护
        if (conn != null) {
            conn.disconnect();
        }
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
        return null;
                // 用户名 密码
            // URL 地址

//            String path = "http://" + IP + "/fengyinguanli/RegLet";
//            path = path + "?r_name=" + loginName + "&r_name=" + name + "&r_zhiwu=" + zhiwu + "&r_shangji=" + shangji + "&r_pwd=" + pwd + "&r_quanxian=" + quanxian;
//
//            conn = (HttpURLConnection) new URL(path).openConnection();
//            conn.setConnectTimeout(3000); // 设置超时时间
//            conn.setReadTimeout(3000);
//            conn.setDoInput(true);
//            conn.setRequestMethod("GET"); // 设置获取信息方式
//            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

//            if (conn.getResponseCode() == 200) {
//                is = conn.getInputStream();
//                System.out.println(is);
//                return parseInfo(is);
//            }

//        }catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 意外退出时进行连接关闭保护
//            if (conn != null) {
//                conn.disconnect();
//            }
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//        return null;
    }
    public static String chaxunshangji(String shangji){
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            // 用户名 密码
            // URL 地址
            String time = TimeUtils.getTime();
            try {
                shangji = URLEncoder.encode(shangji, "utf-8");

            } catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            String path = "http://" + IP + "/fengyinguanli/ChaXunShangJi";
            //path = path + "?fabu_user="+jiluusername+"&fabu_title=" + title + "&fabu_content=" + content + "&fabu_money=" + money+"&fabu_time="+time;

            conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(10000); // 设置超时时间
            conn.setReadTimeout(3000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST"); // 设置获取信息方式
            // conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            String params = "chaxunshangji="+shangji;

            OutputStream os = conn.getOutputStream();
            os.write(params.getBytes());
            os.flush();
            os.close();
            if (conn.getResponseCode() == 200) {

                is = conn.getInputStream();
                StringBuilder sb = new StringBuilder();
                byte[] bytes = new byte[1024];
                int i = 0;
                while ((i = is.read(bytes)) != -1) {
                    sb.append(new String(bytes, 0, i, "utf-8"));
                }
                System.out.println(sb);

                String c;
                c = new String(sb);
                is.close();
                return c;
            }
//            if (conn.getResponseCode() == 200) {
//                is = conn.getInputStream();
//                System.out.println(is);
//                return parseInfo(is);
//            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;

    }
    public static String faBu(String title, String content, String money){
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            // 用户名 密码
            // URL 地址
            String time = TimeUtils.getTime();
            try {
                title = URLEncoder.encode(title, "utf-8");
                content = URLEncoder.encode(content, "utf-8");
                money = URLEncoder.encode(money, "utf-8");

            } catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            String path = "http://" + IP + "/lanlebaLogin/FaBu";
            //path = path + "?fabu_user="+jiluusername+"&fabu_title=" + title + "&fabu_content=" + content + "&fabu_money=" + money+"&fabu_time="+time;

            conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(10000); // 设置超时时间
            conn.setReadTimeout(3000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST"); // 设置获取信息方式
           // conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            String params = "fabu_user="+jiluusername+"&fabu_title=" + title + "&fabu_content=" + content + "&fabu_money=" + money+"&fabu_time="+time;

            OutputStream os = conn.getOutputStream();
            os.write(params.getBytes());
            os.flush();
            os.close();
            if (conn.getResponseCode() == 200){

                is = conn.getInputStream();
                StringBuilder sb = new StringBuilder();
                byte[] bytes = new byte[1024];
                int i = 0;
                while ((i = is.read(bytes)) != -1){
                    sb.append(new String(bytes,0,i,"utf-8"));
                }
                System.out.println(sb);

                String c;
                c = new String(sb);
                is.close();
                return c;



            }
//            if (conn.getResponseCode() == 200) {
//                is = conn.getInputStream();
//                System.out.println(is);
//                return parseInfo(is);
//            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
    public static String jiedan(int id, String jiedanuser){
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            // 用户名 密码
            // URL 地址
            String time = TimeUtils.getTime();
            String path = "http://" + IP + "/lanlebaLogin/JieDan";
            path = path + "?jiedan_id="+id+"&jiedan_user=" + jiedanuser;

            conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(3000); // 设置超时时间
            conn.setReadTimeout(3000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                System.out.println(is);
                return parseInfo(is);
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
    public static String fabuquerenwancheng(int id){
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            // 用户名 密码
            // URL 地址
            String time = TimeUtils.getTime();
            String path = "http://" + IP + "/lanlebaLogin/DingDanFaBuWanCheng";
            path = path + "?fabuwancheng_id="+id;

            conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(3000); // 设置超时时间
            conn.setReadTimeout(3000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                System.out.println(is);
                return parseInfo(is);
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
    public static String jiedanquerenwancheng(int id){
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            // 用户名 密码
            // URL 地址
            String time = TimeUtils.getTime();
            String path = "http://" + IP + "/lanlebaLogin/DingDanJieDanWanCheng";
            path = path + "?jiedanwancheng_id="+id;

            conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(3000); // 设置超时时间
            conn.setReadTimeout(3000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                System.out.println(is);
                return parseInfo(is);
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
    public static String chaxunquanli(String username) {
        String time = TimeUtils.getTime();
        HttpURLConnection conn = null;
        InputStream is = null;

        try {
            // 用户名 密码
            // URL 地址
            String path = "http://" + IP + "/fengyinguanli/QuanXianChaXun";
            path = path + "?username=" + username;

            conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(3000); // 设置超时时间
            conn.setReadTimeout(3000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                System.out.println(is);
                return parseInfo(is);
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    // 将输入流转化为 String 型
    private static String parseInfo(InputStream inStream) throws Exception {
        byte[] data = read(inStream);
        // 转化为字符串
        return new String(data, "UTF-8");
    }

    // 将输入流转化为byte型
    public static byte[] read(InputStream inStream) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        inStream.close();
        return outputStream.toByteArray();
    }
}