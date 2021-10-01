package com.example.taeasy;

public class INList {

    String location, DIV_RN, Traffic_RN, uploadTime;
    int imageId;
    int qrcodeId;

    public INList(String location, String DIV_RN, String traffic_RN, String uploadTime, int imageId, int qrcodeId) {
        this.location = location;
        this.DIV_RN = DIV_RN;
        this.Traffic_RN = traffic_RN;
        this.uploadTime = uploadTime;
        this.imageId = imageId;
        this.qrcodeId = qrcodeId;
    }
}
