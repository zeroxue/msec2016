package com.msec2016.expression;

import com.mifmif.common.regex.Generex;

/**
 * Created by miao on 2016/9/25.
 */
public interface IExpressionGen {


    void setDataType(boolean intType, boolean fractionType, boolean mixedFrationType);

    void setOperatorType(boolean addOp, boolean subOp, boolean mulOp, boolean divOp);

    void setOperatorNum(int minOpNum,int maxOpNum);

    Generex getGenerex();
}
