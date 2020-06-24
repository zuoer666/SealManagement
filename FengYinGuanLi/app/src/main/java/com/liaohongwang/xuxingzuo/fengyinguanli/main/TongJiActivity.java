package com.liaohongwang.xuxingzuo.fengyinguanli.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.liaohongwang.xuxingzuo.fengyinguanli.R;

import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jiluusername;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.quanxian;

public class TongJiActivity extends AppCompatActivity {
    private Button btn_tongji_1;
    private Button btn_tongji_2;
    private Button btn_tongji_3;
    private Button btn_tongji_4;
    private Button btn_tongji_5;
    private Button btn_tongji_6;
    private Button btn_tongji_7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong_ji);
        initView();
    }

    private void initView(){
        btn_tongji_1 = (Button) findViewById(R.id.tongji1);
        btn_tongji_2 = (Button) findViewById(R.id.tongji2);
        btn_tongji_3 = (Button) findViewById(R.id.tongji3);
        btn_tongji_4 = (Button) findViewById(R.id.tongji4);
        btn_tongji_5 = (Button) findViewById(R.id.tongji5);
        btn_tongji_6 = (Button) findViewById(R.id.tongji6);
        btn_tongji_7 = (Button) findViewById(R.id.tongji7);
        if (quanxian.equals("1")){
            btn_tongji_5.setVisibility(View.GONE);
            btn_tongji_6.setVisibility(View.GONE);
            btn_tongji_7.setVisibility(View.GONE);
        }
        if (quanxian.equals("2")){
            btn_tongji_6.setVisibility(View.GONE);
        }
        //领用
        btn_tongji_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jiluusername==null){
                    Toast.makeText(TongJiActivity.this, "请重新登录!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(TongJiActivity.this,TongJiFengYinActivity.class);
                startActivity(intent);
            }
        });
        //安装
        btn_tongji_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jiluusername==null){
                    Toast.makeText(TongJiActivity.this, "请重新登录!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(TongJiActivity.this, TongJiBiaoHaoActivity.class);
                startActivity(intent);
            }
        });
        // 操作员
        btn_tongji_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jiluusername==null){
                    Toast.makeText(TongJiActivity.this, "请重新登录!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(TongJiActivity.this, TongJiCaoZuoYuanActivity.class);
                startActivity(intent);
            }
        });
        //未巡检
        btn_tongji_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jiluusername==null){
                    Toast.makeText(TongJiActivity.this, "请重新登录!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(TongJiActivity.this, TongJiWeiXunJianActivity.class);
                startActivity(intent);
            }
        });
        //巡检
        btn_tongji_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jiluusername==null){
                    Toast.makeText(TongJiActivity.this, "请重新登录!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(TongJiActivity.this, TongJiXunJianActivity.class);
                startActivity(intent);
            }
        });
        //频繁安装
        btn_tongji_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jiluusername==null){
                    Toast.makeText(TongJiActivity.this, "请重新登录!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(TongJiActivity.this, TongJiPinFanAnZhuangActivity.class);
                startActivity(intent);
            }
        });
        //查询统计
        btn_tongji_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jiluusername==null){
                    Toast.makeText(TongJiActivity.this, "请重新登录!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(TongJiActivity.this, TongJiLingYongActivity.class);
                    startActivity(intent);

            }
        });

    }
}
