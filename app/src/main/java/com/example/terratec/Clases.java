package com.example.terratec;

public class Clases {


    int tempdef;

    public int getTempdef() {
        return tempdef;
    }

    public void setTempdef(int tempdef) {
        this.tempdef = tempdef;
    }

    public float getHumedef() {
        return humedef;
    }

    public void setHumedef(float humedef) {
        this.humedef = humedef;
    }

    public String getHumeact() {
        return humeact;
    }

    public void setHumeact(String humeact) {
        this.humeact = humeact;
    }

    public String getTempact() {
        return tempact;
    }

    public void setTempact(String tempact) {
        this.tempact = tempact;
    }

    float humedef;
    String humeact;
    String tempact;




    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    String ip;


    public Clases( int tempdef, float humedef, String humeact, String tempact, String ip) {
        this.tempdef = tempdef;
        this.humedef = humedef;
        this.humeact = humeact;
        this.tempact = tempact;

        this.ip = ip;
    }


}
