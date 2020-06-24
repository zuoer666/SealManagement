package com.liaohongwang.xuxingzuo.fengyinguanli.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liaohongwang.xuxingzuo.fengyinguanli.R;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.BanZu;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.CaoZuoYuanAnZhuang;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.CaoZuoYuanBanZu;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.CaoZuoYuanChaiChu;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.CaoZuoYuanXunJian;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.LingYong;
import com.liaohongwang.xuxingzuo.fengyinguanli.utils.Utils;
import com.liaohongwang.xuxingzuo.fengyinguanli.widget.CustomDatePicker;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.IP;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.banzu;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.banzuxuanze;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jieguo;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jiluusername;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.name;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.quanxian;
import static com.liaohongwang.xuxingzuo.fengyinguanli.widget.CustomDatePicker.ResultHandler;

public class TongJiCaoZuoYuanActivity extends AppCompatActivity implements View.OnClickListener {
    private String spbanzu;
    private EditText ed_tongji_caozuoyuan;
    private String caozuoyuan;
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
//    private Spinner spDown;
//    private List<String> list;
//    private ArrayAdapter<String> adapter;
    private RelativeLayout selectDate, selectTime;
    private TextView currentDate, currentTime,tvxuanzebanzu,tv_queding;
    public static String timekaishi="";
    public static String timejieshu="";
    private CustomDatePicker customDatePicker1, customDatePicker2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong_ji_cao_zuo_yuan);

        ed_tongji_caozuoyuan=findViewById(R.id.ed_tongji_caozuoyuan_info);
        selectTime = (RelativeLayout) findViewById(R.id.selectTime);
        selectTime.setOnClickListener(this);
        selectDate = (RelativeLayout) findViewById(R.id.selectDate);
        selectDate.setOnClickListener(this);

        currentDate = (TextView) findViewById(R.id.currentDate);
        currentTime = (TextView) findViewById(R.id.currentTime);
        tvxuanzebanzu = findViewById(R.id.tv_tongji_lingyong_caozuoyuan_xuanzebanzu);
        btn1=findViewById(R.id.btn_tongji_caozuoyuan_submit_anzhuang);
        btn2=findViewById(R.id.btn_tongji_caozuoyuan_submit_chaichu);
        btn3=findViewById(R.id.btn_tongji_caozuoyuan_submit_xunjian);
        btn4=findViewById(R.id.btn_tongji_caozuoyuan_submit_lingyong);

        spinner = (Spinner) findViewById(R.id.caozuoyuan_spinner);
        initCaoZuoYuan();
        initDatePicker();
        if (quanxian.equals("1")){
            spinner.setVisibility(View.GONE);
            tvxuanzebanzu.setVisibility(View.GONE);
            ed_tongji_caozuoyuan.setText(name);
            ed_tongji_caozuoyuan.setFocusable(false);
            ed_tongji_caozuoyuan.setFocusableInTouchMode(false);
        }
        if (quanxian.equals("2")){
            ed_tongji_caozuoyuan.setVisibility(View.GONE);
            data_list = new ArrayList<String>();
            data_list.add(banzu);
            arr_adapter= new ArrayAdapter<String>(TongJiCaoZuoYuanActivity.this, android.R.layout.simple_spinner_item, data_list);
            //设置样式
            arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //加载适配器
            spinner.setAdapter(arr_adapter);
            spinner.setFocusable(false);
            spinner.setFocusableInTouchMode(false);


        }
        if (quanxian.equals("3")){
            ed_tongji_caozuoyuan.setVisibility(View.GONE);
            sendRequestWithOkHttp4();
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    // 进行点击事件后的逻辑操作
                    timekaishi=currentDate.getText().toString();
                    timejieshu=currentTime.getText().toString();
                    Log.i("111111",timekaishi+timejieshu);
                    banzuxuanze="1";
                    if(quanxian.equals("1")){
                        caozuoyuan=jiluusername;
                        sendRequestWithOkHttp1();
                    }else if (quanxian.equals("2")){
                        spbanzu=spinner.getSelectedItem().toString();
                        sendRequestWithOkHttp5();
                    }else if (quanxian.equals("3")){
                        spbanzu=spinner.getSelectedItem().toString();

                        sendRequestWithOkHttp5();
                    }

                }

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    // 进行点击事件后的逻辑操作
                    timekaishi=currentDate.getText().toString();
                    timejieshu=currentTime.getText().toString();
                    banzuxuanze="2";
                    if(quanxian.equals("1")){
                        caozuoyuan=jiluusername;
                        sendRequestWithOkHttp2();
                    }else if (quanxian.equals("2")){
                        spbanzu=spinner.getSelectedItem().toString();
                        sendRequestWithOkHttp5();
                    }else if (quanxian.equals("3")){
                        spbanzu=spinner.getSelectedItem().toString();
                        sendRequestWithOkHttp5();
                    }
                }


            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    // 进行点击事件后的逻辑操作
                    timekaishi=currentDate.getText().toString();
                    timejieshu=currentTime.getText().toString();
                    banzuxuanze="3";
                    if(quanxian.equals("1")){
                        caozuoyuan=jiluusername;
                        sendRequestWithOkHttp3();
                    }else if (quanxian.equals("2")){
                        spbanzu=spinner.getSelectedItem().toString();
                        sendRequestWithOkHttp5();
                    }else if (quanxian.equals("3")){
                        spbanzu=spinner.getSelectedItem().toString();
                        sendRequestWithOkHttp5();
                    }
                }


            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    // 进行点击事件后的逻辑操作
                    timekaishi=currentDate.getText().toString();
                    timejieshu=currentTime.getText().toString();
                    banzuxuanze="4";
                    if(quanxian.equals("1")){
                        caozuoyuan=jiluusername;
                        sendRequestWithOkHttp6();
                    }else if (quanxian.equals("2")){
                        spbanzu=spinner.getSelectedItem().toString();
                        sendRequestWithOkHttp5();
                    }else if (quanxian.equals("3")){
                        spbanzu=spinner.getSelectedItem().toString();
                        sendRequestWithOkHttp5();
                    }
                }


            }
        });
    }
    private void parseJSOMWithGSON4(String jsonData){
        data_list = new ArrayList<String>();

        String banzu;
        Gson gson = new Gson();
        List<BanZu> fuJinList1 = gson.fromJson(jsonData,new TypeToken<List<BanZu>>(){}.getType());
        for (BanZu fuJin :fuJinList1){
            banzu = fuJin.getBanzu();
            data_list.add(banzu);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //适配器
                arr_adapter= new ArrayAdapter<String>(TongJiCaoZuoYuanActivity.this, android.R.layout.simple_spinner_item, data_list);
                //设置样式
                arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //加载适配器
                spinner.setAdapter(arr_adapter);
                //initConnection();
            }
        });


    }
    private void sendRequestWithOkHttp1(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    OkHttpClient client = new OkHttpClient();
                    RequestBody formBody = new FormBody.Builder()
//                .add("jsonData", "{\"data\":\"121\",\"data1\":\"2232\"}")
                            .add("caozuoyuan", caozuoyuan)
                            .add("timekaishi", timekaishi)
                            .add("timejieshu", timejieshu)
                            .build();

                    Request request = new Request.Builder()
                            .post(formBody)
                            .url("http://" + IP + "/fengyinguanli/CaoZuoYuanAnZhuang")
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
    private void sendRequestWithOkHttp2(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody formBody = new FormBody.Builder()
//                .add("jsonData", "{\"data\":\"121\",\"data1\":\"2232\"}")
                            .add("caozuoyuan", caozuoyuan)
                            .add("timekaishi", timekaishi)
                            .add("timejieshu", timejieshu)
                            .build();

                    Request request = new Request.Builder()
                            .post(formBody)
                            .url("http://" + IP + "/fengyinguanli/CaoZuoYuanChaiChu")
                            .build();
                    Response response = client.newCall(request).execute();
                    String reData = response.body().string();
                    parseJSOMWithGSON2(reData);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void sendRequestWithOkHttp3(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    RequestBody formBody = new FormBody.Builder()
//                .add("jsonData", "{\"data\":\"121\",\"data1\":\"2232\"}")
                            .add("caozuoyuan", caozuoyuan)
                            .add("timekaishi", timekaishi)
                            .add("timejieshu", timejieshu)
                            .build();
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .post(formBody)
                            .url("http://" + IP + "/fengyinguanli/CaoZuoYuanXunJian")
                            .build();
                    Response response = client.newCall(request).execute();
                    String reData = response.body().string();
                    parseJSOMWithGSON3(reData);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void sendRequestWithOkHttp4(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://" + IP + "/fengyinguanli/FanHuiBanZu")
                            .build();
                    Response response = client.newCall(request).execute();
                    String reData = response.body().string();
                    parseJSOMWithGSON4(reData);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void sendRequestWithOkHttp5(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    RequestBody formBody = new FormBody.Builder()
//                .add("jsonData", "{\"data\":\"121\",\"data1\":\"2232\"}")
                            .add("banzu",spbanzu)
                            .add("banzuxuanze",banzuxuanze)
                            .add("timekaishi", timekaishi)
                            .add("timejieshu", timejieshu)
                            .build();
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .post(formBody)
                            .url("http://" + IP + "/fengyinguanli/CaoZuoYuanBanZu")
                            .build();
                    Response response = client.newCall(request).execute();
                    String reData = response.body().string();
                    parseJSOMWithGSON5(reData);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void sendRequestWithOkHttp6(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    RequestBody formBody = new FormBody.Builder()
//                .add("jsonData", "{\"data\":\"121\",\"data1\":\"2232\"}")
                            .add("caozuoyuan", caozuoyuan)
                            .add("timekaishi", timekaishi)
                            .add("timejieshu", timejieshu)
                            .build();
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .post(formBody)
                            .url("http://" + IP + "/fengyinguanli/CaoZuoYuanLingYong")
                            .build();
                    Response response = client.newCall(request).execute();
                    String reData = response.body().string();
                    parseJSOMWithGSON6(reData);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void parseJSOMWithGSON1(String jsonData){
        String dianbiaohao;
        String anzhungshijian;
        String dili;
        String dianbiaoxiang;
        String fengyinhao;
        //List<DingDan> mLists= new ArrayList<DingDan>();
        Gson gson = new Gson();
        final List<CaoZuoYuanAnZhuang> fuJinList1 = gson.fromJson(jsonData,new TypeToken<List<CaoZuoYuanAnZhuang>>(){}.getType());
        for (CaoZuoYuanAnZhuang fuJin :fuJinList1){
            dianbiaohao = "电表号："+fuJin.getDianbiaohao();
            if (fuJin.getAnzhungshijian()!=null&&!fuJin.getAnzhungshijian().equals("")){
                anzhungshijian = "安装时间 ："+fuJin.getAnzhungshijian().substring(0,fuJin.getAnzhungshijian().length()-3);
            }else{
                anzhungshijian = "安装时间 ："+fuJin.getAnzhungshijian();
            }
            dili = fuJin.getDili();
            dianbiaoxiang= "电表箱 ："+fuJin.getDianbiaoxiang();
            fengyinhao= "封印号 ："+fuJin.getFengyinhao();
            CaoZuoYuanJieGuoActivity.fuJinList11.add(new CaoZuoYuanAnZhuang(dianbiaohao,anzhungshijian,dili,dianbiaoxiang,fengyinhao));
        }
        jieguo=1;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(TongJiCaoZuoYuanActivity.this, CaoZuoYuanJieGuoActivity.class);
                startActivity(intent);
            }
        });


    }
    private void parseJSOMWithGSON2(String jsonData){
        String dianbiaohao;
        String chaichushijian;
        String dili;
        String dianbiaoxiang;
        String fengyinhao;
        //List<DingDan> mLists= new ArrayList<DingDan>();
        Gson gson = new Gson();
        List<CaoZuoYuanChaiChu> fuJinList1 = gson.fromJson(jsonData,new TypeToken<List<CaoZuoYuanChaiChu>>(){}.getType());
        for (CaoZuoYuanChaiChu fuJin :fuJinList1){
            dianbiaohao = "电表号："+fuJin.getDianbiaohao();
            if (fuJin.getChaichushijian()!=null&&!fuJin.getChaichushijian().equals("")){
                chaichushijian = "拆除时间 ："+fuJin.getChaichushijian().substring(0,fuJin.getChaichushijian().length()-3);
            }else{
                chaichushijian = "拆除时间 ："+fuJin.getChaichushijian();
            }
            dili = fuJin.getDili();
            dianbiaoxiang= "电表箱 ："+fuJin.getDianbiaoxiang();
            fengyinhao= "封印号 ："+fuJin.getFengyinhao();
            CaoZuoYuanJieGuoActivity.fuJinList22.add(new CaoZuoYuanChaiChu(dianbiaohao,chaichushijian,dili,dianbiaoxiang,fengyinhao));
        }
        jieguo=2;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(TongJiCaoZuoYuanActivity.this, CaoZuoYuanJieGuoActivity.class);
                startActivity(intent);
            }
        });


    }
    private void parseJSOMWithGSON3(String jsonData){
        String dianbiaohao;
        String xunjianshijian;
        String dili;
        String dianbiaoxiang;
        String fengyinhao;
        //List<DingDan> mLists= new ArrayList<DingDan>();
        Gson gson = new Gson();
        List<CaoZuoYuanXunJian> fuJinList1 = gson.fromJson(jsonData,new TypeToken<List<CaoZuoYuanXunJian>>(){}.getType());
        for (CaoZuoYuanXunJian fuJin :fuJinList1){
            dianbiaohao = "电表号："+fuJin.getDianbiaohao();
            if (fuJin.getXunjianshijian()!=null&&!fuJin.getXunjianshijian().equals("")){
                xunjianshijian = "巡检时间 ："+fuJin.getXunjianshijian().substring(0,fuJin.getXunjianshijian().length()-3);
            }else{
                xunjianshijian = "巡检时间 ："+fuJin.getXunjianshijian();
            }
            dili = fuJin.getDili();
            if (fuJin.getDianbiaoxiang().equals("dianbiaoxiang is no")){
                dianbiaoxiang="电表箱 ：no";
            }else{
                dianbiaoxiang= "电表箱 ："+fuJin.getDianbiaoxiang();
            }
            fengyinhao= "封印号 ："+fuJin.getFengyinhao();
            CaoZuoYuanJieGuoActivity.fuJinList33.add(new CaoZuoYuanXunJian(dianbiaohao,xunjianshijian,dili,dianbiaoxiang,fengyinhao));
        }
        jieguo=3;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(TongJiCaoZuoYuanActivity.this, CaoZuoYuanJieGuoActivity.class);
                startActivity(intent);
            }
        });

    }
    private void parseJSOMWithGSON6(String jsonData){
        String lingyongren;
        String lingyongrenshijian;
        String fengyinhao;
        //List<DingDan> mLists= new ArrayList<DingDan>();
        Gson gson = new Gson();
        List<LingYong> fuJinList1 = gson.fromJson(jsonData,new TypeToken<List<LingYong>>(){}.getType());
        for (LingYong fuJin :fuJinList1){
            lingyongren = "领用人 ："+fuJin.getLingyongren();
            if (fuJin.getLingyongrenshijian()!=null&&!fuJin.getLingyongrenshijian().equals("")){
                lingyongrenshijian = "领用时间 ："+fuJin.getLingyongrenshijian().substring(0,fuJin.getLingyongrenshijian().length()-3);
            }else{
                lingyongrenshijian = "领用时间 ："+fuJin.getLingyongrenshijian();
            }
            fengyinhao = "封印号："+fuJin.getFengyinhao();
            CaoZuoYuanJieGuoActivity.fuJinList44.add(new LingYong(lingyongren,lingyongrenshijian,fengyinhao));
        }
        jieguo=4;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(TongJiCaoZuoYuanActivity.this, CaoZuoYuanJieGuoActivity.class);
                startActivity(intent);
            }
        });

    }
    private void parseJSOMWithGSON5(String jsonData){
        String zhanghao;
        String name;
        String mun;

        //List<DingDan> mLists= new ArrayList<DingDan>();
        Gson gson = new Gson();
        List<CaoZuoYuanBanZu> fuJinList1 = gson.fromJson(jsonData,new TypeToken<List<CaoZuoYuanBanZu>>(){}.getType());
        for (CaoZuoYuanBanZu fuJin :fuJinList1){
            zhanghao =fuJin.getZhanghao();
            name = "姓名 ："+fuJin.getName();
            mun = "记录条数 : "+fuJin.getMun();
            TongJiCaoZuoYuanBanZuActivity.fuJinList11.add(new CaoZuoYuanBanZu(zhanghao,name,mun));
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(TongJiCaoZuoYuanActivity.this, TongJiCaoZuoYuanBanZuActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initCaoZuoYuan(){

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectDate:
                // 日期格式为yyyy-MM-dd


                customDatePicker1.show(currentDate.getText().toString());

                //Toast.makeText(this, currentDate.getText().toString(), Toast.LENGTH_SHORT).show();
                //System.out.print(currentTime.getText().toString()+"111111");
                break;

            case R.id.selectTime:
                // 日期格式为yyyy-MM-dd HH:mm

                customDatePicker2.show(currentTime.getText().toString());

                //Toast.makeText(this, currentTime.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_select:
                Toast.makeText(this, currentDate.getText().toString(), Toast.LENGTH_SHORT).show();

                break;
        }
    }
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);

        String kaishi = sdf.format(new Date().getTime()-3 * 24 * 3600 * 1000);
        String now = sdf.format(new Date());
        currentDate.setText(kaishi.split(" ")[0]);
        currentTime.setText(now);

        customDatePicker1 = new CustomDatePicker(this, new ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                currentDate.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker1.showSpecificTime(false); // 不显示时和分
        customDatePicker1.setIsLoop(false); // 不允许循环滚动

        customDatePicker2 = new CustomDatePicker(this, new ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                currentTime.setText(time);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker2.showSpecificTime(true); // 显示时和分
        customDatePicker2.setIsLoop(true); // 允许循环滚动

    }

}
