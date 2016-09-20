package net.mightypork.calculator;

import com.msec2016.model.Problem;
import com.msec2016.service.ProblemGeneratorAndSolver;
import net.mightypork.ReversePolishNotation.ReversePolishNotation;
import net.mightypork.ReversePolishNotation.ReversePolishNotationWithToken;
import net.mightypork.rcalc.RCalc;
import net.mightypork.rcalc.TokenList;
import net.mightypork.rcalc.Tokenizer;
import net.mightypork.rcalc.numbers.Fraction;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

import org.testng.annotations.Test;

/**
 * Created by miao on 2016/9/12.
 */
public class TestViaAssertJ {



    @Test
    public void kidding(){
        assertThat(1).isEqualTo(1);
    }



    public static Fraction rpntime(String s) {


        long begin2 = System.currentTimeMillis(); // 这段代码放在程序执行前

        ReversePolishNotation RPN = new ReversePolishNotation();

        List<String> s2list = RPN.rpn(s);
        System.out.println(s2list);

        Fraction ans = RPN.solver(s2list);

        System.out.println(ans);

        long end2 = System.currentTimeMillis() - begin2; // 这段代码放在程序执行后
        System.out.println("耗时：" + end2 + "毫秒");


        return ans;
    }

    public static Fraction rpntimeWithToken(String s) {


        long begin2 = System.currentTimeMillis(); // 这段代码放在程序执行前

        ReversePolishNotationWithToken RPN = new ReversePolishNotationWithToken();

        TokenList s2list = RPN.rpnParseToken2FrationAndOperator(s);
        System.out.println(s2list);

        Fraction ans = RPN.solver(s2list);

        System.out.println(ans);

        long end2 = System.currentTimeMillis() - begin2; // 这段代码放在程序执行后
        System.out.println("耗时：" + end2 + "毫秒");


        return ans;
    }

    @Test
    public static void testWithKnown() {
        RCalc rc = new RCalc();
        rc.setDebug(true);

        //"adas".charAt()

//@formatter:off
        Map<String, String> testCases = new LinkedHashMap<String, String>() {
            {
                // Parser unit test
                put("1'1/2","3/2");
                put("3/-2","-3/2");
                put("1+2", "3");
                put("1-2", "-1");
                put("2*3", "6");
                put("2#3", "2/3");
                //"2/3",
                put("1+2+3", "6");
                put("1+2-3", "0");
                put("1+2*3", "7");
                put("1+2#3", "5/3");

                put("2*3*4","24");
                put("2*3#4","3/2");
                put("2#3*4","8/3");
                put("402*8#9-7-297-3+4*4-9#9-2-3+94-4-6*1-77-6+3#9+1#5-5+3-9*2+1-7#3#8#14#5*3",
                        "2057/48");

                put("3#2","3/2");
                put("2#1/2","4");
                put("1#2+3/4","5/4");

                put("2#1'1/2","4/3");
                put("2+(2*3)","8");
                put("(1+2)","3");

                put("1/2/3/4","1/24");

            }
        };
        //@formatter:on

        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> entry : testCases.entrySet()) {
            //System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        for (Map.Entry<String, String> entry : testCases.entrySet()) {
            System.out.println("\n\n# Test Case #");

            try {
                System.out.println("IN: " + entry.getKey());


                System.out.println("The original soler:");

                Fraction rcans = rc.evaluate(entry.getKey());

                System.out.println("rcans:" + rcans);

                System.out.println("The rpn solver:");

                Fraction rpnans = rpntime(entry.getKey());

                System.out.println("rpnans:" + rpnans);


                System.out.println("The rpn with token");

                Fraction rpnansWithToken = rpntimeWithToken(entry.getKey());

                System.out.println("rpnans:" + rpnansWithToken);


                assertThat(rcans.toString())
                        .isEqualTo(rpnans.toString())
                        .isEqualTo(rpnansWithToken.toString())
                        .isEqualTo(entry.getValue())
                ;


            } catch (Exception e) {
                //System.out.println("ERROR: " + ;);
                e.printStackTrace();
                return;
            }
        }

    }


    public static Fraction timeRecursive(String s) {

        long begin1 = System.currentTimeMillis(); // 这段代码放在程序执行前


        System.out.println("problem length" + s.length());
        Problem p = new Problem(s);
        ProblemGeneratorAndSolver solver = ProblemGeneratorAndSolver.INSTANCE;
        solver.solve(p);

        System.out.println(p);

        long end1 = System.currentTimeMillis() - begin1; // 这段代码放在程序执行后
        System.out.println("耗时：" + end1 + "毫秒");


        return new Fraction(p.getAnswer());
    }


}
