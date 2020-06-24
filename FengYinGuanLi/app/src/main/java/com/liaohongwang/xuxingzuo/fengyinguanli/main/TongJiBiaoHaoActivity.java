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
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.BiaoHaoChaXun;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.BiaoHaoChaXunAdapter;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.BiaoHaoChaXunXunJian;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.BiaoHaoChaXunXunJianAdapter;
import com.liaohongwang.xuxingzuo.fengyinguanli.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.IP;

public class TongJiBiaoHaoActivity extends AppCompatActivity {
    BiaoHaoChaXunAdapter adapter;
    BiaoHaoChaXunXunJianAdapter adapter11;
    private ListView listView;
    private ListView listView11;
    private TextView text_tip;
    private EditText ed_tongji_biaohao;
    private String biaohao;
    private List<BiaoHaoChaXun> fuJinList = new ArrayList<>();
    private List<BiaoHaoChaXunXunJian> fuJinList11 = new ArrayList<>();
    Button btn;
    Button btn11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong_ji_biao_hao);
        text_tip = findViewById(R.id.text_tip_biaohao);
        btn=findViewById(R.id.btn_tongji_dianbiao_submit);
        btn11=findViewById(R.id.btn_tongji_dianbiao_xunjian_submit);
        ed_tongji_biaohao=findViewById(R.id.ed_tongji_dianbiao_info);
        listView=findViewById(R.id.ls_tongji_biaohaochaxun_);
        listView11=findViewById(R.id.ls_tongji_biaohaochaxun_xunjian);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    // 进行点击事件后的逻辑操作
                    listView.setVisibility(v.VISIBLE);
                    fuJinList.clear();
                    listView11.setVisibility(v.GONE);
                    biaohao=ed_tongji_biaohao.getText().toString();
                    sendRequestWithOkHttp();
                }

            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    // 进行点击事件后的逻辑操作
                    listView11.setVisibility(v.VISIBLE);
                    fuJinList11.clear();
                    listView.setVisibility(v.GONE);
                    biaohao=ed_tongji_biaohao.getText().toString();
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
                            .url("http://" + IP + "/fengyinguanli/BiaoHaoChaXun?biaohao="+biaohao)
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
                            .url("http://" + IP + "/fengyinguanli/BiaoHaoChaXunXunJian?biaohao="+biaohao)
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
        String fengyinhao;
        //List<DingDan> mLists= new ArrayList<DingDan>();
        Gson gson = new Gson();
        List<BiaoHaoChaXun> fuJinList1 = gson.fromJson(jsonData,new TypeToken<List<BiaoHaoChaXun>>(){}.getType());
        for (BiaoHaoChaXun fuJin :fuJinList1){
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
            fengyinhao = "封印号："+fuJin.getFengyinhao();
            fuJinList.add(new BiaoHaoChaXun(anzhungren,anzhungshijian,chaichuren,chaichushijian,dili,dianbiaoxiang,fengyinhao));
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new BiaoHaoChaXunAdapter(TongJiBiaoHaoActivity.this,R.layout.item_tongji_biaohao,fuJinList);
                listView = (ListView) findViewById(R.id.ls_tongji_biaohaochaxun_);
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
    private void parseJSOMWithGSON1(String jsonData){
        String xunjianren;
        String fengyinhao;
        String dianbiaoxiang;
        String xunjianshijian;
        //List<DingDan> mLists= new ArrayList<DingDan>();
        Gson gson = new Gson();
        List<BiaoHaoChaXunXunJian> fuJinList1 = gson.fromJson(jsonData,new TypeToken<List<BiaoHaoChaXunXunJian>>(){}.getType());
        for (BiaoHaoChaXunXunJian fuJin :fuJinList1){
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
            fengyinhao = "封印号："+fuJin.getFengyinhao();
            fuJinList11.add(new BiaoHaoChaXunXunJian(xunjianren,fengyinhao,dianbiaoxiang,xunjianshijian));
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter11 = new BiaoHaoChaXunXunJianAdapter(TongJiBiaoHaoActivity.this,R.layout.item_tongji_biaohao_xunjian,fuJinList11);
                listView11 = (ListView) findViewById(R.id.ls_tongji_biaohaochaxun_xunjian);
                listView11.setAdapter(adapter11);

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
