package com.msec2016.controller;
import com.msec2016.model.Problem;
import com.msec2016.service.ProblemGeneratorAndSolver;
import com.msec2016.test.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by miao on 2016/9/6.
 */
@Controller
public class MainController {
    Logger logger = Logger.getLogger("MainController");

    ProblemGeneratorAndSolver pgas =  ProblemGeneratorAndSolver.INSTANCE;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping("user")
    @ResponseBody
    public User returnUser() {
        User miao = new User("miaodx",21);
        return miao;
    }

    @RequestMapping("gg")
    @ResponseBody
    public User returnGG() {
        return "Well done";
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


    @RequestMapping(value = "newProblems")
    @ResponseBody
    public List<Problem> returnNewProblems() {

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

    @RequestMapping(value="/newUser/{username}/{age}",method=RequestMethod.GET)
    @ResponseBody
    public User show(@PathVariable String username,@PathVariable int age) {
        logger.info("newUser got " + username + "," + age);
        return  new User(username,age);
    }

    @RequestMapping(value="/newUserOlder/{username}/{age}",method=RequestMethod.GET)
    @ResponseBody
    public User show2(@PathVariable String username,@PathVariable String age) {
        return  new User(username,Integer.parseInt(age+1));
    }
}
@Controller class UrlNotFoundController {
    @RequestMapping("*") public String test404(){
            return "error";
    }
}
