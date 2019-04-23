package com.example.basemodule.bean;

public class JsonResponse<T> {

    /**
     * msg : 正常回包
     * code : 0
     * data : (T 相应的对象)
     */

    private String msg;
    private int code;
    private T data;
    private String token;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
