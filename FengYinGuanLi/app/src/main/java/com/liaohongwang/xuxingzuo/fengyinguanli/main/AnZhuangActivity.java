package com.liaohongwang.xuxingzuo.fengyinguanli.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.liaohongwang.xuxingzuo.fengyinguanli.R;
import com.liaohongwang.xuxingzuo.fengyinguanli.custom.CustomScanActivity;
import com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator;
import com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentResult;
import com.liaohongwang.xuxingzuo.fengyinguanli.service.WebService;
import com.liaohongwang.xuxingzuo.fengyinguanli.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.banzu;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jiluusername;
import static com.liaohongwang.xuxingzuo.fengyinguanli.utils.Utils.jiequshuizi;

public class AnZhuangActivity extends AppCompatActivity {
    String jiedao;
    public LocationClient mLocationClient;
    String dianbiaoxiang = "no";
    String diliweizhi= "no";
    private ImageView btn_scan1;
    private ImageView btn_scan2;
    private ImageView btn_scan3;
    private ImageView btn_scan4;
    private String anzhuangfanhui;
    private Button btn_anzhuang_1;
    private Button btn_anzhuang_2;
    private Button btn_anzhuang_3;
    private Button btn_anzhuang_4;
    private EditText ed_anzhuang_1;
    private EditText ed_anzhuang_2;
    private EditText ed_anzhuang_3;
    private EditText ed_anzhuang_4;
    public static final int aaaa = 1;
    public static final int bbbb = 2;
    public static final int cccc = 3;

    private static final int SCAN_CODE = 1000;
    private static final int SCAN_CODE1 = 1001;
    private static final int SCAN_CODE2 = 1002;
    private static final int SCAN_CODE0 = 999;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg){
            switch (msg.what){
                case aaaa:
                    ed_anzhuang_1.setText("");
                    ed_anzhuang_2.setText("");
                    ed_anzhuang_3.setText("");
                    ed_anzhuang_4.setText("");
                    Toast.makeText(AnZhuangActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    break;
                case bbbb:
                    Toast.makeText(AnZhuangActivity.this, "提交失败!封印可能已被使用！", Toast.LENGTH_SHORT).show();
                    break;
                case cccc:

                    Toast.makeText(AnZhuangActivity.this, "这个电表未拆除", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SCAN_CODE && data != null) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "HaveNoResult",Toast.LENGTH_SHORT).show();
            } else {
                String content = result.getContents();
//                if (content.length()>14){
//                    content=content.substring(content.length()-14,content.length());
//                    ed_anzhuang_1.setText(content);
//                }else{
//                    Toast.makeText(this, "请扫描正确的封印号",Toast.LENGTH_SHORT).show();
//                }
                content=jiequshuizi(content);
                ed_anzhuang_1.setText(content);
            }
        }
        if(requestCode == SCAN_CODE1 && data != null){
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "HaveNoResult",Toast.LENGTH_SHORT).show();
            } else {
                String content = result.getContents();
                ed_anzhuang_2.setText(content);
            }
        }
        if(requestCode == SCAN_CODE2 && data != null){
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "HaveNoResult",Toast.LENGTH_SHORT).show();
            } else {
                String content = result.getContents();
//                if (content.length()>14){
//                    content=content.substring(content.length()-14,content.length());
//                    ed_anzhuang_3.setText(content);
//                }else{
//                    Toast.makeText(this, "请扫描正确的封印号",Toast.LENGTH_SHORT).show();
//                }
                content=jiequshuizi(content);
                ed_anzhuang_3.setText(content);
            }
        }
        if(requestCode == SCAN_CODE0 && data != null){
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "HaveNoResult",Toast.LENGTH_SHORT).show();
            } else {
                String content = result.getContents();
//                if (content.length()>14){
//                    content=content.substring(content.length()-14,content.length());
//                    ed_anzhuang_4.setText(content);
//                }else{
//                    Toast.makeText(this, "请扫描正确的封印号",Toast.LENGTH_SHORT).show();
//                }
                content=jiequshuizi(content);
                ed_anzhuang_4.setText(content);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_zhuang);
        initView();
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());

        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(AnZhuangActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(AnZhuangActivity.this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(AnZhuangActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(AnZhuangActivity.this, permissions, 1);
        } else {
            requestLocation();
        }

    }
    private void initView(){
        //btn_anzhuang_1 = (Button) findViewById(R.id.btn_anzhuang_fengyin_queding);
        //btn_anzhuang_2 = (Button) findViewById(R.id.btn_anzhuang_dianbiao_queding);
        //btn_anzhuang_3 = (Button) findViewById(R.id.btn_anzhuang_dianbiaoxiang_queding);
        btn_anzhuang_4 = (Button) findViewById(R.id.btn_anzhuang_submit);
        ed_anzhuang_1 = findViewById(R.id.et_fengyin_info);
//        ed_anzhuang_1.setFocusable(false);
//        ed_anzhuang_1.setFocusableInTouchMode(false);
        ed_anzhuang_2 = findViewById(R.id.et_dianbiao_info);
//        ed_anzhuang_2.setFocusable(false);
//        ed_anzhuang_2.setFocusableInTouchMode(false);
        ed_anzhuang_3 = findViewById(R.id.et_dianbiaoxiang_info);
        ed_anzhuang_4 = findViewById(R.id.et_anzhuang_dianbiaoxiangyuanlai_info);
//        ed_anzhuang_3.setFocusable(false);
//        ed_anzhuang_3.setFocusableInTouchMode(false);
        btn_scan1=findViewById(R.id.btn_scan_anzhuang1);
        btn_scan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(AnZhuangActivity.this);
                intentIntegrator.setREQUEST_CODE(SCAN_CODE);
                intentIntegrator.setCaptureActivity(CustomScanActivity.class);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.initiateScan();
            }
        });
        btn_scan2=findViewById(R.id.btn_scan_anzhuang2);
        btn_scan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(AnZhuangActivity.this);
                intentIntegrator.setREQUEST_CODE(SCAN_CODE1);
                intentIntegrator.setCaptureActivity(CustomScanActivity.class);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.initiateScan();
            }
        });
        btn_scan3=findViewById(R.id.btn_scan_anzhuang3);
        btn_scan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(AnZhuangActivity.this);
                intentIntegrator.setREQUEST_CODE(SCAN_CODE2);
                intentIntegrator.setCaptureActivity(CustomScanActivity.class);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.initiateScan();
            }
        });
        btn_scan4=findViewById(R.id.btn_scan_anzhuang4);
        btn_scan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(AnZhuangActivity.this);
                intentIntegrator.setREQUEST_CODE(SCAN_CODE0);
                intentIntegrator.setCaptureActivity(CustomScanActivity.class);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.initiateScan();
            }
        });
//        btn_anzhuang_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ed_anzhuang_1.setFocusable(false);
//                ed_anzhuang_1.setFocusableInTouchMode(false);
//            }
//        });
//        btn_anzhuang_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ed_anzhuang_2.setFocusable(false);
//                ed_anzhuang_2.setFocusableInTouchMode(false);
//            }
//        });
//        btn_anzhuang_3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ed_anzhuang_3.setFocusable(false);
//                ed_anzhuang_3.setFocusableInTouchMode(false);
//            }
//        });
        btn_anzhuang_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    if (!TextUtils.isEmpty(ed_anzhuang_3.getText().toString())) {
                        dianbiaoxiang = ed_anzhuang_3.getText().toString();

                    }
                    if(TextUtils.isEmpty(ed_anzhuang_1.getText().toString()) || TextUtils.isEmpty(ed_anzhuang_2.getText().toString())){
                        Toast.makeText(AnZhuangActivity.this, "封印号码或者电表号码不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        //获取地理位置
                        System.out.println("111222"+diliweizhi);
                        //
                        if(jiedao==null){
                            Toast.makeText(AnZhuangActivity.this, "拒绝访问位置信息或网络不佳，请重新进入安装界面！", Toast.LENGTH_SHORT).show();
                        }else {

                            String str1;
                            str1=ed_anzhuang_1.getText().toString();
                            fengyinanzhuang(str1, ed_anzhuang_2.getText().toString(), dianbiaoxiang, jiluusername, diliweizhi,ed_anzhuang_4.getText().toString());

                        }
                    }
                }


            }
        });

    }

    public void fengyinanzhuang(final String fengyinid, final String dianbiaoid, final String dianbiaoxiangid, final String anzhuangrenid, final String dili, final String yuanlai){

        new Thread(){
            @Override
            public void run() {
                anzhuangfanhui = WebService.fengyinanzhuang(fengyinid,dianbiaoid,dianbiaoxiangid,anzhuangrenid,dili,banzu,yuanlai);
                System.out.println(anzhuangfanhui);
                if (anzhuangfanhui!=null){
                    if (anzhuangfanhui.equals("11")){

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
                    }else {
                        if (anzhuangfanhui.equals("22")){

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
                        }else if(anzhuangfanhui.equals("33")){

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
                        }else if(anzhuangfanhui.equals("44")){

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AnZhuangActivity.this, "封印未领取或领取人不是您！", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else if(anzhuangfanhui.equals("55")){

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ed_anzhuang_1.setText("");
                                    ed_anzhuang_2.setText("");
                                    ed_anzhuang_3.setText("");
                                    ed_anzhuang_4.setText("");
                                    Toast.makeText(AnZhuangActivity.this, "已安装，原来电表箱里的电表都拆除或没有表！", Toast.LENGTH_LONG).show();
                                }
                            });
                        }else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AnZhuangActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
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
                            Toast toast = Toast.makeText(AnZhuangActivity.this,"服务器错误", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                }

            }
        }.start();
    }

    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(500);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }


    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            jiedao=location.getStreet();
            System.out.println("zuoer"+jiedao);
            diliweizhi= location.getProvince()+location.getCity()+location.getDistrict()+location.getStreet();

        }
//
//        @Override
//        public void onConnectHotSpotMessage(String s, int i) {
//
//        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }


}
