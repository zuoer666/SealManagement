package com.liaohongwang.xuxingzuo.fengyinguanli.Login;

/**
 * Created by xuxingzuo on 2018/3/19.
 */

public class NameAndClass {
    private String name;
    private String banzu;

    public NameAndClass(String name, String banzu) {
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
