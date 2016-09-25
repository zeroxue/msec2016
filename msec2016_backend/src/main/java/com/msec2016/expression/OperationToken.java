package com.msec2016.expression;

/**
 * Created by miao on 2016/9/25.
 */
public enum OperationToken {
    // 利用构造函数传参
    ADD('+'), SUB('-'), MUL('*'), DIV('#'), BASEOP('&'), FRACTION('/'), LEFTP('('), RIGHTP(')'),
    INSTANCE('~'); //ATTENTION `INSTANCE` is just a trick for us to use `allOperatioString`


    // 定义私有变量
    private char nCode;

    // 构造函数，枚举类型只能为私有
    private OperationToken(char _nCode) {
        this.nCode = _nCode;
    }

    public static void main(String[] args) {
        OperationToken add = OperationToken.ADD;
        System.out.println(add);
        System.out.println(OperationToken.INSTANCE.allOperationString());
    }

    @Override
    public String toString() {
        return Character.toString(this.nCode);
    }

    public String allOperationString() {

        String allStr = "";

        OperationToken[] operationTokens = OperationToken.values();

        for (OperationToken op : operationTokens) {
            allStr += op.toString();
        }

        return allStr;
    }
}