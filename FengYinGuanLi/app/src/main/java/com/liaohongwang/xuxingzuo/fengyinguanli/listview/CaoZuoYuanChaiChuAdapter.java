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

public class CaoZuoYuanChaiChuAdapter extends ArrayAdapter<CaoZuoYuanChaiChu> {

    private int resourceID;
    public CaoZuoYuanChaiChuAdapter(Context context, int textViewResourceID, List<CaoZuoYuanChaiChu> objects){
        super(context,textViewResourceID,objects);
        resourceID = textViewResourceID;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final CaoZuoYuanChaiChu FengYinChaXun = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);

        TextView dianbiaohao = (TextView) view.findViewById(R.id.tv_tongji_caozuoyuan_chaichu_dianbiao);
        TextView chaichushijian = (TextView) view.findViewById(R.id.tv_tongji_caozuoyuan_chaichu_chaichushijian);
        TextView dili = (TextView) view.findViewById(R.id.tv_tongji_caozuoyuan_chaichu_diliweizhi);
        TextView dianbiaoxiang = (TextView) view.findViewById(R.id.tv_tongji_caozuoyuan_chaichu_dianbiaoxiang);
        TextView fengyinhao =  view.findViewById(R.id.tv_tongji_caozuoyuan_chaichu_fengyinhao);
        dianbiaohao.setText(FengYinChaXun.getDianbiaohao());
        chaichushijian.setText(FengYinChaXun.getChaichushijian());
        dili.setText(FengYinChaXun.getDili());
        dianbiaoxiang.setText(FengYinChaXun.getDianbiaoxiang());
        fengyinhao.setText(FengYinChaXun.getFengyinhao());

        return view;
    }
}