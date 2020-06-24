package com.clas;


public class GetNameAndBanZuClass {
	private String name;
    private String banzu;

    public GetNameAndBanZuClass(String name, String banzu) {
        this.name = name;
        this.banzu = banzu;
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
}
