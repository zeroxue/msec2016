package com.msec2016.model;

import com.mifmif.common.regex.Generex;

/**
 * Created by miao on 2016/9/8.
 */
public class GenExpression {


    /**
     *
     * @param justInt produce integer only or fraction
     * @return
     */
    public Generex getGenExpression(boolean justInt){

        String integer = "[1-9]\\d{0,2}";
        String justFrac = "\\(" + integer + "/" + integer + "\\)"; // fraction,the numerator and denominator should not be too big


        String realNum = "";
        if(justInt){
            realNum = integer;
        }else{
            realNum = "(" + justFrac + "|" + integer + ")" ;   //integer or justFrac
        }


        String operator = "[\\+\\-\\*\\/]"; //operator


        String reStr = realNum + "(" + operator + realNum + ")+";

        System.out.println(reStr);

        return new Generex(reStr);
    }


    //fraction is by default
    public Generex getGenExpression(){
        return getGenExpression(false);
    }

}
