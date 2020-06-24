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

public class CaoZuoYuanBanZuAdapter extends ArrayAdapter<CaoZuoYuanBanZu> {
    private int resourceID;
    public CaoZuoYuanBanZuAdapter(Context context, int textViewResourceID, List<CaoZuoYuanBanZu> objects){
        super(context,textViewResourceID,objects);
        resourceID = textViewResourceID;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final CaoZuoYuanBanZu FengYinChaXun = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);

        //TextView zhanghao  = (TextView) view.findViewById(R.id.tv_tongji_banzu_zhanghao);
        TextView name = (TextView) view.findViewById(R.id.tv_tongji_banzu_name);
        TextView mun = view.findViewById(R.id.tv_tongji_banzu_jilumun);
        TextView xiangxi = view.findViewById(R.id.tv_tongji_banzu_xiangxi);
        //zhanghao.setText(FengYinChaXun.getZhanghao());
        name.setText(FengYinChaXun.getName());
        mun.setText(FengYinChaXun.getMun());
        xiangxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jiedan.onJieDanClick(FengYinChaXun.getZhanghao());
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
