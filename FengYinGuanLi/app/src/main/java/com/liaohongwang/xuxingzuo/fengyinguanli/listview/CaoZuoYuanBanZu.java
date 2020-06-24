package com.liaohongwang.xuxingzuo.fengyinguanli.listview;

/**
 * Created by xuxingzuo on 2018/3/19.
 */

public class CaoZuoYuanBanZu {
    private String zhanghao;
    private String name;
    private String mun;

    public CaoZuoYuanBanZu(String zhanghao, String name, String mun) {
        this.zhanghao = zhanghao;
        this.name = name;
        this.mun = mun;
    }

    public String getZhanghao() {
        return zhanghao;
    }

    public void setZhanghao(String zhanghao) {
        this.zhanghao = zhanghao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMun() {
        return mun;
    }

    public void setMun(String mun) {
        this.mun = mun;
    }
}
