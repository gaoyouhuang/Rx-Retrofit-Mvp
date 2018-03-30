package com.example.scs.rxretrofitmvp.bean;

/**
 * Created by scs on 18-3-12.
 */

public class BaseResponse<T> {
    private int Code;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    private String Data;
    private boolean Success;
}
