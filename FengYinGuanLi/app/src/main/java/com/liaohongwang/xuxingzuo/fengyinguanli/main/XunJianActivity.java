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
import com.liaohongwang.xuxingzuo.fengyinguanli.service.WebService;
import com.liaohongwang.xuxingzuo.fengyinguanli.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jiluusername;
import static com.liaohongwang.xuxingzuo.fengyinguanli.utils.Utils.jiequshuizi;

public class XunJianActivity extends AppCompatActivity {
    public LocationClient mLocationClient;
    String dianbiaoxiang = "dianbiaoxiangno";
    String diliweizhi= "diliweizhino";
    private String xunjianfanhui;
    private Button btn_xunjian_1;
    private Button btn_xunjian_2;
    private Button btn_xunjian_3;
    private Button btn_xunjian_4;
    private EditText ed_xunjian_1;
    private EditText ed_xunjian_2;
    private EditText ed_xunjian_3;
    public static final int aaaa = 1;
    public static final int bbbb = 2;
    public static final int cccc = 3;
    private static final int SCAN_CODE = 1007;
    private static final int SCAN_CODE1 = 1008;
    private static final int SCAN_CODE2 = 1009;
    private ImageView btn_scan1;
    private ImageView btn_scan2;
    private ImageView btn_scan3;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg){
            switch (msg.what){
                case aaaa:
                    ed_xunjian_1.setText("");
                    ed_xunjian_2.setText("");
                    ed_xunjian_3.setText("");
                    Toast.makeText(XunJianActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    break;
                case bbbb:
                    Toast.makeText(XunJianActivity.this, "提交失败!", Toast.LENGTH_SHORT).show();
                    break;
                case cccc:

                    Toast.makeText(XunJianActivity.this, "不能电表箱巡检，可能安装时没扫电表箱", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_xun_jian);
        initView();
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());

        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(XunJianActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(XunJianActivity.this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(XunJianActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(XunJianActivity.this, permissions, 1);
        } else {
            requestLocation();
        }
    }
    private void initView(){
//        btn_xunjian_1 = (Button) findViewById(R.id.btn_xunjian_fengyin_queding);
//        btn_xunjian_2 = (Button) findViewById(R.id.btn_xunjian_dianbiao_queding);
//        btn_xunjian_3 = (Button) findViewById(R.id.btn_xunjian_dianbiaoxiang_queding);
        btn_xunjian_4 = (Button) findViewById(R.id.btn_xunjian_submit);
        ed_xunjian_1 = findViewById(R.id.et_xunjian_fengyin_info);
//        ed_xunjian_1.setFocusable(false);
//        ed_xunjian_1.setFocusableInTouchMode(false);
        ed_xunjian_2 = findViewById(R.id.et_xunjian_dianbiao_info);
//        ed_xunjian_2.setFocusable(false);
//        ed_xunjian_2.setFocusableInTouchMode(false);
        ed_xunjian_3 = findViewById(R.id.et_xunjian_dianbiaoxiang_info);
//        ed_xunjian_3.setFocusable(false);
//        ed_xunjian_3.setFocusableInTouchMode(false);
        btn_scan1=findViewById(R.id.btn_scan_xunjian1);
        btn_scan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator intentIntegrator = new com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator(XunJianActivity.this);
                intentIntegrator.setREQUEST_CODE(SCAN_CODE);
                intentIntegrator.setCaptureActivity(CustomScanActivity.class);
                intentIntegrator.setDesiredBarcodeFormats(com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.initiateScan();
            }
        });
        btn_scan2=findViewById(R.id.btn_scan_xunjian2);
        btn_scan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator intentIntegrator = new com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator(XunJianActivity.this);
                intentIntegrator.setREQUEST_CODE(SCAN_CODE1);
                intentIntegrator.setCaptureActivity(CustomScanActivity.class);
                intentIntegrator.setDesiredBarcodeFormats(com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.initiateScan();
            }
        });
        btn_scan3=findViewById(R.id.btn_scan_xunjian3);
        btn_scan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator intentIntegrator = new com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator(XunJianActivity.this);
                intentIntegrator.setREQUEST_CODE(SCAN_CODE2);
                intentIntegrator.setCaptureActivity(CustomScanActivity.class);
                intentIntegrator.setDesiredBarcodeFormats(com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.initiateScan();
            }
        });

//        btn_xunjian_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ed_xunjian_1.setFocusable(false);
//                ed_xunjian_1.setFocusableInTouchMode(false);
//            }
//        });
//        btn_xunjian_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ed_xunjian_2.setFocusable(false);
//                ed_xunjian_2.setFocusableInTouchMode(false);
//            }
//        });
//        btn_xunjian_3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ed_xunjian_3.setFocusable(false);
//                ed_xunjian_3.setFocusableInTouchMode(false);
//            }
//        });
        btn_xunjian_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    String str1;
                    str1=ed_xunjian_1.getText().toString();

                    if (!TextUtils.isEmpty(ed_xunjian_1.getText().toString())&&!TextUtils.isEmpty(ed_xunjian_2.getText().toString())){
                        if (!TextUtils.isEmpty(ed_xunjian_3.getText().toString())){
                            dianbiaoxiang = ed_xunjian_3.getText().toString();
                            xunjian(str1,ed_xunjian_2.getText().toString(),dianbiaoxiang,jiluusername,diliweizhi);
                        }else{
                            dianbiaoxiang="dianbiaoxiang is no";
                            xunjian(str1,ed_xunjian_2.getText().toString(),dianbiaoxiang,jiluusername,diliweizhi);
                        }

                    }else if (!TextUtils.isEmpty(ed_xunjian_1.getText().toString())){
//                        if (!(ed_xunjian_1.getText().toString().length() == 14)) {
//                            Toast.makeText(XunJianActivity.this, "封印号不是14位", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
                        //封印巡检
                        if (!TextUtils.isEmpty(ed_xunjian_3.getText().toString())){
                            dianbiaoxiang = ed_xunjian_3.getText().toString();
                            xunjian(str1,"dianbiao is no",dianbiaoxiang,jiluusername,diliweizhi);
                        }else{
                            xunjian(str1,"dianbiao is no","dianbiaoxiang is no",jiluusername,diliweizhi);
                        }


                    }else if (!TextUtils.isEmpty(ed_xunjian_2.getText().toString())){
                        //电表巡检

                        if (!TextUtils.isEmpty(ed_xunjian_3.getText().toString())){
                            dianbiaoxiang = ed_xunjian_3.getText().toString();
                            xunjian("fengyin is no",ed_xunjian_2.getText().toString(),dianbiaoxiang,jiluusername,diliweizhi);
                        }else {
                            xunjian("fengyin is no",ed_xunjian_2.getText().toString(),"dianbiaoxiang is no",jiluusername,diliweizhi);
                        }


                    }else if (!TextUtils.isEmpty(ed_xunjian_3.getText().toString())){
                        dianbiaoxiang = ed_xunjian_3.getText().toString();
                        xunjian("fengyin is no","dianbiao is no",dianbiaoxiang,jiluusername,diliweizhi);
                    }else{
                        Toast.makeText(XunJianActivity.this, "当前封印号码,电表号码,电表箱号码都为空，至少有其一", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

            }
        });

    }
    public void xunjian(final String fengyinid, final String dianbiaoid, final String dianbiaoxiangid, final String xunjianrenrenid, final String dili){

        new Thread(){
            @Override
            public void run() {
                xunjianfanhui = WebService.xunjian(fengyinid,dianbiaoid,dianbiaoxiangid,xunjianrenrenid,dili);
                System.out.println(xunjianfanhui);
                if (xunjianfanhui!=null){
                    if (xunjianfanhui.equals("11")){

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
                        if (xunjianfanhui.equals("22")){

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
                        }else if(xunjianfanhui.equals("33")){

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
                        }else if(xunjianfanhui.equals("44")){

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                                    Toast toast = Toast.makeText(XunJianActivity.this,"不能巡检,未安装！", Toast.LENGTH_SHORT);
                                    toast.show();
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
                            Toast toast = Toast.makeText(XunJianActivity.this,"服务器错误", Toast.LENGTH_SHORT);
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


            diliweizhi= location.getProvince()+location.getCity()+location.getDistrict()+location.getStreet();
            System.out.println("11112222"+diliweizhi);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    positionText.setText(currentPosition);
//                }
//            });
//        }
//
//        @Override
//        public void onConnectHotSpotMessage(String s, int i) {
//
       }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
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
//                    ed_xunjian_1.setText(content);
//                }else{
//                    Toast.makeText(this, "请扫描正确的封印号",Toast.LENGTH_SHORT).show();
//                }
                content=jiequshuizi(content);
                ed_xunjian_1.setText(content);
            }
        }
        if(requestCode == SCAN_CODE1 && data != null){
            com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentResult result = com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "HaveNoResult",Toast.LENGTH_SHORT).show();
            } else {
                String content = result.getContents();
                ed_xunjian_2.setText(content);
            }
        }
        if (requestCode == SCAN_CODE2 && data != null) {
            com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentResult result = com.liaohongwang.xuxingzuo.fengyinguanli.custom.IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "HaveNoResult",Toast.LENGTH_SHORT).show();
            } else {
                String content = result.getContents();
//                if (content.length()>14){
//                    content=content.substring(content.length()-14,content.length());
//                    ed_xunjian_3.setText(content);
//                }else{
//                    Toast.makeText(this, "请扫描正确的封印号",Toast.LENGTH_SHORT).show();
//                }
                content=jiequshuizi(content);
                ed_xunjian_3.setText(content);
            }
        }
    }
}
