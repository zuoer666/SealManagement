package com.clas;

public class LingYongClass {
	String lingyongren;
    String lingyongrenshijian;
    String fengyinhao;

    public LingYongClass(String lingyongren,String lingyongrenshijian,String fengyinhao) {
        this.lingyongren=lingyongren;
        this.lingyongrenshijian = lingyongrenshijian;
        this.fengyinhao = fengyinhao;
    }

    public String getLingyongren() {
        return lingyongren;
    }

    public void setLingyongren(String lingyongren) {
        this.lingyongren = lingyongren;
    }

    public String getLingyongrenshijian() {
        return lingyongrenshijian;
    }

    public void setLingyongrenshijian(String lingyongrenshijian) {
        this.lingyongrenshijian = lingyongrenshijian;
    }

    public String getFengyinhao() {
        return fengyinhao;
    }

    public void setFengyinhao(String fengyinhao) {
        this.fengyinhao = fengyinhao;
    }
}
