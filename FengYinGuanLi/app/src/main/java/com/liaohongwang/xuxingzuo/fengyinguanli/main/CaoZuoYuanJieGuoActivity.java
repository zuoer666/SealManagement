package com.liaohongwang.xuxingzuo.fengyinguanli.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.liaohongwang.xuxingzuo.fengyinguanli.R;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.CaoZuoYuanAnZhuang;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.CaoZuoYuanAnZhuangAdapter;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.CaoZuoYuanChaiChu;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.CaoZuoYuanChaiChuAdapter;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.CaoZuoYuanXunJian;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.CaoZuoYuanXunJianAdapter;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.LingYong;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.LingYongAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.banzuxuanze;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jieguo;

public class CaoZuoYuanJieGuoActivity extends AppCompatActivity {
    public static CaoZuoYuanAnZhuangAdapter adapter1;
    public static CaoZuoYuanChaiChuAdapter adapter2;
    public static CaoZuoYuanXunJianAdapter adapter3;
    public static LingYongAdapter adapter4;
    public static  ListView listView1;
    public static  ListView listView2;
    public static  ListView listView3;
    public static  ListView listView4;
    public static  TextView text_tip;
    public static List<CaoZuoYuanAnZhuang> fuJinList11 = new ArrayList<>();
    public static  List<CaoZuoYuanChaiChu> fuJinList22 = new ArrayList<>();
    public static List<CaoZuoYuanXunJian> fuJinList33 = new ArrayList<>();
    public static List<LingYong> fuJinList44 = new ArrayList<>();
    TextView top_tv_jieguo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cao_zuo_yuan_jie_guo);
        top_tv_jieguo = findViewById(R.id.top_caozuoyian_jieguo);
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
        text_tip = findViewById(R.id.text_tip_caozuoyuan);
        if (jieguo==1){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    CaoZuoYuanJieGuoActivity.adapter1 = new CaoZuoYuanAnZhuangAdapter(CaoZuoYuanJieGuoActivity.this,R.layout.item_tongji_caozuoyuan_anzhuang,CaoZuoYuanJieGuoActivity.fuJinList11);
                    CaoZuoYuanJieGuoActivity.listView1 = (ListView) findViewById(R.id.ls_tongji_caozuoyuanchaxun_anzhuang);
                    CaoZuoYuanJieGuoActivity.listView1.setAdapter(CaoZuoYuanJieGuoActivity.adapter1);

                }
            });
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    CaoZuoYuanJieGuoActivity.text_tip.setVisibility(View.VISIBLE);
                }
            });
        }else if(jieguo==2) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    CaoZuoYuanJieGuoActivity.adapter2 = new CaoZuoYuanChaiChuAdapter(CaoZuoYuanJieGuoActivity.this,R.layout.item_tongji_caozuoyuan_chaichu,CaoZuoYuanJieGuoActivity.fuJinList22);
                    CaoZuoYuanJieGuoActivity.listView2 = (ListView) findViewById(R.id.ls_tongji_caozuoyuanchaxun_chaichu);
                    CaoZuoYuanJieGuoActivity.listView2.setAdapter(CaoZuoYuanJieGuoActivity.adapter2);

                }
            });
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    CaoZuoYuanJieGuoActivity.text_tip.setVisibility(View.VISIBLE);
                }
            });
        }else if (jieguo==3){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    CaoZuoYuanJieGuoActivity.adapter3 = new CaoZuoYuanXunJianAdapter(CaoZuoYuanJieGuoActivity.this,R.layout.item_tongji_caozuoyuan_xunjian,CaoZuoYuanJieGuoActivity.fuJinList33);
                    CaoZuoYuanJieGuoActivity.listView3 = (ListView) findViewById(R.id.ls_tongji_caozuoyuanchaxun_xunjian);
                    CaoZuoYuanJieGuoActivity.listView3.setAdapter(CaoZuoYuanJieGuoActivity.adapter3);

                }
            });
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    CaoZuoYuanJieGuoActivity.text_tip.setVisibility(View.VISIBLE);
                }
            });
        }else if (jieguo==4){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    CaoZuoYuanJieGuoActivity.adapter4 = new LingYongAdapter(CaoZuoYuanJieGuoActivity.this,R.layout.item_tongji_lingyong,CaoZuoYuanJieGuoActivity.fuJinList44);
                    CaoZuoYuanJieGuoActivity.listView4 = (ListView) findViewById(R.id.ls_tongji_caozuoyuanchaxun_lingyong);
                    CaoZuoYuanJieGuoActivity.listView4.setAdapter(CaoZuoYuanJieGuoActivity.adapter4);

                }
            });
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    CaoZuoYuanJieGuoActivity.text_tip.setVisibility(View.VISIBLE);
                }
            });
        }



    }
    @Override
    protected void onStop() {
        super.onStop();
        fuJinList11.clear();
        fuJinList22.clear();
        fuJinList33.clear();
        fuJinList44.clear();
        this.finish();
    }
}
