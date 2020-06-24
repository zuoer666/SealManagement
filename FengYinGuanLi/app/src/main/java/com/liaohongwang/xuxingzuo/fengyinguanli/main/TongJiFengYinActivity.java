package com.liaohongwang.xuxingzuo.fengyinguanli.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liaohongwang.xuxingzuo.fengyinguanli.R;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.FengYinChaXun;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.FengYinChaXunAdapter;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.FengYinChaXunXunJian;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.FengYinChaXunXunJianAdapter;
import com.liaohongwang.xuxingzuo.fengyinguanli.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.view.View.VISIBLE;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.IP;

public class TongJiFengYinActivity extends AppCompatActivity {
    FengYinChaXunXunJianAdapter adapter11;
    private ListView listView11;
    private List<FengYinChaXunXunJian> fuJinList11 = new ArrayList<>();
    FengYinChaXunAdapter adapter;
    private ListView listView;
    private TextView text_tip;
    private EditText ed_tongji_fenghao;
    private String fenghao;
    private List<FengYinChaXun> fuJinList = new ArrayList<>();
    Button btn;
    Button btn11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong_ji_feng_yin);
        text_tip = findViewById(R.id.text_tip_fengyin);
        btn=findViewById(R.id.btn_tongji_fengyin_submit);
        btn11=findViewById(R.id.btn_tongji_fengyin_xunjian_submit);
        ed_tongji_fenghao=findViewById(R.id.ed_tongji_fenghao_info);
        listView=findViewById(R.id.ls_tongji_fengyinchaxun_);
        listView11=findViewById(R.id.ls_tongji_fengyinchaxun_xunjian);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    listView.setVisibility(v.VISIBLE);
                    fuJinList.clear();
                    listView11.setVisibility(v.GONE);
                    fenghao=ed_tongji_fenghao.getText().toString();
                    sendRequestWithOkHttp();
                }

            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    listView11.setVisibility(v.VISIBLE);
                    fuJinList11.clear();
                    listView.setVisibility(v.GONE);
                    fenghao=ed_tongji_fenghao.getText().toString();
                    sendRequestWithOkHttp1();
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
                            .url("http://" + IP + "/fengyinguanli/FengYinChaXun?fenghao="+fenghao)
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
    private void sendRequestWithOkHttp1(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://" + IP + "/fengyinguanli/FengYinChaXunXunJian?fenghao="+fenghao)
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
    private void parseJSOMWithGSON(String jsonData){
        String anzhungren;
        String anzhungshijian;
        String chaichuren;
        String chaichushijian;
        String dili;
        String dianbiaoxiang;
        String dianbiaohao;
        //List<DingDan> mLists= new ArrayList<DingDan>();
        Gson gson = new Gson();
        List<FengYinChaXun> fuJinList1 = gson.fromJson(jsonData,new TypeToken<List<FengYinChaXun>>(){}.getType());
        for (FengYinChaXun fuJin :fuJinList1){

            anzhungren = "安装人 ："+fuJin.getAnzhuangren();
            if (fuJin.getAnzhuangshijian()!=null&&!fuJin.getAnzhuangshijian().equals("")){
                anzhungshijian = "安装时间 ："+fuJin.getAnzhuangshijian().substring(0,fuJin.getAnzhuangshijian().length()-3);
            }else{
                anzhungshijian = "安装时间 ："+fuJin.getAnzhuangshijian();
            }
            if (fuJin.getChaichuren().equals("null")){
                chaichuren= "拆除人 ：no";
            }else{
                chaichuren= "拆除人 ："+fuJin.getChaichuren();
            }

            if (fuJin.getChaichushijian()!=null&&!fuJin.getChaichushijian().equals("")){
                if (fuJin.getChaichushijian().equals("8888-08-08 08:08:08" )){
                    chaichushijian = "拆除时间 ："+"no";
                }else{
                    chaichushijian = "拆除时间 ："+fuJin.getChaichushijian().substring(0,fuJin.getChaichushijian().length()-3);
                }

            }else{
                chaichushijian = "拆除时间 ："+fuJin.getChaichushijian();
            }
            dili = fuJin.getDili();

            dianbiaoxiang= "电表箱 ："+fuJin.getDianbiaoxiang();
            dianbiaohao = "电表号："+fuJin.getDianbiaohao();
            fuJinList.add(new FengYinChaXun(anzhungren,anzhungshijian,chaichuren,chaichushijian,dili,dianbiaoxiang,dianbiaohao));
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new FengYinChaXunAdapter(TongJiFengYinActivity.this,R.layout.item_tongji_fengyin,fuJinList);
                listView = (ListView) findViewById(R.id.ls_tongji_fengyinchaxun_);
                listView.setAdapter(adapter);

            }
        });
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               text_tip.setVisibility(VISIBLE);

            }
        });
    }
    private void parseJSOMWithGSON1(String jsonData){
        String xunjianren;
        String dianbiaohao;
        String dianbiaoxiang;
        String xunjianshijian;
        //List<DingDan> mLists= new ArrayList<DingDan>();
        Gson gson = new Gson();
        List<FengYinChaXunXunJian> fuJinList1 = gson.fromJson(jsonData,new TypeToken<List<FengYinChaXunXunJian>>(){}.getType());
        for (FengYinChaXunXunJian fuJin :fuJinList1){
            xunjianren = "巡检人 ："+fuJin.getXunjianren();
            if (fuJin.getXunjianshijian()!=null&&!fuJin.getXunjianshijian().equals("")){
                xunjianshijian = "巡检时间 ："+fuJin.getXunjianshijian().substring(0,fuJin.getXunjianshijian().length()-3);
            }else{
                xunjianshijian = "巡检时间 ："+fuJin.getXunjianshijian();
            }
            if (fuJin.getDianbiaoxiang().equals("dianbiaoxiang is no")){
                dianbiaoxiang= "电表箱 ：no";
            }else {
                dianbiaoxiang= "电表箱 ："+fuJin.getDianbiaoxiang();
            }

            dianbiaohao = "电表号："+fuJin.getDianbiaohao();
            fuJinList11.add(new FengYinChaXunXunJian(xunjianren,dianbiaohao,dianbiaoxiang,xunjianshijian));
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter11 = new FengYinChaXunXunJianAdapter(TongJiFengYinActivity.this,R.layout.item_tongji_fengyin_xunjian,fuJinList11);
                listView11 = (ListView) findViewById(R.id.ls_tongji_fengyinchaxun_xunjian);
                listView11.setAdapter(adapter11);

            }
        });
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text_tip.setVisibility(VISIBLE);

            }
        });

    }
}
