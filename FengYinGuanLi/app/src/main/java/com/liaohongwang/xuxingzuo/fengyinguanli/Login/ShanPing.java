package com.liaohongwang.xuxingzuo.fengyinguanli.Login;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.liaohongwang.xuxingzuo.fengyinguanli.R;
import com.liaohongwang.xuxingzuo.fengyinguanli.main.MainActivity;
import com.liaohongwang.xuxingzuo.fengyinguanli.service.WebService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.IP;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jilupassword;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jiluusername;
import static java.lang.Thread.sleep;


public class ShanPing extends AppCompatActivity {
    // 返回的数据
    private String info;
    String banbenid;
    String xiazaidizhi;
    String conxiangxi;
    int a = 2;
    // 返回主线程更新数据
    private static Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //取掉标题栏和菜单栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        if (!checkNetwork()) {
            Toast toast = Toast.makeText(ShanPing.this,"网络未连接", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shan_ping);

        new Thread(new MyThread1()).start();
        ImageView imageLogo = (ImageView)findViewById(R.id.zengzaijinrulogo);
        //渐变 透明到不透明

        AlphaAnimation animation = new AlphaAnimation(0.1f,1.0f);
        animation.setDuration(2000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //获取账号密码
                SharedPreferences sp = getSharedPreferences("userandpassword",0);
                String user = sp.getString("user","abgjklabgajklegbajkbgakjbgjsdbnbdgnbaesjkgbejgbjgbwjegbejwKgbkwjgbgjbl");
                String password = sp.getString("password","abgjklabgajklegbajkbgakjbgjsdbnbdgnbaesjkgbejgbjgbwjegbejwKgbkwjgbgjbl");
                System.out.println(user+"111111");
                System.out.println(password+"111111");
                if (user.equals("abgjklabgajklegbajkbgakjbgjsdbnbdgnbaesjkgbejgbjgbwjegbejwKgbkwjgbgjbl")&&password.equals("abgjklabgajklegbajkbgakjbgjsdbnbdgnbaesjkgbejgbjgbwjegbejwKgbkwjgbgjbl")){
                    //跳转新界面
                    if (a ==2){
                        Intent intent = new Intent(ShanPing.this,Login.class);
                        startActivity(intent);
                    }

                }else{
                    jiluusername = user;
                    jilupassword = password;

                    new Thread(new MyThread()).start();
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imageLogo.setAnimation(animation);


    }

    public class MyThread1 implements Runnable {
        @Override
        public void run() {
            try {

                banbenid=getVersion();
                xiazaidizhi=getDiZhi();
                conxiangxi=getContent();
                sleep(800);
                checkVersion();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public void checkVersion() {
        //如果检测本程序的版本号小于服务器的版本号，那么提示用户更新//闪屏界面图片


        if (!banbenid.equals(Integer.toString(getVersionCode()))) {
            showDialogUpdate();//弹出提示版本更新的对话框
            a=1;
        }else{
            a=2;




        }
    }

    private void showDialogUpdate() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
                AlertDialog.Builder builer = new AlertDialog.Builder(ShanPing.this);
                // 设置提示框的标题

                builer.setTitle("版本升级");
                builer.setMessage(conxiangxi);
                builer.setPositiveButton("确定", (dialog, which) -> loadNewVersionProgress());
                //当点取消按钮时不做任何举动
                builer.setNegativeButton("取消", (dialogInterface, i) -> {finish();});
                AlertDialog dialog = builer.create();
                dialog.show();
            }
        });




    }

    private void loadNewVersionProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://"+IP+"/fengyinguanli/app-debug.apk"));
                startActivity(intent);
            }
        });
    }

//    /**
//     * 下载新版本程序
//     */
//    private void loadNewVersionProgress() {
//        final   String uri=xiazaidizhi;
//        final ProgressDialog pd;    //进度条对话框
//        pd = new  ProgressDialog(this);
//        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        pd.setMessage("正在下载更新");
//
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                pd.show();
//            }
//        });
//        //启动子线程下载任务
//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    File file = getFileFromServer(uri, pd);
//                    sleep(1000);
//                    installApk(file);
//                    pd.dismiss(); //结束掉进度条对话框
//                } catch (Exception e) {
//                    //下载apk失败
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
//                            Toast.makeText(getApplicationContext(), "下载新版本失败", Toast.LENGTH_LONG).show();
//                        }
//                    });
//
//                    e.printStackTrace();
//                }
//            }}.start();
//    }
//
//    /**
//     * 安装apk
//     */
//    protected void installApk(File file) {
//        Intent intent = new Intent();
//        //执行动作
//        intent.setAction(Intent.ACTION_VIEW);
//        //执行的数据类型
//        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//        startActivity(intent);
//    }
//
//    /**
//     * 从服务器获取apk文件的代码
//     * 传入网址uri，进度条对象即可获得一个File文件
//     * （要在子线程中执行哦）
//     */
//    public static File getFileFromServer(String uri, ProgressDialog pd) throws Exception{
//        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
//        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//            URL url = new URL(uri);
//            HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
//            conn.setConnectTimeout(5000);
//            //获取到文件的大小
//            pd.setMax(conn.getContentLength());
//            InputStream is = conn.getInputStream();
//            long time= System.currentTimeMillis();//当前时间的毫秒数
//            File file = new File(Environment.getExternalStorageDirectory(), time+"updata.apk");
//            FileOutputStream fos = new FileOutputStream(file);
//            BufferedInputStream bis = new BufferedInputStream(is);
//            byte[] buffer = new byte[1024];
//            int len ;
//            int total=0;
//            while((len =bis.read(buffer))!=-1){
//                fos.write(buffer, 0, len);
//                total+= len;
//                //获取当前下载量
//                pd.setProgress(total);
//            }
//            fos.close();
//            bis.close();
//            is.close();
//            return file;
//        }
//        else{
//            return null;
//        }
//    }


    /*
     * 获取当前程序的版本名
     */
    private String getVersionName() throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        Log.e("TAG", "版本号" + packInfo.versionCode);
        Log.e("TAG", "版本名" + packInfo.versionName);
        return packInfo.versionName;

    }


    /*
     * 获取当前程序的版本号
     */
    private int getVersionCode() {
        try {

            //获取packagemanager的实例
            PackageManager packageManager = getPackageManager();
            //getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            Log.e("TAG", "版本号" + packInfo.versionCode);
            Log.e("TAG", "版本名" + packInfo.versionName);
            return packInfo.versionCode;

        } catch (Exception e) {
            e.printStackTrace();

        }

        return  1;
    }
    private String getVersion() throws IOException, JSONException {
        URL url = new URL("http://47.93.22.29:8080/fengyinguanli/version.txt");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setReadTimeout(8 * 1000);
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String string;
        string = bufferedReader.readLine();
        //对json数据进行解析
        JSONObject jsonObject = new JSONObject(string);
        byte[] type = jsonObject.getString("code").getBytes();
        String strings = new String(type,"utf-8");
        return strings;
    }
    private String getDiZhi() throws IOException, JSONException {
        URL url = new URL("http://47.93.22.29:8080/fengyinguanli/version.txt");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setReadTimeout(8 * 1000);
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String string;
        string = bufferedReader.readLine();
        //对json数据进行解析
        JSONObject jsonObject = new JSONObject(string);
        String strings = jsonObject.getString("update");
        return strings;
    }
    private String getContent() throws IOException, JSONException {
        URL url = new URL("http://47.93.22.29:8080/fengyinguanli/version.txt");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setReadTimeout(8 * 1000);
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String string;
        string = bufferedReader.readLine();
        //对json数据进行解析
        JSONObject jsonObject = new JSONObject(string);
        String strings = jsonObject.getString("content");
        return strings;
    }
    public class MyThread implements Runnable {
        @Override
        public void run() {

            info = WebService.executeHttpGet(jiluusername, jilupassword);
            System.out.println("222222");
            // info = WebServicePost.executeHttpPost(username.getText().toString(), password.getText().toString());


            if (a ==2){
                if (info!=null){
                    if (info.equals("1")){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //sendRequestWithOkHttp();
                                try {
                                    sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Intent intent = new Intent(ShanPing.this,MainActivity.class);
                                startActivity(intent);
                            }
                        });
                    }else{
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //自动登录失败
                                //跳转新界面
                                Intent intent = new Intent(ShanPing.this,Login.class);
                                startActivity(intent);
                            }
                        });
                    }

                }else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //自动登录失败
                            //跳转新界面
                            Intent intent = new Intent(ShanPing.this,Login.class);
                            startActivity(intent);
                        }
                    });

                }
            }

        }
    }
//    public static void sendRequestWithOkHttp(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder()
//                            .url("http://" + IP + "/fengyinguanli/GetBasicInfo?username="+jiluusername)
//                            .build();
//                    Response response = client.newCall(request).execute();
//                    String reData = response.body().string();
//                    parseJSOMWithGSON(reData);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();
//    }
//    private static void parseJSOMWithGSON(String jsonData){
//
//
//        //List<DingDan> mLists= new ArrayList<DingDan>();
//        Gson gson = new Gson();
//        List<BasicInfo> fuJinList1 = gson.fromJson(jsonData,new TypeToken<List<BasicInfo>>(){}.getType());
//        for (BasicInfo fuJin :fuJinList1){
//            name = fuJin.getName();
//            banzu =fuJin.getBanzu();
//            quanxian = fuJin.getQuanxian();
//        }
//    }
    //finish当前界面
    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
    public boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }
}
