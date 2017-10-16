package com.library.pojo;

import java.util.Date;

public class Reserve extends ReserveKey {
    private Date operateTime;

    private Date reserveTime;

    private Integer reserveState;

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Date getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Date reserveTime) {
        this.reserveTime = reserveTime;
    }

    public Integer getReserveState() {
        return reserveState;
    }

    public void setReserveState(Integer reserveState) {
        this.reserveState = reserveState;
    }
}