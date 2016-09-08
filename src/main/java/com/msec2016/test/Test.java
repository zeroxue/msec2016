package com.msec2016.test;

import com.msec2016.model.Problem;
import com.msec2016.service.ProblemGeneratorAndSolver;

/**
 * Created by miao on 2016/9/8.
 */
public class Test {
    public static void main(String[] args) {

        ProblemGeneratorAndSolver solver = ProblemGeneratorAndSolver.INSTANCE;

        Problem p1 = solver.newProblemJustInt();

        System.out.println(p1);

        solver.solve(p1);

        System.out.println(p1);

    }
}
