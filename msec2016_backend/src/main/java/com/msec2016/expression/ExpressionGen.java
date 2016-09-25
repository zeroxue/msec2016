package com.msec2016.expression;

import com.mifmif.common.regex.Generex;

/**
 * Created by miao on 2016/9/25.
 */
public class ExpressionGen implements IExpressionGen {

    private String dataType = "";
    private String operatorType = "";


    public String addNewType(String originalType, String newType) {
        if (originalType.isEmpty()) {
            return newType;
        } else {
            return "(" + originalType + "|" + newType + ")";
        }
    }


    @Override
    public void setDataType(boolean intType, boolean fractionType, boolean mixedFrationType) {

        dataType = "";//every update will delete the former one

        String integer = "[1-9]\\d{0,2}";
        String justFrac = "\\(" + integer + "/" + integer + "\\)"; // fraction,the numerator and denominator should not be too big
        String mixedFrac = integer + "'" + justFrac;


        if (intType) {
            dataType = addNewType(dataType, integer);
        }
        if (fractionType) {
            dataType = addNewType(dataType, justFrac);
        }
        if (mixedFrationType) {
            dataType = addNewType(dataType, mixedFrac);
        }
    }

    /**
     * @param addOp
     * @param subOp
     * @param mulOp
     * @param divOp, we use '#' to represent division
     */
    @Override
    public void setOperatorType(boolean addOp, boolean subOp, boolean mulOp, boolean divOp) {

        operatorType = "";//every update will delete the former one

        if (addOp) {
            operatorType = addNewType(operatorType, OperationToken.ADD.toString());
        }
        if (subOp) {
            operatorType = addNewType(operatorType, OperationToken.SUB.toString());
        }
        if (mulOp) {
            operatorType = addNewType(operatorType, OperationToken.MUL.toString());
        }
        if (divOp) {
            operatorType = addNewType(operatorType, "\\#"); //Interesting,should not be this.
        }
    }


    public Generex getGenerex() {


        String reStr = dataType + "(" + operatorType + dataType + ")+";

        return new Generex(reStr);
    }
}
