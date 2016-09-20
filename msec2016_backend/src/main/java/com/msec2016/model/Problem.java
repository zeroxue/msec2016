package com.msec2016.model;

import com.alibaba.fastjson.JSON;

/**
 * Created by miao on 2016/9/7.
 */
public class Problem {

    private String definaton = "";
    private String answer = "";

    public Problem(String _definaton) {
        this.definaton = _definaton;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


    public String getDefinaton() {
        return definaton;
    }

    public void setDefinaton(String definaton) {
        this.definaton = definaton;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
