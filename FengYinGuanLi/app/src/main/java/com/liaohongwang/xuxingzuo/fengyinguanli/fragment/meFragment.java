package com.liaohongwang.xuxingzuo.fengyinguanli.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.liaohongwang.xuxingzuo.fengyinguanli.Login.Login;
import com.liaohongwang.xuxingzuo.fengyinguanli.R;
import com.liaohongwang.xuxingzuo.fengyinguanli.main.XiuGaiMiMaActivity;

import static com.liaohongwang.xuxingzuo.fengyinguanli.core.DataEngine.jiluusername;

/**
 * Created by xuxingzuo on 2017/11/4.
 */

public class meFragment extends Fragment {
    Button btnqiehuanzhanghao;
    TextView tv_zhanghao;
    TextView tv_xiugaimima;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_fragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnqiehuanzhanghao = (Button) getActivity().findViewById(R.id.me_qiehuanzhanghao);
        tv_zhanghao = (TextView) getActivity().findViewById(R.id.me_zhanghaoxianshi);
        tv_zhanghao.setText(jiluusername);
        tv_xiugaimima=getActivity().findViewById(R.id.tv_me_xiugaimima);
        btnqiehuanzhanghao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        tv_xiugaimima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),XiuGaiMiMaActivity.class);
                startActivity(intent);
            }
        });
    }
}
