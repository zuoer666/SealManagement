package com.liaohongwang.xuxingzuo.fengyinguanli.Login;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liaohongwang.xuxingzuo.fengyinguanli.R;
import com.liaohongwang.xuxingzuo.fengyinguanli.main.MainActivity;
import com.liaohongwang.xuxingzuo.fengyinguanli.service.WebService;
import com.liaohongwang.xuxingzuo.fengyinguanli.utils.Utils;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.IP;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jilupassword;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jiluusername;

public class Login extends AppCompatActivity implements OnClickListener {

    String editString;
    String name;
    String banzu;
    TextView tv_name;
    TextView tv_banzu;
    // 登陆按钮
    private Button logbtn;
    // 注册文本
    private TextView regtv;
    // 显示用户名和密码
    EditText username, password;
    // 创建等待框
    private ProgressDialog dialog;
    // 返回的数据
    private String info;
    // 返回主线程更新数据
    private static Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 获取控件
        username = (EditText) findViewById(R.id.ed_accunt);
        password = (EditText) findViewById(R.id.ed_password);
        logbtn = (Button) findViewById(R.id.btn_login);
        //regtv = (TextView) findViewById(R.id.tv_register);
        tv_name=findViewById(R.id.tv_login_name);
        tv_banzu=findViewById(R.id.tv_login_class);
        SharedPreferences sp = getSharedPreferences("userandpassword",0);
        String bcuser = sp.getString("user","");
        String bcpassword = sp.getString("password","");

        username.setText(bcuser);
        password.setText(bcpassword);

        // 设置按钮监听器
        logbtn.setOnClickListener(this);
//        regtv.setOnClickListener(this);
        username.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if(delayRun!=null){
                    //每次editText有变化的时候，则移除上次发出的延迟线程
                    handler.removeCallbacks(delayRun);
                }
                editString = s.toString();

                //延迟1000ms，如果不再输入字符，则执行该线程的run方法
                handler.postDelayed(delayRun, 1000);


            }
        });
    }
    private Runnable delayRun = new Runnable() {

        @Override
        public void run() {
            //在这里调用服务器的接口，获取数据
            sendRequestWithOkHttp1();
        }

    };

    private void sendRequestWithOkHttp1(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://" + IP + "/fengyinguanli/GetNameAndBanZu?username="+editString)
                            .build();
                    Response response = client.newCall(request).execute();
                    String reData = response.body().string();
                    parseJSOMWithGSON1(reData);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void parseJSOMWithGSON1(String jsonData){


        //List<DingDan> mLists= new ArrayList<DingDan>();
        Gson gson = new Gson();
        List<NameAndClass> fuJinList1 = gson.fromJson(jsonData,new TypeToken<List<NameAndClass>>(){}.getType());
        for (NameAndClass fuJin :fuJinList1){
            name = fuJin.getName();
            banzu =fuJin.getBanzu();
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
            tv_name.setText(name);
            tv_banzu.setText(banzu);
            }
        });

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (Utils.isFastClick()) {
                    // 检测网络，无法检测wifi
                    if (!checkNetwork()) {
                        Toast toast = Toast.makeText(Login.this,"网络未连接", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        break;
                    }
                    jiluusername = username.getText().toString();
                    jilupassword = password.getText().toString();
                    if (TextUtils.isEmpty(jiluusername) || TextUtils.isEmpty(jilupassword)) {
                        Toast.makeText(this, "账号或者密码不能为空!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // 提示框
                    dialog = new ProgressDialog(this);
                    dialog.setTitle("温馨提示");
                    dialog.setMessage("正在登陆，请稍后...");
                    dialog.setCancelable(false);
                    dialog.show();
                    // 创建子线程，分别进行Get和Post传输

                    new Thread(new MyThread()).start();
                    break;
                }

//            case R.id.tv_register:
//                Intent regItn = new Intent(Login.this, Register.class);
//                // overridePendingTransition(anim_enter);
//                startActivity(regItn);
//                break;
        }
        ;
    }

    // 子线程接收数据，主线程修改数据
    public class MyThread implements Runnable {
        @Override
        public void run() {

            info = WebService.executeHttpGet(username.getText().toString(), password.getText().toString());
            // info = WebServicePost.executeHttpPost(username.getText().toString(), password.getText().toString());
            //测试
            //info = "1";
            if (info!=null){
                if (info.equals("1")){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            //保存密码
                            SharedPreferences sp = getSharedPreferences("userandpassword",0);
                            SharedPreferences.Editor ed = sp.edit();

                            ed.putString("user",jiluusername);
                            ed.putString("password",jilupassword);
                            ed.commit();

                            Toast toast = Toast.makeText(Login.this,"登录成功", Toast.LENGTH_SHORT);
                            toast.show();
                            dialog.dismiss();
                            // 登录成功 跳转
                            Intent intent = new Intent(Login.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }else{
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Toast toast = Toast.makeText(Login.this,"登录失败,账号或密码错误", Toast.LENGTH_SHORT);
                            toast.show();
                            dialog.dismiss();
                        }
                    });

                }

            }else {
                handler.post(new Runnable() {
                @Override
                public void run() {
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    Toast toast = Toast.makeText(Login.this,"服务器错误", Toast.LENGTH_SHORT);
                    toast.show();
                    dialog.dismiss();
                }
            });

            }
        }
    }

    // 检测网络
    public boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }

    //finish当前界面
    @Override
    protected void onStop() {
        super.onStop();
       // this.finish();
    }

}