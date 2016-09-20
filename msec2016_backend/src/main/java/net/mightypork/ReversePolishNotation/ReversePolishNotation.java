package net.mightypork.ReversePolishNotation;


import net.mightypork.rcalc.Tokenizer;
import net.mightypork.rcalc.numbers.Fraction;

import java.util.*;

/**
 * Created by miao on 2016/9/11.
 */
public class ReversePolishNotation {

    private static Map<Character, Integer> operationPriority = new HashMap<Character, Integer>() {
        {
            put('@', -1);    // the init element

            put('+', 1);
            put('-', 1);
            put('*', 2);
            put('#', 2);
            put('/', 3); //fraction

            put('(', 0);
            put(')', 0); //deal with parenthesis separately
        }
    };

    static boolean operationHigher(char a, char b) {
        return operationPriority.get(a) > operationPriority.get(b);
    }


    public static void main(String[] args) {

        System.out.println("hei this is main");

        //System.out.println(operationPriority.get(')'));


        String s = "402*8#9-7-297-3+4*4-9#9-2-3+94-4-6*1-77-6+3#9+1#5-5+3-9*2+1-7#3#8#14#5#1#7#9#5+3#9-9-4*6#2+4-50#54#7*9#9#7-2*1+5-2*63-43+8*4-20#9*3*40";
        //"-9-5-5-5#8*7#6#29*5+5+2+7*59#1#1#3#541#6-8+5#6#7#6-1#76*7-8-2-9+6#3+6";

        //[-1857738804709/1539772560]

        //String s = "2*3#4";

    }

    /**
     * RPN solver
     *
     * @param input
     * @return
     */
    public Fraction solver(List<String> input) {

        Stack<String> syntaxTree = new Stack<>();

        Stack<Fraction> fracStack = new Stack<>();

        Fraction tf = new Fraction(1);


        for (String s : input) {
            if ("+*#/".contains(s)) { //operator,take care of the ORDER!!!

                String s2 = syntaxTree.pop();
                String s1 = syntaxTree.pop();

                Fraction f2 = fracStack.pop();
                Fraction f1 = fracStack.pop();


                String tmp = "{" + s + "," + s1 + "," + s2 + "}";
                syntaxTree.push(tmp);

                switch (s) {
                    case "+":
                        tf = f1.add(f2);
                        break;
                    //case "-":tf = f1.subtract(f2);break; //we have changed '-' to
                    case "*":
                        tf = f1.multiply(f2);
                        break;
                    case "#":
                        tf = f1.divide(f2);
                        break;
                    case "/":
                        tf = f1.divide(f2);
                        break;  //deal with fraction
                }

                fracStack.push(tf);

            } else {
                syntaxTree.push(s);
                fracStack.push(new Fraction(s));
            }
        }

        System.out.println(fracStack);
        //return syntaxTree.pop();

        return fracStack.pop();
    }


    /**
     * Do not deal with so many chanced errors.Just Make things run,the faster the better.
     *
     * @param inputStr
     * @return
     */
    public List<String> rpn(String inputStr) {
        Stack<Character> operationStack = new Stack<>();
        operationStack.push('@');

        List<String> numArr = new ArrayList<>();


          /* tokenizer.formatStr will format the string*/
        Tokenizer tokenizer = new Tokenizer();
        String input = tokenizer.formatStr(inputStr);
        System.out.println("after format: " + input);

        //String input = inputStr;

        String tmp = "";
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if ("+*#()/'".indexOf(ch) == -1) {
                //not operator or parenthesis or '/'fraction and "'"(带分数),and do not deal with '-' at all,
                // for we have deal with it before in the tokenizer.formatStr() funtion
                tmp += ch;
                continue;
            } else if (!tmp.equals("")) {
                numArr.add(tmp);
                tmp = "";
            }


            if (ch == '(') {
                operationStack.push(ch);
            } else if (ch == ')') {

                while (operationStack.peek() != '(') {
                    numArr.add(operationStack.pop().toString());
                }

                operationStack.pop();//pop '(' away
            } else { // +-*# operator and /(fraction)

                if (operationHigher(ch, operationStack.peek())) {
                    operationStack.push(ch);
                } else {
                    while (operationStack.peek() != '(' &&
                            operationHigher(ch, operationStack.peek()) == false) {
                        //I made it wrong,if the stack peek is '(',should stop. "2+(2*3)" should be "2 2 3 * +",not "2 2 * 3 +"


                        numArr.add(operationStack.pop().toString());
                    }

                    operationStack.push(ch);
                }
            }
        }

        //put the last num into account,"1+2"->"1 2" not "1" ('+' is pop out below)
        if (!tmp.equals("")) {
            numArr.add(tmp);
            tmp = "";
        }

        while (operationStack.peek() != '@') {
            numArr.add(operationStack.pop().toString());
        }


        return numArr;
    }

}
