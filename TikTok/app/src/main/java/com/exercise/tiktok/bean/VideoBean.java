package com.exercise.tiktok.bean;

public class VideoBean {
    private int img_up;
    private int img_video;
    private String name;
    private String miaoshu;
    private String dianzan;
    private String pingluen;
    private String zhuanfa;

    public VideoBean(int img_up, int img_video, String name, String miaoshu, String dianzan, String pingluen, String zhuanfa) {
        this.img_up = img_up;
        this.img_video = img_video;
        this.name = name;
        this.miaoshu = miaoshu;
        this.dianzan = dianzan;
        this.pingluen = pingluen;
        this.zhuanfa = zhuanfa;
    }

    public int getImg_up() {
        return img_up;
    }

    public int getImg_video() {
        return img_video;
    }

    public String getName() {
        return name;
    }

    public String getMiaoshu() {
        return miaoshu;
    }

    public String getDianzan() {
        return dianzan;
    }

    public String getPingluen() {
        return pingluen;
    }

    public String getZhuanfa() {
        return zhuanfa;
    }

}
