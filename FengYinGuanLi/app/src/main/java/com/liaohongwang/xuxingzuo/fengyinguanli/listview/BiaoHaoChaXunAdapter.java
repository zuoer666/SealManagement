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

public class BiaoHaoChaXunAdapter extends ArrayAdapter<BiaoHaoChaXun> {
    private int resourceID;
    public BiaoHaoChaXunAdapter(Context context, int textViewResourceID, List<BiaoHaoChaXun> objects){
        super(context,textViewResourceID,objects);
        resourceID = textViewResourceID;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final BiaoHaoChaXun FengYinChaXun = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);

        TextView anzhungren = (TextView) view.findViewById(R.id.tv_tongji_biaohaochaxun_anzhuangren);
        TextView anzhungshijian = (TextView) view.findViewById(R.id.tv_tongji_biaohaochaxun_anzhuanhshijian);
        TextView chaichuren = (TextView) view.findViewById(R.id.tv_tongji_biaohaochaxun_chaichuren);
        TextView chaichushijian = (TextView) view.findViewById(R.id.tv_tongji_biaohaochaxun_chaichushijian);
        //extView dili = (TextView) view.findViewById(R.id.tv_tongji_biaohaochaxun_diliweizhi);
        TextView dianbiaoxiang =  view.findViewById(R.id.tv_tongji_biaohaochaxun_dianbiaoxiang);
        TextView fengyinhao =  view.findViewById(R.id.tv_tongji_biaohaochaxun_fengyinhao);
        anzhungren.setText(FengYinChaXun.getAnzhuangren());
        anzhungshijian.setText(FengYinChaXun.getAnzhuangshijian());
        chaichuren.setText(FengYinChaXun.getChaichuren());
        chaichushijian.setText(FengYinChaXun.getChaichushijian());
        //dili.setText(FengYinChaXun.getDili());
        dianbiaoxiang.setText(FengYinChaXun.getDianbiaoxiang());
        fengyinhao.setText(FengYinChaXun.getFengyinhao());

        return view;
    }
}