package com.bluesky.myspring.entity;

import org.hibernate.id.GUIDGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ShareDataInfo {
    public  static  String DefaultUUID= UUID.randomUUID().toString();
    public  static  String CurrenDatestr= new SimpleDateFormat("yyyy-MM-dd HH:mi:ss").format(new Date());
}
