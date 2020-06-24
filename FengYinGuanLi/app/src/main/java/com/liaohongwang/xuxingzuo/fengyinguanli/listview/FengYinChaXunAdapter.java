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

public class FengYinChaXunAdapter extends ArrayAdapter<FengYinChaXun> {
    private int resourceID;
    public FengYinChaXunAdapter(Context context, int textViewResourceID, List<FengYinChaXun> objects){
        super(context,textViewResourceID,objects);
        resourceID = textViewResourceID;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final FengYinChaXun FengYinChaXun = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);

        TextView anzhungren = (TextView) view.findViewById(R.id.tv_tongji_fengyinchaxun_anzhuangren);
        TextView anzhungshijian = (TextView) view.findViewById(R.id.tv_tongji_fengyinchaxun_anzhuanhshijian);
        TextView chaichuren = (TextView) view.findViewById(R.id.tv_tongji_fengyinchaxun_chaichuren);
        TextView chaichushijian = (TextView) view.findViewById(R.id.tv_tongji_fengyinchaxun_chaichushijian);
        TextView dili = (TextView) view.findViewById(R.id.tv_tongji_fengyinchaxun_diliweizhi);
        TextView dianbiaoxiang =  view.findViewById(R.id.tv_tongji_fengyinchaxun_dianbiaoxiang);
        TextView dianbiaohao =  view.findViewById(R.id.tv_tongji_fengyinchaxun_dianbiaohao);
        anzhungren.setText(FengYinChaXun.getAnzhuangren());
        anzhungshijian.setText(FengYinChaXun.getAnzhuangshijian());
        chaichuren.setText(FengYinChaXun.getChaichuren());
        chaichushijian.setText(FengYinChaXun.getChaichushijian());
        dili.setText(FengYinChaXun.getDili());
        dianbiaoxiang.setText(FengYinChaXun.getDianbiaoxiang());
        dianbiaohao.setText(FengYinChaXun.getDianbiaohao());

        return view;
    }
}
