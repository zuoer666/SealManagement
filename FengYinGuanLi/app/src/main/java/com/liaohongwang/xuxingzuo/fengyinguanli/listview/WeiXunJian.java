package com.liaohongwang.xuxingzuo.fengyinguanli.listview;

/**
 * Created by xuxingzuo on 2018/3/19.
 */

public class WeiXunJian  {
    String dianbiaohao;
    String anzhungshijian;
    String dili;
    String dianbiaoxiang;
    String fengyinhao;

    public WeiXunJian(String dianbiaohao,String anzhungshijian, String dili, String dianbiaoxiang ,String fengyinhao) {
        this.dianbiaohao=dianbiaohao;
        this.anzhungshijian = anzhungshijian;
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

    public String getAnzhungshijian() {
        return anzhungshijian;
    }

    public void setAnzhungshijian(String anzhungshijian) {
        this.anzhungshijian = anzhungshijian;
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
