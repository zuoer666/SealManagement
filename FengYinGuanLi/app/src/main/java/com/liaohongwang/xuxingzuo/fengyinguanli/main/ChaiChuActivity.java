package com.liaohongwang.xuxingzuo.fengyinguanli.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.liaohongwang.xuxingzuo.fengyinguanli.R;
import com.liaohongwang.xuxingzuo.fengyinguanli.custom.CustomScanActivity;
import com.liaohongwang.xuxingzuo.fengyinguanli.service.WebService;
import com.liaohongwang.xuxingzuo.fengyinguanli.utils.Utils;

import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jiluusername;
import static com.liaohongwang.xuxingzuo.fengyinguanli.utils.Utils.jiequshuizi;

public class ChaiChuActivity extends AppCompatActivity {
    private String chaichufanhui;
    private Button btn_chaichu_1;
    private Button btn_chaichu_2;
    private Button btn_chaichu_3;
    private EditText ed_chaichu_1;
    private EditText ed_chaichu_2;
    //private EditText ed_chaichu_3;
    private EditText ed_chaichu_4;
    public static final int aaaa = 1;
    public static final int bbbb = 2;
    public static final int cccc = 3;
    public static final int dddd = 4;
    private static final int SCAN_CODE = 1003;
    private static final int SCAN_CODE1 = 1004;
    private static final int SCAN_CODE2 = 1005;
    private static final int SCAN_CODE3 = 1006;
    private ImageView btn_scan1;
    private ImageView btn_scan2;
    private ImageView btn_scan3;
    private ImageView btn_scan4;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg){
            switch (msg.what){
                case aaaa:
                    ed_chaichu_1.setText("");
                    ed_chaichu_2.setText("");
                    //ed_chaichu_3.setText("");
                    ed_chaichu_4.setText("");
                    Toast.makeText(ChaiChuActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    break;
                case bbbb:
                    Toast.makeText(ChaiChuActivity.this, "提交失败!可能没有安装！", Toast.LENGTH_SHORT).show();
                    break;
                case cccc:

                    Toast.makeText(ChaiChuActivity.this, "这个电表未安装！", Toast.LENGTH_SHORT).show();
                    break;
                case dddd:

                    Toast.makeText(ChaiChuActivity.this, "已拆除，新的电表箱封印未使用，原因：原来电表箱里的电表都拆除或没有表！", Toast.LENGTH_LONG).show();
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
        setContentView(R.layout.activity_chai_chu);
        initView();
    }
    private void initView(){
//        btn_chaichu_1 = (Button) findViewById(R.id.btn_chaichu_dianbiao_queding);
//        btn_chaichu_2 = (Button) findViewById(R.id.btn_chaichu_dianbiao_queding);
        btn_chaichu_3 = (Button) findViewById(R.id.btn_chaichu_submit);
        ed_chaichu_1 = findViewById(R.id.et_chaichu_fengyin_info);
//        ed_chaichu_1.setFocusable(false);
//        ed_chaichu_1.setFocusableInTouchMode(false);
        ed_chaichu_2 = findViewById(R.id.et_chaichu_dianbiao_info);
//        ed_chaichu_2.setFocusable(false);
//        ed_chaichu_2.setFocusableInTouchMode(false);
        //ed_chaichu_3 = findViewById(R.id.et_chaichu_dianbiaoxiangyuanlai_info);
//        ed_chaichu_3.setFocusable(false);
//        ed_chaichu_3.setFocusableInTouchMode(false);
        ed_chaichu_4 = findViewById(R.id.et_chaichu_dianbiaoxiangxianzai_info);
//        ed_chaichu_4.setFocusable(false);
//        ed_chaichu_4.setFocusableInTouchMode(false);
        btn_scan1=findViewById(R.id.btn_scan_chaichu1);
        btn_scan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator intentIntegrator = new com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator(ChaiChuActivity.this);
                intentIntegrator.setREQUEST_CODE(SCAN_CODE);
                intentIntegrator.setCaptureActivity(CustomScanActivity.class);
                intentIntegrator.setDesiredBarcodeFormats(com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.initiateScan();
            }
        });
        btn_scan2=findViewById(R.id.btn_scan_chaichu2);
        btn_scan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator intentIntegrator = new com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator(ChaiChuActivity.this);
                intentIntegrator.setREQUEST_CODE(SCAN_CODE1);
                intentIntegrator.setCaptureActivity(CustomScanActivity.class);
                intentIntegrator.setDesiredBarcodeFormats(com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.initiateScan();
            }
        });
//        btn_scan3=findViewById(R.id.btn_scan_chaichu3);
//        btn_scan3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator intentIntegrator = new com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator(ChaiChuActivity.this);
//                intentIntegrator.setREQUEST_CODE(SCAN_CODE2);
//                intentIntegrator.setCaptureActivity(CustomScanActivity.class);
//                intentIntegrator.setDesiredBarcodeFormats(com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator.ALL_CODE_TYPES);
//                intentIntegrator.setOrientationLocked(false);
//                intentIntegrator.initiateScan();
//            }
//        });
        btn_scan4=findViewById(R.id.btn_scan_chaichu4);
        btn_scan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator intentIntegrator = new com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator(ChaiChuActivity.this);
                intentIntegrator.setREQUEST_CODE(SCAN_CODE3);
                intentIntegrator.setCaptureActivity(CustomScanActivity.class);
                intentIntegrator.setDesiredBarcodeFormats(com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.initiateScan();
            }
        });
//        btn_chaichu_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ed_chaichu_1.setFocusable(false);
//                ed_chaichu_2.setFocusableInTouchMode(false);
//            }
//        });
//        btn_chaichu_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ed_chaichu_1.setFocusable(false);
//                ed_chaichu_2.setFocusableInTouchMode(false);
//            }
//        });
        btn_chaichu_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    // 进行点击事件后的逻辑操作
                    if(TextUtils.isEmpty(ed_chaichu_1.getText().toString()) || TextUtils.isEmpty(ed_chaichu_2.getText().toString())){
                        Toast.makeText(ChaiChuActivity.this, "封印号码或者电表号码不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
//                    if (!TextUtils.isEmpty(ed_chaichu_4.getText().toString())){
//                        if (TextUtils.isEmpty(ed_chaichu_3.getText().toString()) && TextUtils.isEmpty(ed_chaichu_4.getText().toString())){
//                            Toast.makeText(ChaiChuActivity.this, "如果更换表箱，原来的和现在的电表箱号码都需要扫描", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                    }
                    String str1;
                    str1=ed_chaichu_1.getText().toString();
                    String str3;
                    //str3=ed_chaichu_3.getText().toString();
                    String str4;
                    str4=ed_chaichu_4.getText().toString();
                    fengyinchaichu(str1,ed_chaichu_2.getText().toString(),jiluusername,"",str4);
                }

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SCAN_CODE && data != null) {
            com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentResult result = com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "HaveNoResult",Toast.LENGTH_SHORT).show();
            } else {
                String content = result.getContents();
//                if (content.length()>14){
//                    content=content.substring(content.length()-14,content.length());
//                    ed_chaichu_1.setText(content);
//                }else{
//                    Toast.makeText(this, "请扫描正确的封印号",Toast.LENGTH_SHORT).show();
//                }
                content=jiequshuizi(content);
                ed_chaichu_1.setText(content);
            }
        }
        if(requestCode == SCAN_CODE1 && data != null){
            com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentResult result = com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "HaveNoResult",Toast.LENGTH_SHORT).show();
            } else {
                String content = result.getContents();
                ed_chaichu_2.setText(content);
            }
        }
//        if(requestCode == SCAN_CODE2 && data != null){
//            com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentResult result = com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//            if (result == null) {
//                Toast.makeText(this, "HaveNoResult",Toast.LENGTH_SHORT).show();
//            } else {
//                String content = result.getContents();
//                if (content.length()>14){
//                    content=content.substring(content.length()-14,content.length());
//                    ed_chaichu_3.setText(content);
//                }else{
//                    Toast.makeText(this, "请扫描正确的封印号",Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
        if(requestCode == SCAN_CODE3 && data != null){
            com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentResult result = com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "HaveNoResult",Toast.LENGTH_SHORT).show();
            } else {
                String content = result.getContents();
//                if (content.length()>14){
//                    content=content.substring(content.length()-14,content.length());
//                    ed_chaichu_4.setText(content);
//                }else{
//                    Toast.makeText(this, "请扫描正确的封印号",Toast.LENGTH_SHORT).show();
//                }
                content=jiequshuizi(content);
                ed_chaichu_4.setText(content);
            }
        }
    }
    public void fengyinchaichu(final String fengyinid, final String dianbiaoid, final String chaichurenid,final String dianbiaoxiangyuanlai,final String dianbiaoxiangxianzai){

        new Thread(){
            @Override
            public void run() {
                chaichufanhui = WebService.fengyinchaichu(fengyinid,dianbiaoid,chaichurenid,dianbiaoxiangyuanlai,dianbiaoxiangxianzai);
                System.out.println(chaichufanhui);
                if (chaichufanhui!=null){
                    if (chaichufanhui.equals("11")){

                        Utils.runOnBackgroundThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(50);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                android.os.Message message1 = new android.os.Message();
                                message1.what = aaaa;
                                handler.sendMessage(message1);
                            }
                        });
                    }else if (chaichufanhui.equals("22")){


                            Utils.runOnBackgroundThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(50);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    android.os.Message message1 = new android.os.Message();
                                    message1.what = bbbb;
                                    handler.sendMessage(message1);
                                }
                            });
                    }else if (chaichufanhui.equals("33")){

                            Utils.runOnBackgroundThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(50);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    android.os.Message message1 = new android.os.Message();
                                    message1.what = cccc;
                                    handler.sendMessage(message1);
                                }
                            });

                    }else if (chaichufanhui.equals("44")){

                        Utils.runOnBackgroundThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(50);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                android.os.Message message1 = new android.os.Message();
                                message1.what = dddd;
                                handler.sendMessage(message1);
                            }
                        });

                    }
                    if (chaichufanhui.equals("55")){

                        Utils.runOnBackgroundThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(50);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast toast = Toast.makeText(ChaiChuActivity.this,"封印和表号与安装时不一致", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                });
                            }
                        });

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
                            Toast toast = Toast.makeText(ChaiChuActivity.this,"服务器错误", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                }

            }
        }.start();
    }


}
