package net.mightypork.calculator;

import net.mightypork.rcalc.RCalc;

/**
 * Created by miao on 2016/9/9.
 */
public class Test2 {

    public static void main(String[] args) {
        RCalc rc = new RCalc();
        rc.setDebug(true);

//@formatter:off
        String[] testCases = new String[] {
                // Parser unit test
                "1+2+3",
                "3+(2+1)",
                "1*2+3",
                "3+2*1",
                "2*1+3",
                "1+2*3/4",
                "1+2*(3/4)",
                "3/4*2+1",
                "2*3*4*5",
                "5*(4*(3*2))",
                "3!2"
        };
        //@formatter:on

        for (String expr : testCases) {
            System.out.println("\n\n# Test Case #");

            try {
                //System.out.println("IN: " + expr);
                System.out.println("OUT: " + rc.evaluate(expr));
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }

    }

}
