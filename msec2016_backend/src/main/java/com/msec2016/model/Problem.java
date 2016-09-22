package com.msec2016.model;

import com.alibaba.fastjson.JSON;

/**
 * Created by miao on 2016/9/7.
 */
public class Problem {

    private String definition = "";
    private String answer = "";

    public Problem(String _definaton) {
        this.definition = _definaton;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
