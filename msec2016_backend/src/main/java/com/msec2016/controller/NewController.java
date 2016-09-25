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
    @RequestMapping(value = "/newProblems/{num}/{datatype}/{optype}", method = RequestMethod.GET)
    @ResponseBody
    public List<Problem> newProblems(@PathVariable int num, @PathVariable String datatype, @PathVariable String optype) {
        logger.info("What we really care : asking for " + num + ":" + datatype + "," + optype);


        String datatypeNew = "";
        String optypeNew = "";


        datatype = datatype.toLowerCase();
        if (datatype.contains("int")) {
            datatypeNew += "1";
        } else {
            datatypeNew += "0";
        }

        if (datatype.contains("fraction")) {
            datatypeNew += "1";
        } else {
            datatypeNew += "0";
        }

        if (datatype.contains("mixed")) { //in fact,it's a little wrong,for "mixedfraction" contains "fraction"
            datatypeNew += "1";
        } else {
            datatypeNew += "0";
        }

        optype = optype.toLowerCase();

        if (optype.contains("add")) {
            optypeNew += "+";
        }
        if (optype.contains("sub")) {
            optypeNew += "\\+";
        }
        if (optype.contains("mul")) {
            optypeNew += "*";
        }
        if (optype.contains("div")) {
            optypeNew += "@";  //ATTENTION,THIS IS A BIG TRICK,since http cannot transport `#` here,so use `@` for `#`
        }


        return newProblemsClear(num, datatypeNew, optypeNew);

    }


    /**
     * @param num
     * @param datatype
     * @param optype   can be just "+-*#"
     * @return
     */

    @RequestMapping(value = "/newProblemsClear/{num}/{datatype}/{optype}", method = RequestMethod.GET)
    @ResponseBody
    public List<Problem> newProblemsClear(@PathVariable int num, @PathVariable String datatype, @PathVariable String optype) {
        String requestFor = "What we really care : asking for " + num + ":" + datatype + "," + optype;
        logger.info(requestFor);


        if (datatype.matches("[0-1]{3}") == false) {
            String errmsg = "The datatype should be 000,001,010,...";
            logger.warning(errmsg);
            //return new SomeRtnError(errmsg,requestFor);
            return null;
        }
        if (optype.matches("[+\\-*@]{1,4}") == false) {       //ATTENTION,THIS IS A BIG TRICK,since http cannot transport `#` here,so use `@` for `#`
            logger.warning("The optype should be +,-,*,@,+-,...");
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
        if (datatype.charAt(2) == '1') { //in fact,it's a little wrong,for "mixedfraction" contains "fraction"
            mixedFrationType = true;
        }

        optype = optype.toLowerCase();

        if (optype.contains("+")) {
            addOp = true;
        }
        if (optype.contains("-")) {
            subOp = true;
        }
        if (optype.contains("*")) {
            mulOp = true;
        }
        if (optype.contains("@")) { //ATTENTION,THIS IS A BIG TRICK,since http cannot transport `#` here,so use `@` for `#`
            divOp = true;
        }


        List<Problem> problemList = proGenAndSolver.get_whatever_you_nee_Problems(num, 10, 10,
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