package com.lewei.vo;


import com.google.gson.Gson;

/**
 * Created by qwz on 2017/1/16 1:48.
 * Project lw4Home
 * Package com.homebase.com.lewei.production.vo
 *
 * @Doc
 */
public class ResultJson {

    Integer code = null;
    String message = "";
    Object data = "";

    public Integer getCode() {
        return code==null? 1:code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message==null?"":message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data==null? "":data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static String toJson(Integer code,String message,Object data) {
        Gson gson = new Gson();
        ResultJson resultJson = new ResultJson();
        resultJson.setCode(code);
        resultJson.setMessage(message);
        resultJson.setData(data==null?"":data);
        return gson.toJson(resultJson);
    }

}
