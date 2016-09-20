package com.msec2016.service;

import com.mifmif.common.regex.Generex;
import com.msec2016.model.GenExpression;
import com.msec2016.model.Problem;
import net.mightypork.rcalc.RCalc;
import net.mightypork.rcalc.numbers.Fraction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miao on 2016/9/8.
 */
public enum ProblemGeneratorAndSolver {
    INSTANCE;

    private RCalc rc = new RCalc();
    private Generex genFraction = new GenExpression().getGenExpression();
    private Generex genJustInt = new GenExpression().getGenExpression(true);
    private ProblemGeneratorAndSolver() {
        rc.setDebug(true);
    }

    public void solve(Problem problem) {

        Fraction ans = rc.evaluate(problem.getDefinaton());

        problem.setAnswer(ans.toString());
    }


    public void solveAll(List<Problem> problems) {
        for (Problem p : problems) {
            Fraction ans = rc.evaluate(p.getDefinaton());
            p.setAnswer(ans.toString());
        }
    }


    public Problem newProblem() {
        return newProblems(1, 1, 10, false).get(0);
    }

    public Problem newProblemJustInt() {
        return newProblems(1, 1, 10, true).get(0);
    }


    public List<Problem> newProblems(int num) {
        return newProblems(num, 1, 10, false);
    }

    public List<Problem> newProblems(int num, int min, int max) {
        return newProblems(num, min, max, false);
    }

    public List<Problem> newProblemsJustInt(int num) {
        return newProblems(num, 1, 10, true);
    }

    public List<Problem> newProblemsJustInt(int num, int min, int max) {
        return newProblems(num, min, max, true);
    }


    public List<Problem> newProblems(int num, int min, int max, boolean justInt) {

        Generex genE;
        if (justInt) {
            genE = genJustInt;
        } else {
            genE = genFraction;
        }

        List<Problem> rtn = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            rtn.add(new Problem(genE.random(min, max)));
        }

        return rtn;
    }


}
