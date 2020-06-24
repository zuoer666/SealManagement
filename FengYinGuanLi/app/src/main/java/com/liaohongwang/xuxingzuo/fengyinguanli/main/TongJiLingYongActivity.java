package com.liaohongwang.xuxingzuo.fengyinguanli.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liaohongwang.xuxingzuo.fengyinguanli.R;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.BanZu;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.LingYong;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.LingYongAdapter;
import com.liaohongwang.xuxingzuo.fengyinguanli.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.IP;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.banzu;

public class TongJiLingYongActivity extends AppCompatActivity {
    private Spinner spinner;
    private List<String> data_list;
    LingYongAdapter adapter;
    private ListView listView;
    private TextView text_tip;
    String caozuoyuan;
    private ArrayAdapter<String> arr_adapter;
    private List<LingYong> fuJinList = new ArrayList<>();
    Button btn;
    Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong_ji_ling_yong);
        text_tip = findViewById(R.id.text_tip_lingyong);
        btn=findViewById(R.id.btn_tongji_lingyong_submit);
        btn2=findViewById(R.id.btn_tongji_gerenlingyong_submit);
        spinner = (Spinner) findViewById(R.id.lingyong_spinner);
        sendRequestWithOkHttp4();
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
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    // 进行点击事件后的逻辑操作
                    fuJinList.clear();
                    caozuoyuan=spinner.getSelectedItem().toString();
                    sendRequestWithOkHttp2();
                }

            }
        });
    }
    private void sendRequestWithOkHttp4(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://" + IP + "/fengyinguanli/FanHuiCaoZuoYuan?banzu="+banzu)
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
                arr_adapter= new ArrayAdapter<String>(TongJiLingYongActivity.this, android.R.layout.simple_spinner_item, data_list);
                //设置样式
                arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //加载适配器
                spinner.setAdapter(arr_adapter);
                //initConnection();
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
                            .url("http://" + IP + "/fengyinguanli/TongJiLingYong?banzu="+banzu+"&caozuoyuan=no")
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
    private void sendRequestWithOkHttp2(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://" + IP + "/fengyinguanli/TongJiLingYong?banzu=no&caozuoyuan="+caozuoyuan)
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
    private void parseJSOMWithGSON(String jsonData){
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
            fuJinList.add(new LingYong(lingyongren,lingyongrenshijian,fengyinhao));
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new LingYongAdapter(TongJiLingYongActivity.this,R.layout.item_tongji_lingyong,fuJinList);
                listView = (ListView) findViewById(R.id.ls_tongji_lingyong);
                listView.setAdapter(adapter);

            }
        });
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text_tip.setVisibility(View.VISIBLE);

            }
        });

    }
    private void parseJSOMWithGSON2(String jsonData){
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
            fuJinList.add(new LingYong(lingyongren,lingyongrenshijian,fengyinhao));
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new LingYongAdapter(TongJiLingYongActivity.this,R.layout.item_tongji_lingyong,fuJinList);
                listView = (ListView) findViewById(R.id.ls_tongji_lingyong);
                listView.setAdapter(adapter);

            }
        });
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text_tip.setVisibility(View.VISIBLE);

            }
        });

    }
}
