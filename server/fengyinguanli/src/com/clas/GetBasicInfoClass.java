package com.clas;

public class GetBasicInfoClass {
	private String name;
    private String banzu;
    private String quanxian;
    
    public GetBasicInfoClass(String name, String banzu, String quanxian) {
        this.name = name;
        this.banzu = banzu;
        this.quanxian = quanxian;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBanzu() {
        return banzu;
    }

    public void setBanzu(String banzu) {
        this.banzu = banzu;
    }

    public String getQuanxian() {
        return quanxian;
    }

    public void setQuanxian(String quanxian) {
        this.quanxian = quanxian;
    }
}
