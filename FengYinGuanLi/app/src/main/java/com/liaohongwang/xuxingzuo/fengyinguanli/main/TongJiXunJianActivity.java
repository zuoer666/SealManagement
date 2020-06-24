package com.liaohongwang.xuxingzuo.fengyinguanli.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liaohongwang.xuxingzuo.fengyinguanli.R;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.XunJian;
import com.liaohongwang.xuxingzuo.fengyinguanli.listview.XunJianAdapter;
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
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.banzu;

public class TongJiXunJianActivity extends AppCompatActivity {
    XunJianAdapter adapter;
    private ListView listView;
    private TextView text_tip;
    private List<XunJian> fuJinList = new ArrayList<>();
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong_ji_xun_jian);
        text_tip = findViewById(R.id.text_tip_xunjian);
        btn=findViewById(R.id.btn_tongji_xunjian_submit);
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
                    RequestBody formBody = new FormBody.Builder()
//                .add("jsonData", "{\"data\":\"121\",\"data1\":\"2232\"}")
                            .add("banzu", banzu)
                            .build();
                    Request request = new Request.Builder()
                            .url("http://" + IP + "/fengyinguanli/TongJiXunJian")
                            .post(formBody)
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
    private void parseJSOMWithGSON(String jsonData){
        String dianbiaohao;
        String anzhungshijian;
        String dili;
        String dianbiaoxiang;
        String fengyinhao;
        //List<DingDan> mLists= new ArrayList<DingDan>();
        Gson gson = new Gson();
        List<XunJian> fuJinList1 = gson.fromJson(jsonData,new TypeToken<List<XunJian>>(){}.getType());
        for (XunJian fuJin :fuJinList1){
            dianbiaohao = "电表号 ："+fuJin.getDianbiaohao();
            if (fuJin.getAnzhungshijian()!=null&&!fuJin.getAnzhungshijian().equals("")){
                anzhungshijian = "安装时间 ："+fuJin.getAnzhungshijian().substring(0,fuJin.getAnzhungshijian().length()-3);
            }else{
                anzhungshijian = "安装时间 ："+fuJin.getAnzhungshijian();
            }
            dili = fuJin.getDili();
            dianbiaoxiang= "安装人 ："+fuJin.getDianbiaoxiang();
            fengyinhao = "封印号："+fuJin.getFengyinhao();
            fuJinList.add(new XunJian(dianbiaohao,anzhungshijian,dili,dianbiaoxiang,fengyinhao));
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new XunJianAdapter(TongJiXunJianActivity.this,R.layout.item_tongji_xunjian,fuJinList);
                listView = (ListView) findViewById(R.id.ls_tongji_xunjian);
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
