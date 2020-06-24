package com.liaohongwang.xuxingzuo.fengyinguanli.listview;

/**
 * Created by xuxingzuo on 2018/3/13.
 */

public class BiaoHaoChaXun {
    String anzhuangren;
    String anzhuangshijian;
    String chaichuren;
    String chaichushijian;
    String dili;
    String dianbiaoxiang;
    String fengyinhao;

    public BiaoHaoChaXun(String anzhungren,String anzhungshijian, String chaichuren, String chaichushijian ,String dili ,String dianbiaoxiang,String fengyinhao) {
        this.anzhuangren=anzhungren;
        this.anzhuangshijian = anzhungshijian;
        this.chaichuren = chaichuren;
        this.chaichushijian = chaichushijian;
        this.dili = dili;
        this.dianbiaoxiang = dianbiaoxiang;
        this.fengyinhao = fengyinhao;
    }

    public String getAnzhuangren() {
        return anzhuangren;
    }

    public void setAnzhuangren(String anzhuangren) {
        this.anzhuangren = anzhuangren;
    }

    public String getAnzhuangshijian() {
        return anzhuangshijian;
    }

    public void setAnzhuangshijian(String anzhuangshijian) {
        this.anzhuangshijian = anzhuangshijian;
    }

    public String getChaichuren() {
        return chaichuren;
    }

    public void setChaichuren(String chaichuren) {
        this.chaichuren = chaichuren;
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
