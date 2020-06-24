package com.liaohongwang.xuxingzuo.fengyinguanli.listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.liaohongwang.xuxingzuo.fengyinguanli.R;

import java.util.List;

/**
 * Created by xuxingzuo on 2018/3/19.
 */

public class PinFanAnZhuangAdapter extends ArrayAdapter<PinFanAnZhuang> {
    private int resourceID;
    public PinFanAnZhuangAdapter(Context context, int textViewResourceID, List<PinFanAnZhuang> objects){
        super(context,textViewResourceID,objects);
        resourceID = textViewResourceID;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final PinFanAnZhuang FengYinChaXun = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        TextView dianbiaohao = (TextView) view.findViewById(R.id.tv_tongji_pinfananzhuang_dianbiao);
        //TextView anzhungshijian = (TextView) view.findViewById(R.id.tv_tongji_pinfananzhuang_anzhuanhshijian);
        //TextView dili = (TextView) view.findViewById(R.id.tv_tongji_pinfananzhuang_diliweizhi);
        //TextView dianbiaoxiang =  view.findViewById(R.id.tv_tongji_pinfananzhuang_dianbiaoxiang);
        //TextView fengyinhao =  view.findViewById(R.id.tv_tongji_pinfananzhuang_fengyinhao);
        TextView xiangxi = view.findViewById(R.id.tv_tongji_pinfananzhuang_xiangxi);
        dianbiaohao.setText(FengYinChaXun.getDianbiaohao());
        //anzhungshijian.setText(FengYinChaXun.getAnzhungshijian());
        //dili.setText(FengYinChaXun.getDili());
        //dianbiaoxiang.setText(FengYinChaXun.getDianbiaoxiang());
        //fengyinhao.setText(FengYinChaXun.getFengyinhao());
        xiangxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jiedan.onJieDanClick(FengYinChaXun.getDianbiaohao().substring(5,FengYinChaXun.getDianbiaohao().length()));
            }
        });
        return view;
    }
    public interface JieDanListener {
        void onJieDanClick(String i);
    }

    private JieDanListener jiedan;

    public void setJieDanListener(JieDanListener mJieDaneListener) {
        this.jiedan = mJieDaneListener;
    }
}
