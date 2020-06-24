package com.liaohongwang.xuxingzuo.fengyinguanli.listview;

/**
 * Created by xuxingzuo on 2018/3/13.
 */

public class CaoZuoYuanXunJian {
    String dianbiaohao;
    String xunjianshijian;
    String dili;
    String dianbiaoxiang;
    String fengyinhao;

    public CaoZuoYuanXunJian(String dianbiaohao,String xunjianshijian, String dili, String dianbiaoxiang ,String fengyinhao) {
        this.dianbiaohao=dianbiaohao;
        this.xunjianshijian = xunjianshijian;
        this.dili = dili;
        this.dianbiaoxiang = dianbiaoxiang;
        this.fengyinhao = fengyinhao;
    }

    public String getDianbiaohao() {
        return dianbiaohao;
    }

    public void setDianbiaohao(String dianbiaohao) {
        this.dianbiaohao = dianbiaohao;
    }

    public String getXunjianshijian() {
        return xunjianshijian;
    }

    public void setXunjianshijian(String xunjianshijian) {
        this.xunjianshijian = xunjianshijian;
    }

    public String getDili() {
        return dili;
    }

    public void setDili(String dili) {
        this.dili = dili;
    }

    public String getDianbiaoxiang() {
        return dianbiaoxiang;
    }

    public void setDianbiaoxiang(String dianbiaoxiang) {
        this.dianbiaoxiang = dianbiaoxiang;
    }

    public String getFengyinhao() {
        return fengyinhao;
    }

    public void setFengyinhao(String fengyinhao) {
        this.fengyinhao = fengyinhao;
    }
}
