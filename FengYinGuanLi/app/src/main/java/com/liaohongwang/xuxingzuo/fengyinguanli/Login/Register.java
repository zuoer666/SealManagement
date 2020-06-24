//package com.liaohongwang.xuxingzuo.fengyinguanli.Login;
//
//import android.annotation.SuppressLint;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.ConnectivityManager;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;
//import android.text.TextUtils;
//import android.view.Gravity;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.liaohongwang.xuxingzuo.fengyinguanli.R;
//import com.liaohongwang.xuxingzuo.fengyinguanli.main.MainActivity;
//import com.liaohongwang.xuxingzuo.fengyinguanli.service.WebService;
//import com.liaohongwang.xuxingzuo.fengyinguanli.utils.Utils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jilupassword;
//import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jiluusername;
//import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.quanli;
//
//public class Register extends AppCompatActivity {
//    private Spinner spinner;
//    private List<String> data_list;
//    private ArrayAdapter<String> arr_adapter;
//    private String info;
//    private String fanhui;
//    private String isShangji;
//    // 创建等待框
//    private ProgressDialog dialog;
//    private EditText edAccount;
//    private EditText edPassword;
//    private EditText edQueRenPassword;
//    private EditText edName;
//    private EditText edShangJiName;
//    private TextView tvAccount;
//    private TextView tvPassword;
//    private TextView tvTishi;
//
//    public static final int aaaa = 1;
//    public static final int bbbb = 2;
//    public static final int cccc = 3;
//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler() {
//        public void handleMessage(android.os.Message msg){
//            switch (msg.what){
//                case aaaa:
//                    dialog.dismiss();
//                    setContentView(R.layout.zhuce_success);
//                    tvAccount = (TextView) findViewById(R.id.tv_zucesuccessuser);
//                    tvPassword = (TextView) findViewById(R.id.tv_zucesuccesspas);
//                    tvAccount.setText(jiluusername);
//                    tvAccount.setTextSize(16);
//                    tvAccount.setTextColor(0xFF107ee4);
//                    tvPassword.setText(jilupassword);
//                    tvPassword.setTextSize(16);
//                    tvPassword.setTextColor(0xFF107ee4);
//                    break;
//                case bbbb:
//                    dialog.dismiss();
//                    setContentView(R.layout.zuceshibai);
//                    tvTishi = (TextView) findViewById(R.id.tv_zuceshibaitishi);
//                    tvTishi.setText(" 账号已被注册！");
//                    tvTishi.setTextSize(16);
//                    tvTishi.setTextColor(0xFF107ee4);
//                    break;
//                case cccc:
//                    dialog.dismiss();
//                    setContentView(R.layout.zuceshibai);
//                    tvTishi = (TextView) findViewById(R.id.tv_zuceshibaitishi);
//                    tvTishi.setText(" 服务器错误！联系左耳 qq：2413673661");
//                    tvTishi.setTextSize(16);
//                    tvTishi.setTextColor(0xFF107ee4);
//                    break;
//                default:
//                    break;
//            }
//        }
//    };
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        if (getSupportActionBar() != null){
//            getSupportActionBar().hide();
//        }
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//        edAccount  = (EditText) findViewById(R.id.ed_accunt);
//        edPassword = (EditText) findViewById(R.id.ed_password);
//        edQueRenPassword = findViewById(R.id.ed_password_queren);
//        edName = findViewById(R.id.ed_name);
//        edShangJiName=findViewById(R.id.ed_shangjiname);
//        spinner = (Spinner) findViewById(R.id.spinner);
//
//        //数据
//        data_list = new ArrayList<String>();
//        data_list.add("操作员");
//        data_list.add("所长");
//        data_list.add("主任");
//
//        //适配器
//        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
//        //设置样式
//        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //加载适配器
//        spinner.setAdapter(arr_adapter);
//        //initConnection();
//    }
//
//    public void onZuCeZhangHaoClick(View view){
//        if (!checkNetwork()) {
//            Toast toast = Toast.makeText(this,"网络未连接", Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.CENTER, 0, 0);
//            toast.show();
//        }
//        jiluusername = edAccount.getText().toString().trim();
//        jilupassword = edPassword.getText().toString().trim();
//        if (TextUtils.isEmpty(jiluusername) || TextUtils.isEmpty(jilupassword) || TextUtils.isEmpty(edQueRenPassword.getText().toString().trim())
//                || TextUtils.isEmpty(edName.getText().toString().trim())|| TextUtils.isEmpty(edShangJiName.getText().toString().trim())|| TextUtils.isEmpty(spinner.toString())) {
//            Toast.makeText(this, "账号、密码、姓名、职务、上级姓名不能为空!", Toast.LENGTH_SHORT).show();
//            return;
//        }else {
//            if (!jilupassword.equals(edQueRenPassword.getText().toString().trim())){
//                System.out.println(jilupassword);
//                System.out.println(edQueRenPassword.getText().toString().trim());
//                Toast.makeText(this, "两次输入密码不同!", Toast.LENGTH_SHORT).show();
//                edPassword.setText("");
//                edQueRenPassword.setText("");
//                return;
//            }
//        }
//        dialog = new ProgressDialog(this);
//        dialog.setTitle("温馨提示提示");
//        dialog.setMessage("正在注册，请稍后...");
//        dialog.setCancelable(false);
//        dialog.show();
//        System.out.println(spinner.toString()+"3344");
//        if (spinner.getSelectedItem().toString().equals("操作员")){
//            quanli= new String("1");
//        }else if (spinner.getSelectedItem().toString().equals("所长")){
//            quanli= new String("2");
//        }else if (spinner.getSelectedItem().toString().equals("主任")){
//            quanli= new String("3");
//        }
//        chaxunshangji(edShangJiName.getText().toString().trim());
//
//
//    }
//    public void chaxunshangji(final String shangji){
//
//        new Thread(){
//            @Override
//            public void run() {
//                fanhui = WebService.chaxunshangji(shangji);
//                System.out.println(fanhui);
//                if (fanhui!=null){
//                        if (fanhui.equals("shangjibucunzai")){
//
//                            Utils.runOnBackgroundThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    try {
//                                        Thread.sleep(500);
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//                                    isShangji = "no";
//
//                                    if (isShangji.equals("no")){
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                dialog.dismiss();
//                                                Toast.makeText(Register.this, "上级不存在!", Toast.LENGTH_SHORT).show();
//                                                return;
//                                            }
//                                        });
//
//                                    }
//
//                                }
//                            });
//                        }else if(fanhui.equals("shangjicunzai")){
//
//                            register(jiluusername,edName.getText().toString().trim(),spinner.getSelectedItem().toString(),edShangJiName.getText().toString().trim(),jilupassword,quanli);
//                        }
//
//                }else{
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
////                    try {
////                        Thread.sleep(500);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
//                            dialog.dismiss();
//                            Toast toast = Toast.makeText(Register.this,"服务器错误", Toast.LENGTH_SHORT);
//                            toast.show();
//                        }
//                    });
//
//                }
//
//            }
//        }.start();
//    }
//    //实现注册(1、注册成功 2、注册失败 )
//    public void register(final String loginName, final String name, final String zhiwu, final String shangji, final String pwd, final String quanxian){
//
//        new Thread(){
//            @Override
//            public void run() {
//                fanhui = WebService.zuceexecuteHttpGet(loginName,name,zhiwu,shangji,pwd,quanxian);
//                System.out.println(fanhui+2222);
//                if (fanhui!=null){
//                    if (fanhui.equals("11")){
//
//                        Utils.runOnBackgroundThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    Thread.sleep(500);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                                android.os.Message message1 = new android.os.Message();
//                                message1.what = aaaa;
//                                handler.sendMessage(message1);
//                            }
//                        });
//                    }else {
//                        if (fanhui.equals("22")){
//
//                            Utils.runOnBackgroundThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    try {
//                                        Thread.sleep(500);
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//                                    android.os.Message message1 = new android.os.Message();
//                                    message1.what = bbbb;
//                                    handler.sendMessage(message1);
//                                }
//                            });
//                        }else {
//
//                            Utils.runOnBackgroundThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    try {
//                                        Thread.sleep(500);
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//                                    android.os.Message message1 = new android.os.Message();
//                                    message1.what = cccc;
//                                    handler.sendMessage(message1);
//                                }
//                            });
//                        }
//                    }
//                }else{
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
////                    try {
////                        Thread.sleep(500);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
//                            dialog.dismiss();
//                            Toast toast = Toast.makeText(Register.this,"服务器错误", Toast.LENGTH_SHORT);
//                            toast.show();
//                        }
//                    });
//
//                }
//
//            }
//        }.start();
//    }
//    public void onZuCeZhangHaochenggong(View view){
//        //将当前账号记录下来
//
//        //开始登录
//        new Thread(new MyThread()).start();
//
//
//    }
//        public class MyThread implements Runnable {
//            @Override
//            public void run() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        dialog.setTitle("温馨提示提示");
//                        dialog.setMessage("正在登录，请稍后...");
//                        dialog.setCancelable(false);
//                        dialog.show();
//                    }
//                });
//
//
//                info = WebService.executeHttpGet(edAccount.getText().toString(), edPassword.getText().toString());
//                // info = WebServicePost.executeHttpPost(username.getText().toString(), password.getText().toString());
//
//                if (info!=null){
//                    if (info.equals("1")){
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    Thread.sleep(500);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                                dialog.dismiss();
//                                Toast toast = Toast.makeText(Register.this,"登录成功", Toast.LENGTH_SHORT);
//                                toast.show();
//
//                                //保存密码
//                                SharedPreferences sp = getSharedPreferences("userandpassword",0);
//                                SharedPreferences.Editor ed = sp.edit();
//
//                                ed.putString("user",jiluusername);
//                                ed.putString("password",jilupassword);
//                                ed.commit();
//
//                                // 登录成功 跳转
//                                Intent intent = new Intent(Register.this,MainActivity.class);
//                                startActivity(intent);
//                            }
//                        });
//                    }else{
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    Thread.sleep(500);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                                dialog.dismiss();
//                                Toast toast = Toast.makeText(Register.this,"登录失败", Toast.LENGTH_SHORT);
//                                toast.show();
//                            }
//                        });
//
//                    }
//                }else {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            dialog.dismiss();
//                            Toast toast = Toast.makeText(Register.this,"服务器错误", Toast.LENGTH_SHORT);
//                            toast.show();
//                        }
//                    });
//
//                }
//
//            }
//        }
//
//    public void onZuCeZhangHaocongxinzuce(View view){
//        startActivity(new Intent(this, Register.class));
//        finish();
//    }
//
//    // 检测网络
//    private boolean checkNetwork() {
//        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connManager.getActiveNetworkInfo() != null) {
//            return connManager.getActiveNetworkInfo().isAvailable();
//        }
//        return false;
//    }
//    //finish当前界面
//    @Override
//    protected void onStop() {
//        super.onStop();
//        this.finish();
//    }
//}
