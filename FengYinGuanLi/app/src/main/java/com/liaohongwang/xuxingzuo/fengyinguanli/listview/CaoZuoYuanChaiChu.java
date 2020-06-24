package com.liaohongwang.xuxingzuo.fengyinguanli.listview;

/**
 * Created by xuxingzuo on 2018/3/13.
 */

public class CaoZuoYuanChaiChu {
    String dianbiaohao;
    String chaichushijian;
    String dili;
    String dianbiaoxiang;
    String fengyinhao;

    public CaoZuoYuanChaiChu(String dianbiaohao,String chaichushijian, String dili, String dianbiaoxiang ,String fengyinhao) {
        this.dianbiaohao=dianbiaohao;
        this.chaichushijian = chaichushijian;
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

    public String getChaichushijian() {
        return chaichushijian;
    }

    public void setChaichushijian(String chaichushijian) {
        this.chaichushijian = chaichushijian;
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
