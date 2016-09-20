package com.msec2016.test;

import com.msec2016.model.Problem;
import com.msec2016.service.ProblemGeneratorAndSolver;

import java.util.List;

/**
 * Created by miao on 2016/9/8.
 */
public class Test {


    public static void main(String[] args) {

        ProblemGeneratorAndSolver solver = ProblemGeneratorAndSolver.INSTANCE;

        List<Problem> p1 = solver.newProblemsJustInt(1, 200, 200);

        System.out.println(p1.get(0).getDefinaton());
        System.out.println(p1.get(0).getDefinaton().length());

        solver.solveAll(p1);

        System.out.println(p1);


    }
}
