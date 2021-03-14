package me.katnissali.bungeechat.Util;

public class Rank {

    private String name;
    private String permission;
    private String format;

    public Rank(String name, String permission, String format){
        this.name = name;
        this.permission = permission;
        this.format = format;
    }

    //  GETTERS
    public String getFormat(){
        return format;
    }
    public String getPerission(){
        return permission;
    }
    public String getName(){
        return name;
    }

}
