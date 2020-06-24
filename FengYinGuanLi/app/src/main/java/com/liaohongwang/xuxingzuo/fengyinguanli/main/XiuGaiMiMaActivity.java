package com.liaohongwang.xuxingzuo.fengyinguanli.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liaohongwang.xuxingzuo.fengyinguanli.Login.Login;
import com.liaohongwang.xuxingzuo.fengyinguanli.Login.NameAndClass;
import com.liaohongwang.xuxingzuo.fengyinguanli.R;
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

public class XiuGaiMiMaActivity extends AppCompatActivity {
    String fanhui;
    String name;
    String banzu;
    TextView tv_name;
    TextView tv_banzu;
    private EditText edoldPassword;
    private EditText edPassword;
    private EditText edQueRenPassword;
    public static final int aaaa = 1;
    public static final int bbbb = 2;
    public static final int cccc = 3;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg){
            switch (msg.what){
                case aaaa:
                    MainActivity.activity.finish();
                    SharedPreferences sp = getSharedPreferences("userandpassword",0);
                    SharedPreferences.Editor ed = sp.edit();

                    ed.putString("user",jiluusername);
                    ed.putString("password","");
                    ed.commit();
                    Toast.makeText(XiuGaiMiMaActivity.this, "修改成功!请重新登录", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(XiuGaiMiMaActivity.this, Login.class);
                    startActivity(intent);
                    break;
                case bbbb:
                    Toast.makeText(XiuGaiMiMaActivity.this, "服务器错误!数据库可能错误！", Toast.LENGTH_SHORT).show();
                    break;
                case cccc:
                    Toast.makeText(XiuGaiMiMaActivity.this, "服务器错误!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_xiu_gai_mi_ma);
        tv_name=findViewById(R.id.tv_xiugaimima_name);
        tv_banzu=findViewById(R.id.tv_xiugaimima_class);
        edoldPassword  = (EditText) findViewById(R.id.ed_oldpassword);
        edPassword = (EditText) findViewById(R.id.ed_newpassword1);
        edQueRenPassword = findViewById(R.id.ed_newpassword2);
        sendRequestWithOkHttp1();
    }
    private void sendRequestWithOkHttp1(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://" + IP + "/fengyinguanli/GetNameAndBanZu?username="+jiluusername)
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

    public void onxiugaimimaClick(View view){
        if (!checkNetwork()) {
            Toast toast = Toast.makeText(XiuGaiMiMaActivity.this,"网络未连接", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

        if (TextUtils.isEmpty(edoldPassword.getText().toString()) || TextUtils.isEmpty(edPassword.getText().toString()) || TextUtils.isEmpty(edQueRenPassword.getText().toString())) {
            Toast.makeText(XiuGaiMiMaActivity.this, "旧密码，新密码，确认新密码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }else {
            if (!edPassword.getText().toString().equals(edQueRenPassword.getText().toString())){
                Toast.makeText(XiuGaiMiMaActivity.this, "两次输入的新密码不同!", Toast.LENGTH_SHORT).show();
                edPassword.setText("");
                edQueRenPassword.setText("");
                return;
            }
        }
        if (jilupassword.equals(edoldPassword.getText().toString())){
            xiugaimima(jiluusername,edPassword.getText().toString());
        }else
        {
            Toast.makeText(XiuGaiMiMaActivity.this, "旧密码不对!", Toast.LENGTH_SHORT).show();
        }





    }
    public void xiugaimima(final String username, final String newpassword){

        new Thread(){
            @Override
            public void run() {
                fanhui = WebService.xiugaimima(username,newpassword);
                if (fanhui!=null){
                    if (fanhui.equals("11")){

                        Utils.runOnBackgroundThread(new Runnable() {
                            @Override
                            public void run() {

                                android.os.Message message1 = new android.os.Message();
                                message1.what = aaaa;
                                handler.sendMessage(message1);
                            }
                        });
                    }else {
                        if (fanhui.equals("22")){

                            Utils.runOnBackgroundThread(new Runnable() {
                                @Override
                                public void run() {

                                    android.os.Message message1 = new android.os.Message();
                                    message1.what = bbbb;
                                    handler.sendMessage(message1);
                                }
                            });
                        }else {

                            Utils.runOnBackgroundThread(new Runnable() {
                                @Override
                                public void run() {

                                    android.os.Message message1 = new android.os.Message();
                                    message1.what = cccc;
                                    handler.sendMessage(message1);
                                }
                            });
                        }
                    }
                }else{
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                            Toast toast = Toast.makeText(XiuGaiMiMaActivity.this,"服务器错误", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                }

            }
        }.start();
    }
    // 检测网络
    private boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }
    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
}
