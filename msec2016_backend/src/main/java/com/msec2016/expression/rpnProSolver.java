package com.msec2016.expression;

import com.msec2016.ReversePolishNotation.ReversePolishNotation;
import net.mightypork.rcalc.numbers.Fraction;

import java.util.List;

/**
 * Created by miao on 2016/9/25.
 */
public class rpnProSolver implements IProSolver {

    private ReversePolishNotation rpnSolver = new ReversePolishNotation();

    @Override
    public List<Problem> solveAll(List<Problem> problemList) {

        for (Problem problem : problemList) {
            List<String> s2list = rpnSolver.toRPNpresentaion(problem.getDefinition());
            System.out.println(s2list);

            Fraction ans = rpnSolver.solver(s2list);
            problem.setAnswer(ans.toString());
        }


        return problemList;
    }
}
