package com.liaohongwang.xuxingzuo.fengyinguanli.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liaohongwang.xuxingzuo.fengyinguanli.R;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.CaoZuoYuanAnZhuang;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.CaoZuoYuanBanZu;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.CaoZuoYuanBanZuAdapter;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.CaoZuoYuanChaiChu;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.CaoZuoYuanXunJian;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.LingYong;
import com.liaohongwang.xuxingzuo.fengyinguanli.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.IP;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.banzuxuanze;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jieguo;
import static com.liaohongwang.xuxingzuo.fengyinguanli.main.TongJiCaoZuoYuanActivity.timejieshu;
import static com.liaohongwang.xuxingzuo.fengyinguanli.main.TongJiCaoZuoYuanActivity.timekaishi;

public class TongJiCaoZuoYuanBanZuActivity extends AppCompatActivity {
    public static CaoZuoYuanBanZuAdapter adapter1;
    String caozuoyuan;
    public static ListView listView1;
    public static List<CaoZuoYuanBanZu> fuJinList11 = new ArrayList<>();
    TextView top_tv_jieguo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong_ji_cao_zuo_yuan_ban_zu);
        top_tv_jieguo = findViewById(R.id.top_caozuoyian_banzu);
        if (banzuxuanze.equals("1")){
            //安装
            top_tv_jieguo.setText("安装查询");
        }else if (banzuxuanze.equals("2")){
            //拆除
            top_tv_jieguo.setText("拆除查询");
        }else if (banzuxuanze.equals("3")){
            //巡检
            top_tv_jieguo.setText("巡检查询");
        }else if (banzuxuanze.equals("4")){
            //领用
            top_tv_jieguo.setText("领用查询");
        }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TongJiCaoZuoYuanBanZuActivity.adapter1 = new CaoZuoYuanBanZuAdapter(TongJiCaoZuoYuanBanZuActivity.this,R.layout.item_tongji_caozuoyuan_banzu,TongJiCaoZuoYuanBanZuActivity.fuJinList11);
                    TongJiCaoZuoYuanBanZuActivity.listView1 = (ListView) findViewById(R.id.ls_tongji_banzu_caozuoyuanchaxun_banzu);
                    TongJiCaoZuoYuanBanZuActivity.listView1.setAdapter(TongJiCaoZuoYuanBanZuActivity.adapter1);
                    adapter1.setJieDanListener(new CaoZuoYuanBanZuAdapter.JieDanListener() {
                        @Override
                        public void onJieDanClick(final String a) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    if (Utils.isFastClick()) {
                                        // 进行点击事件后的逻辑操作
                                        caozuoyuan=a;
                                        System.out.println(caozuoyuan+"666666");
                                        if (banzuxuanze.equals("1")){
                                            //安装
                                            sendRequestWithOkHttp1();
                                        }else if (banzuxuanze.equals("2")){
                                            //拆除
                                            sendRequestWithOkHttp2();
                                        }else if (banzuxuanze.equals("3")){
                                            //巡检
                                            sendRequestWithOkHttp3();
                                        }else if (banzuxuanze.equals("4")){
                                            //领用
                                            sendRequestWithOkHttp4();
                                        }
                                    }

                                }
                            }).start();
                        }

                    });
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
                    parseJSOMWithGSON4(reData);
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

                Intent intent = new Intent(TongJiCaoZuoYuanBanZuActivity.this, CaoZuoYuanJieGuoActivity.class);
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
            dianbiaoxiang= "电表箱 ："+fuJin.getDianbiaoxiang();
            dili = fuJin.getDili();
            fengyinhao= "封印号 ："+fuJin.getFengyinhao();
            CaoZuoYuanJieGuoActivity.fuJinList22.add(new CaoZuoYuanChaiChu(dianbiaohao,chaichushijian,dili,dianbiaoxiang,fengyinhao));
        }
        jieguo=2;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(TongJiCaoZuoYuanBanZuActivity.this, CaoZuoYuanJieGuoActivity.class);
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
                dianbiaoxiang= "电表箱 ：no";
            }else {
                dianbiaoxiang= "电表箱 ："+fuJin.getDianbiaoxiang();
            }
            fengyinhao= "封印号 ："+fuJin.getFengyinhao();
            CaoZuoYuanJieGuoActivity.fuJinList33.add(new CaoZuoYuanXunJian(dianbiaohao,xunjianshijian,dili,dianbiaoxiang,fengyinhao));
        }
        jieguo=3;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(TongJiCaoZuoYuanBanZuActivity.this, CaoZuoYuanJieGuoActivity.class);
                startActivity(intent);
            }
        });

    }
    private void parseJSOMWithGSON4(String jsonData){
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

                Intent intent = new Intent(TongJiCaoZuoYuanBanZuActivity.this, CaoZuoYuanJieGuoActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStop() {
        super.onStop();
        fuJinList11.clear();
    }
}
