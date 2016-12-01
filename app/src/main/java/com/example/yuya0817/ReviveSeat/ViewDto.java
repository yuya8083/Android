package com.example.yuya0817.ReviveSeat;

import java.io.Serializable;


//  Created by yuya0817 on 2016/11/26.

public class ViewDto implements Serializable {
    private String userid;

    public ViewDto() {
    }

    public ViewDto(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
