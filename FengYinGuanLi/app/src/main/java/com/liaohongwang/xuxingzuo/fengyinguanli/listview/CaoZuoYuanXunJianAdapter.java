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


public class CaoZuoYuanXunJianAdapter extends ArrayAdapter<CaoZuoYuanXunJian> {

    private int resourceID;
    public CaoZuoYuanXunJianAdapter(Context context, int textViewResourceID, List<CaoZuoYuanXunJian> objects){
        super(context,textViewResourceID,objects);
        resourceID = textViewResourceID;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final CaoZuoYuanXunJian FengYinChaXun = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);

        TextView dianbiaohao = (TextView) view.findViewById(R.id.tv_tongji_caozuoyuan_xunjian_dianbiao);
        TextView xunjianshijian = (TextView) view.findViewById(R.id.tv_tongji_caozuoyuan_xunjian_xunjianshijian);
        TextView dili = (TextView) view.findViewById(R.id.tv_tongji_caozuoyuan_xunjian_diliweizhi);
        TextView dianbiaoxiang = (TextView) view.findViewById(R.id.tv_tongji_caozuoyuan_xunjian_dianbiaoxiang);
        TextView fengyinhao =  view.findViewById(R.id.tv_tongji_caozuoyuan_xunjian_fengyinhao);
        dianbiaohao.setText(FengYinChaXun.getDianbiaohao());
        xunjianshijian.setText(FengYinChaXun.getXunjianshijian());
        dili.setText(FengYinChaXun.getDili());
        dianbiaoxiang.setText(FengYinChaXun.getDianbiaoxiang());
        fengyinhao.setText(FengYinChaXun.getFengyinhao());

        return view;
    }
}