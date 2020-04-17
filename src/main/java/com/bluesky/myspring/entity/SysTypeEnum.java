package com.bluesky.myspring.entity;

import lombok.Getter;
import lombok.Setter;

public enum SysTypeEnum {

    BusWebSys("业务系统",0),
    BusManagerSys("业务后台",1),
    BusMobile("移动系统",2);
    @Getter
    private  String sysremark;
    @Getter
    private   Integer sysvalue;
    private SysTypeEnum(String _remark,Integer _value){
        this.sysremark=_remark;
        this.sysvalue=_value;
    }


}
