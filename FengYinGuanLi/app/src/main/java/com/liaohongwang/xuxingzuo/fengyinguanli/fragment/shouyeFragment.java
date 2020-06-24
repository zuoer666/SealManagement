package com.liaohongwang.xuxingzuo.fengyinguanli.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liaohongwang.xuxingzuo.fengyinguanli.Login.BasicInfo;
import com.liaohongwang.xuxingzuo.fengyinguanli.R;
import com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine;
import com.liaohongwang.xuxingzuo.fengyinguanli.main.AnZhuangActivity;
import com.liaohongwang.xuxingzuo.fengyinguanli.main.ChaiChuActivity;
import com.liaohongwang.xuxingzuo.fengyinguanli.main.LingYongActivity;
import com.liaohongwang.xuxingzuo.fengyinguanli.main.TongJiActivity;
import com.liaohongwang.xuxingzuo.fengyinguanli.main.XunJianActivity;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.IP;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jilupassword;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jiluusername;
import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.quanxian;


/**
 * Created by xuxingzuo on 2017/11/4.
 */

public class shouyeFragment extends Fragment {
    // 返回的数据
    private Button btn_shouye_1;
    private Button btn_shouye_2;
    private Button btn_shouye_3;
    private Button btn_shouye_4;
    private Button btn_shouye_5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shouye_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //quanxianchaxun(jiluusername);
        initView();
        SharedPreferences sp = getActivity().getSharedPreferences("userandpassword",0);
        String bcuser = sp.getString("user","");
        bcuser=jiluusername;
        System.out.println(bcuser+"11111111111");
        String bcpassword = sp.getString("password","");
        bcpassword=jilupassword;
        sendRequestWithOkHttp2();

    }
    private void sendRequestWithOkHttp2(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://" + IP + "/fengyinguanli/GetBasicInfo?username="+jiluusername)
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
    private void parseJSOMWithGSON2(String jsonData){


        //List<DingDan> mLists= new ArrayList<DingDan>();
        Gson gson = new Gson();
        List<BasicInfo> fuJinList1 = gson.fromJson(jsonData,new TypeToken<List<BasicInfo>>(){}.getType());
        for (BasicInfo fuJin :fuJinList1){
            DataEngine.name = fuJin.getName();
            DataEngine.banzu =fuJin.getBanzu();
            quanxian = fuJin.getQuanxian();
        }
    }
//    public void quanxianchaxun(final String username) {
//
//        new Thread() {
//            @Override
//            public void run() {
//                String fanhui;
//                fanhui = WebService.chaxunquanli(username);
//                System.out.println(fanhui);
//                if (fanhui != null) {
//                    if (fanhui.equals("1")) {
//                        quanli = new String("1");
//                    } else {
//                        if (fanhui.equals("2")) {
//                            quanli = new String("2");
//                        } else {
//                            if (fanhui.equals("3")) {
//                                quanli = new String("3");
//
//                            }
//                        }
//                    }
//                }
//
//            }
//        }.start();
//    }

    private void initView() {
        btn_shouye_1 = (Button) getActivity().findViewById(R.id.shouye1);
        btn_shouye_2 = (Button) getActivity().findViewById(R.id.shouye2);
        btn_shouye_3 = (Button) getActivity().findViewById(R.id.shouye3);
        btn_shouye_4 = (Button) getActivity().findViewById(R.id.shouye4);
        btn_shouye_5 = (Button) getActivity().findViewById(R.id.shouye5);
        //领用
        btn_shouye_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jiluusername==null){
                    Toast.makeText(getActivity(), "请重新登录!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getActivity(), LingYongActivity.class);
                startActivity(intent);
            }
        });
        //安装
        btn_shouye_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jiluusername==null){
                    Toast.makeText(getActivity(), "请重新登录!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getActivity(), AnZhuangActivity.class);
                startActivity(intent);
            }
        });
        //拆除
        btn_shouye_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jiluusername==null){
                    Toast.makeText(getActivity(), "请重新登录!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getActivity(), ChaiChuActivity.class);
                startActivity(intent);
            }
        });
        //巡检
        btn_shouye_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jiluusername==null){
                    Toast.makeText(getActivity(), "请重新登录!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getActivity(), XunJianActivity.class);
                startActivity(intent);
            }
        });
        //查询统计
        btn_shouye_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jiluusername==null){
                    Toast.makeText(getActivity(), "请重新登录!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getActivity(), TongJiActivity.class);
                startActivity(intent);

            }
        });

    }
}
