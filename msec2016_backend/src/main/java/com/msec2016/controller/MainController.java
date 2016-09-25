package com.msec2016.controller;

import com.msec2016.expression.ProGenAndSolver;
import com.msec2016.expression.Problem;
import com.msec2016.test.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by miao on 2016/9/6.
 */

@CrossOrigin
@Controller
public class MainController {
    Logger logger = Logger.getLogger("MainController");

    ProGenAndSolver proGenAndSolver = new ProGenAndSolver();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping("user")
    @ResponseBody
    public User returnUser() {
        User miao = new User("miaodx", 21);
        return miao;
    }

    @RequestMapping("gg")
    @ResponseBody
    public Object returnGG() {
        return "Well done";
    }

    @RequestMapping("newProblem")
    @ResponseBody
    public Object returnNewProblem() {
        List<Problem> problemList = proGenAndSolver.get_Int_AddOp_Problems(1);

        problemList = proGenAndSolver.solveAll();

        return problemList.get(0);
    }


    @RequestMapping(value = "newProblems")
    @ResponseBody
    public List<Problem> returnNewProblems() {

        List<Problem> ps = proGenAndSolver.get_Int_AddOp_Problems(10);
        ps = proGenAndSolver.solveAll();

        return ps;
    }


    @RequestMapping("newProblemsInt")
    @ResponseBody
    public Object returnNewProblemsInt() {

        List<Problem> ps = proGenAndSolver.get_Int_AddOp_Problems(10);
        ps = proGenAndSolver.solveAll();

        return ps;
    }

    @RequestMapping(value = "/newUser/{username}/{age}", method = RequestMethod.GET)
    @ResponseBody
    public User show(@PathVariable String username, @PathVariable int age) {
        logger.info("newUser got " + username + "," + age);
        return new User(username, age);
    }

    @RequestMapping(value = "/newUserOlder/{username}/{age}", method = RequestMethod.GET)
    @ResponseBody
    public User show2(@PathVariable String username, @PathVariable String age) {
        return new User(username, Integer.parseInt(age + 1));
    }
}

@CrossOrigin
@Controller
class UrlNotFoundController {
    @RequestMapping("*")
    public String test404() {
        return "error";
    }
}
