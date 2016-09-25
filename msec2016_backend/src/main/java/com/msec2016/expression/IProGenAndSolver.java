package com.msec2016.expression;

import java.util.List;

/**
 * Created by miao on 2016/9/25.
 */
public interface IProGenAndSolver {


    //List<Problem> getProblems();
    List<Problem> get_Int_AddOp_Problems(int num);

    List<Problem> get_Int_TwoOp_Problems(int num);

    /**
     * Well,this is UGLY Indeed.
     *
     * @return
     */
    List<Problem> get_whatever_you_nee_Problems(int num, int minlen, int maxlen,
                                                boolean intType, boolean fractionType, boolean mixedFrationType,
                                                boolean addOp, boolean subOp, boolean mulOp, boolean divOp
    );


    List<Problem> solveAll();

    List<Problem> solveProvidedProblems(List<Problem> problemList);

}
