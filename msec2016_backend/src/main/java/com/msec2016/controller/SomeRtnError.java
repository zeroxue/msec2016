package com.msec2016.controller;

/**
 * Created by miao on 2016/9/25.
 * <p>
 * To provide a rtnError to the front,so thing's can be a little easy understanding
 * <p>
 * Maybe configuration is better.FIX LATTER when necessary.
 */
public class SomeRtnError {

    private String message = "";
    private String whichRequest = "";

    SomeRtnError(String message, String whichRequest) {
        this.message = message;
        this.whichRequest = whichRequest;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getWhichRequest() {
        return whichRequest;
    }

    public void setWhichRequest(String whichRequest) {
        this.whichRequest = whichRequest;
    }


}
