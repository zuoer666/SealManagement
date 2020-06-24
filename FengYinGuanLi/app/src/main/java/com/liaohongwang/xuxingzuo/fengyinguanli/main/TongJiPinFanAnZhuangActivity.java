package com.liaohongwang.xuxingzuo.fengyinguanli.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liaohongwang.xuxingzuo.fengyinguanli.R;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.PinFanAnZhuang;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.PinFanAnZhuangAdapter;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.TongJiPinFanAnzhuangXiangXi;
import com.liaohongwang.xuxingzuo.fengyinguanli.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.IP;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.banzu;

public class TongJiPinFanAnZhuangActivity extends AppCompatActivity {
    PinFanAnZhuangAdapter adapter;
    private ListView listView;
    private TextView text_tip;
    private List<PinFanAnZhuang> fuJinList = new ArrayList<>();
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong_ji_pin_fan_an_zhuang);
        text_tip = findViewById(R.id.text_tip_pinfananzhuang);
        btn=findViewById(R.id.btn_tongji_pinfananzhuang_submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    // 进行点击事件后的逻辑操作
                    fuJinList.clear();
                    sendRequestWithOkHttp();
                }

            }
        });
    }
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://" + IP + "/fengyinguanli/PinFanAnZhuang?banzu="+banzu)
                            .build();
                    Response response = client.newCall(request).execute();
                    String reData = response.body().string();
                    parseJSOMWithGSON(reData);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void sendRequestWithOkHttp1(String a){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://" + IP + "/fengyinguanli/BiaoHaoChaXun?biaohao="+a)
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
    private void parseJSOMWithGSON(String jsonData) {
        String dianbiaohao;
        String anzhungshijian;
        String dili;
        String dianbiaoxiang;
        String fengyinhao;
        //List<DingDan> mLists= new ArrayList<DingDan>();
        Gson gson = new Gson();
        List<PinFanAnZhuang> fuJinList1 = gson.fromJson(jsonData, new TypeToken<List<PinFanAnZhuang>>() {
        }.getType());
        for (PinFanAnZhuang fuJin : fuJinList1) {
            dianbiaohao = "电表号 ：" + fuJin.getDianbiaohao();
//            if (fuJin.getAnzhungshijian()!=null){
//                anzhungshijian = "安装时间 ："+fuJin.getAnzhungshijian().substring(0,12)+" 时";
//            }else{
//                anzhungshijian = "安装时间 ："+fuJin.getAnzhungshijian();
//            }
//            dili = fuJin.getDili();
//            dianbiaoxiang= "电表箱 ："+fuJin.getDianbiaoxiang();
//            fengyinhao = "封印号："+fuJin.getFengyinhao();
           fuJinList.add(new PinFanAnZhuang(dianbiaohao,"","","",""));
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new PinFanAnZhuangAdapter(TongJiPinFanAnZhuangActivity.this,R.layout.item_tongji_pinfananzhuang,fuJinList);
                listView = (ListView) findViewById(R.id.ls_tongji_pinfananzhuang);
                listView.setAdapter(adapter);
                adapter.setJieDanListener(new PinFanAnZhuangAdapter.JieDanListener() {
                    @Override
                    public void onJieDanClick(final String a) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                if (Utils.isFastClick()) {
                                    sendRequestWithOkHttp1(a);
                                }

                            }
                        }).start();
                    }

                });
            }
        });
    }
    private void parseJSOMWithGSON1(String jsonData){
        String anzhungren;
        String anzhungshijian;
        String chaichuren;
        String chaichushijian;
        String dili;
        String dianbiaoxiang;
        String fengyinhao;
        //List<DingDan> mLists= new ArrayList<DingDan>();
        Gson gson = new Gson();
        List<TongJiPinFanAnzhuangXiangXi> fuJinList1 = gson.fromJson(jsonData,new TypeToken<List<TongJiPinFanAnzhuangXiangXi>>(){}.getType());
        for (TongJiPinFanAnzhuangXiangXi fuJin :fuJinList1){
            anzhungren = "安装人 ："+fuJin.getAnzhuangren();
            if (fuJin.getAnzhuangshijian()!=null&&!fuJin.getAnzhuangshijian().equals("")){
                anzhungshijian = "安装时间 ："+fuJin.getAnzhuangshijian().substring(0,fuJin.getAnzhuangshijian().length()-3);
            }else{
                anzhungshijian = "安装时间 ："+fuJin.getAnzhuangshijian();
            }

            chaichuren= "拆除人 ："+fuJin.getChaichuren();
            if (!fuJin.getChaichushijian().equals("")){
                if (fuJin.getChaichushijian().equals("8888-08-08 08:08:08" )){
                    chaichushijian = "no";
                }else{
                    chaichushijian = "拆除时间 ："+fuJin.getChaichushijian().substring(0,fuJin.getChaichushijian().length()-3);
                }

            }else{
                chaichushijian = "拆除时间 ："+fuJin.getChaichushijian();
            }
            dili = fuJin.getDili();
            dianbiaoxiang= "电表箱 ："+fuJin.getDianbiaoxiang();
            fengyinhao = "封印号："+fuJin.getFengyinhao();
            TongJiPianFanAnZhuangXiangXiActivity.fuJinList11.add(new TongJiPinFanAnzhuangXiangXi(anzhungren,anzhungshijian,chaichuren,chaichushijian,dili,dianbiaoxiang,fengyinhao));
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(TongJiPinFanAnZhuangActivity.this, TongJiPianFanAnZhuangXiangXiActivity.class);
                startActivity(intent);
            }
        });








    }
}
