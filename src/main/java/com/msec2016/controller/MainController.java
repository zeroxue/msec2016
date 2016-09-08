package com.msec2016.controller;
import com.msec2016.model.Problem;
import com.msec2016.service.ProblemGeneratorAndSolver;
import com.msec2016.test.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by miao on 2016/9/6.
 */
@Controller
public class MainController {

    ProblemGeneratorAndSolver pgas =  ProblemGeneratorAndSolver.INSTANCE;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping("user")
    @ResponseBody
    public Object returnUser() {
        User miao = new User("miaodx",21);
        return miao;
    }


    @RequestMapping("newProblem")
    @ResponseBody
    public Object returnNewProblem() {
        Problem p = pgas.newProblem();
        pgas.solve(p);

        return p;
    }


    @RequestMapping("newProblemInt")
    @ResponseBody
    public Object returnNewProblemInt() {
        Problem p = pgas.newProblemJustInt();
        pgas.solve(p);

        return p;
    }


    @RequestMapping("newProblems")
    @ResponseBody
    public Object returnNewProblems() {

        List<Problem> ps = pgas.newProblems(10);
        pgas.solveAll(ps);

        return ps;
    }


    @RequestMapping("newProblemsInt")
    @ResponseBody
    public Object returnNewProblemsInt() {

        List<Problem> ps = pgas.newProblemsJustInt(10);
        pgas.solveAll(ps);

        return ps;
    }
}
