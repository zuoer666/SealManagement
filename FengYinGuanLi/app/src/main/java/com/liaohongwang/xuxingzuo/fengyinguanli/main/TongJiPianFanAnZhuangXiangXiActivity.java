package com.liaohongwang.xuxingzuo.fengyinguanli.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.liaohongwang.xuxingzuo.fengyinguanli.R;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.TongJiPinFanAnzhuangXiangXi;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.TongJiPinFanAnzhuangXiangXiAdapter;

import java.util.ArrayList;
import java.util.List;

public class TongJiPianFanAnZhuangXiangXiActivity extends AppCompatActivity {
    public static TongJiPinFanAnzhuangXiangXiAdapter adapter1;
    public static ListView listView1;
    public static List<TongJiPinFanAnzhuangXiangXi> fuJinList11 = new ArrayList<>();
    TextView text_tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong_ji_pian_fan_an_zhuang_xiang_xi);
        text_tip = findViewById(R.id.text_tip_pinfananzhuang_xiangxi);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TongJiPianFanAnZhuangXiangXiActivity.adapter1 = new TongJiPinFanAnzhuangXiangXiAdapter(TongJiPianFanAnZhuangXiangXiActivity.this,R.layout.item_tongji_pinfananzhuangxiangxi,TongJiPianFanAnZhuangXiangXiActivity.fuJinList11);
                TongJiPianFanAnZhuangXiangXiActivity.listView1 = (ListView) findViewById(R.id.ls_tongji_pinfananzhuang_xiangxi);
                TongJiPianFanAnZhuangXiangXiActivity.listView1.setAdapter(TongJiPianFanAnZhuangXiangXiActivity.adapter1);

            }
        });
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text_tip.setVisibility(View.VISIBLE);
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        fuJinList11.clear();
        this.finish();
    }
}
