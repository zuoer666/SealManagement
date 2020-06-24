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

public class CaoZuoYuanAnZhuangAdapter extends ArrayAdapter<CaoZuoYuanAnZhuang> {

    private int resourceID;
    public CaoZuoYuanAnZhuangAdapter(Context context, int textViewResourceID, List<CaoZuoYuanAnZhuang> objects){
        super(context,textViewResourceID,objects);
        resourceID = textViewResourceID;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final CaoZuoYuanAnZhuang FengYinChaXun = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);

        TextView dianbiaohao = (TextView) view.findViewById(R.id.tv_tongji_caozuoyuan_anzhuang_dianbiao);
        TextView anzhungshijian = (TextView) view.findViewById(R.id.tv_tongji_caozuoyuan_anzhuang_anzhuanhshijian);
        TextView dili = (TextView) view.findViewById(R.id.tv_tongji_caozuoyuan_anzhuang_diliweizhi);
        TextView dianbiaoxiang = (TextView) view.findViewById(R.id.tv_tongji_caozuoyuan_anzhuang_dianbiaoxiang);
        TextView fengyinhao =  view.findViewById(R.id.tv_tongji_caozuoyuan_anzhuang_fengyinhao);
        dianbiaohao.setText(FengYinChaXun.getDianbiaohao());
        anzhungshijian.setText(FengYinChaXun.getAnzhungshijian());
        dili.setText(FengYinChaXun.getDili());
        dianbiaoxiang.setText(FengYinChaXun.getDianbiaoxiang());
        fengyinhao.setText(FengYinChaXun.getFengyinhao());

        return view;
    }
}