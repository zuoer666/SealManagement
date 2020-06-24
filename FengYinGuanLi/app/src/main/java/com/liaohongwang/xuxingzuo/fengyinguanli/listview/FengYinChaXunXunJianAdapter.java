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
 * Created by xuxingzuo on 2018/3/29.
 */

public class FengYinChaXunXunJianAdapter extends ArrayAdapter<FengYinChaXunXunJian> {

    private int resourceID;
    public FengYinChaXunXunJianAdapter(Context context, int textViewResourceID, List<FengYinChaXunXunJian> objects){
        super(context,textViewResourceID,objects);
        resourceID = textViewResourceID;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final FengYinChaXunXunJian FengYinChaXun = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);

        TextView xunjianren = (TextView) view.findViewById(R.id.tv_tongji_fengyinchaxun_xunjian_xunjianren);
        TextView dianbiaohao = (TextView) view.findViewById(R.id.tv_tongji_fengyinchaxun_xunjian_dianbiaohao);
        TextView dianbiaoxiang = (TextView) view.findViewById(R.id.tv_tongji_fengyinchaxun_xunjian_dianbiaoxiang);
        TextView xunjianshijian =  view.findViewById(R.id.tv_tongji_fengyinchaxun_xunjian_xunjianshijian);
        xunjianren.setText(FengYinChaXun.getXunjianren());
        dianbiaohao.setText(FengYinChaXun.getDianbiaohao());
        dianbiaoxiang.setText(FengYinChaXun.getDianbiaoxiang());
        xunjianshijian.setText(FengYinChaXun.getXunjianshijian());

        return view;
    }
}
