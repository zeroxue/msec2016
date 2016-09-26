package com.msec2016.controller;

import com.msec2016.expression.IProGenAndSolver;
import com.msec2016.expression.ProGenAndSolver;
import com.msec2016.expression.Problem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by miao on 2016/9/25.
 */

@CrossOrigin
@Controller
public class NewController {
    Logger logger = Logger.getLogger("NewController");


    IProGenAndSolver proGenAndSolver = new ProGenAndSolver();


    /**
     * @param num
     * @param datatype
     * @param optype   WE USE THIS:
     *                 public List<Problem> get_whatever_you_nee_Problems(int num, int minlen, int maxlen,
     *                 boolean intType, boolean fractionType, boolean mixedFrationType,
     *                 boolean addOp, boolean subOp, boolean mulOp, boolean divOp) {
     * @return
     */

    @RequestMapping(value = "/newProblems/{num}/{minOpNum}/{maxOpNum}/{datatype}/{optype}", method = RequestMethod.GET)
    @ResponseBody
    public List<Problem> newProblemsClear(@PathVariable int num,@PathVariable int minOpNum,@PathVariable int maxOpNum,
                                          @PathVariable String datatype, @PathVariable String optype) {
        String requestFor = "What we really care : asking for " + num + "{" + minOpNum + "," + maxOpNum + "}:" + datatype + "," + optype;
        logger.info(requestFor);


        if (datatype.matches("[0-1]{3}") == false) {
            String errmsg = "The datatype should be 000,001,010,...";
            logger.warning(errmsg);
            //return new SomeRtnError(errmsg,requestFor);
            return null;
        }
        if (optype.matches("[0-1]{4}") == false) {
            logger.warning("The optype should be 0000,0001,0010,...  represent `+-*#` ...");
            return null;
        }


        boolean intType = false, fractionType = false, mixedFrationType = false;
        boolean addOp = false, subOp = false, mulOp = false, divOp = false;


        datatype = datatype.toLowerCase();


        if (datatype.charAt(0) == '1') {
            intType = true;
        }
        if (datatype.charAt(1) == '1') {
            fractionType = true;
        }
        if (datatype.charAt(2) == '1') {
            mixedFrationType = true;
        }

        optype = optype.toLowerCase();

        if (optype.charAt(0) == '1') {
            addOp = true;
        }
        if (optype.charAt(1) == '1') {
            subOp = true;
        }
        if (optype.charAt(2) == '1') {
            mulOp = true;
        }
        if (optype.charAt(3) == '1') {
            divOp = true;
        }


        List<Problem> problemList = proGenAndSolver.get_whatever_you_nee_Problems(num, minOpNum, maxOpNum,
                intType, fractionType, mixedFrationType,
                addOp, subOp, mulOp, divOp);

        problemList = proGenAndSolver.solveAll();


        return problemList;

    }


    @RequestMapping(value = "/calc/{description}", method = RequestMethod.GET)
    @ResponseBody
    public Problem calc(@PathVariable String description) {

        //TRICK,to deal with the http transportation
        description = description.replaceAll("@", "#");
        description = description.replaceAll("&", "/");

        String requestFor = "What we got,want to calc: " + description;
        logger.info(requestFor);


        List<Problem> problemList = new ArrayList<>();
        problemList.add(new Problem(description));

        return proGenAndSolver.solveProvidedProblems(problemList).get(0);

    }
}