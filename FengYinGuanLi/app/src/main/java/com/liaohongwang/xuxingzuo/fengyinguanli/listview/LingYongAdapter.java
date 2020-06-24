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
 * Created by xuxingzuo on 2018/3/13.
 */


public class LingYongAdapter extends ArrayAdapter<LingYong> {
    private int resourceID;
    public LingYongAdapter(Context context, int textViewResourceID, List<LingYong> objects){
        super(context,textViewResourceID,objects);
        resourceID = textViewResourceID;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LingYong FengYinChaXun = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        TextView lingyongren = (TextView) view.findViewById(R.id.tv_tongji_lingyong_lingyongren);
        TextView lingyongrenshijian = (TextView) view.findViewById(R.id.tv_tongji_lingyong_lingyongshijian);
        TextView fengyinhao = (TextView) view.findViewById(R.id.tv_tongji_lingyong_fengyinhao);
        lingyongren.setText(FengYinChaXun.getLingyongren());
        lingyongrenshijian.setText(FengYinChaXun.getLingyongrenshijian());
        fengyinhao.setText(FengYinChaXun.getFengyinhao());

        return view;
    }
}
