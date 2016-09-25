package com.msec2016.expression;

import com.mifmif.common.regex.Generex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miao on 2016/9/25.
 */
public class ProGenAndSolver implements IProGenAndSolver {

    private IProSolver proSolver = new rpnProSolver();
    private IExpressionGen expressionGen = new ExpressionGen();
    private List<Problem> problemList = new ArrayList<>();

    @Override
    public List<Problem> get_Int_AddOp_Problems(int num) {


        expressionGen.setDataType(true, false, false);
        expressionGen.setOperatorType(true, false, false, false);

        Generex generex = expressionGen.getGenerex();


        problemList.clear(); // clear before use
        for (int i = 0; i < num; i++) {
            problemList.add(new Problem(generex.random(10, 10)));
        }


        return problemList;
    }


    @Override
    public List<Problem> get_Int_TwoOp_Problems(int num) {
        return null;
    }

    @Override
    public List<Problem> get_whatever_you_nee_Problems(int num, int minlen, int maxlen,
                                                       boolean intType, boolean fractionType, boolean mixedFrationType,
                                                       boolean addOp, boolean subOp, boolean mulOp, boolean divOp) {

        expressionGen.setDataType(intType, fractionType, mixedFrationType);
        expressionGen.setOperatorType(addOp, subOp, mulOp, divOp);

        Generex generex = expressionGen.getGenerex();


        problemList.clear(); // clear before use
        for (int i = 0; i < num; i++) {
            problemList.add(new Problem(generex.random(minlen, maxlen)));
        }


        return problemList;
    }

    @Override
    public List<Problem> solveAll() {
        return proSolver.solveAll(problemList);
    }

    @Override
    public List<Problem> solveProvidedProblems(List<Problem> ps) {

        return proSolver.solveAll(ps);

    }
}
