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

public class TongJiPinFanAnzhuangXiangXiAdapter extends ArrayAdapter<TongJiPinFanAnzhuangXiangXi> {
    private int resourceID;
    public TongJiPinFanAnzhuangXiangXiAdapter(Context context, int textViewResourceID, List<TongJiPinFanAnzhuangXiangXi> objects){
        super(context,textViewResourceID,objects);
        resourceID = textViewResourceID;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final TongJiPinFanAnzhuangXiangXi FengYinChaXun = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);

        TextView anzhungren = (TextView) view.findViewById(R.id.tv_tongji_pinfananzhuang_anzhuangren);
        TextView anzhungshijian = (TextView) view.findViewById(R.id.tv_tongji_pinfananzhuang_anzhuanhshijian);
        TextView chaichuren = (TextView) view.findViewById(R.id.tv_tongji_pinfananzhuang_chaichuren);
        TextView chaichushijian = (TextView) view.findViewById(R.id.tv_tongji_pinfananzhuang_chaichushijian);
        TextView dili = (TextView) view.findViewById(R.id.tv_tongji_pinfananzhuang_diliweizhi);
        TextView dianbiaoxiang =  view.findViewById(R.id.tv_tongji_pinfananzhuang_dianbiaoxiang);
        TextView fengyinhao =  view.findViewById(R.id.tv_tongji_pinfananzhuang_fengyinhao);
        anzhungren.setText(FengYinChaXun.getAnzhuangren());
        anzhungshijian.setText(FengYinChaXun.getAnzhuangshijian());
        chaichuren.setText(FengYinChaXun.getChaichuren());
        chaichushijian.setText(FengYinChaXun.getChaichushijian());
        dili.setText(FengYinChaXun.getDili());
        dianbiaoxiang.setText(FengYinChaXun.getDianbiaoxiang());
        fengyinhao.setText(FengYinChaXun.getFengyinhao());

        return view;
    }
}
