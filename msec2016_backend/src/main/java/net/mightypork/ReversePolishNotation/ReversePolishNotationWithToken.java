package net.mightypork.ReversePolishNotation;

import net.mightypork.rcalc.IToken;
import net.mightypork.rcalc.ParseError;
import net.mightypork.rcalc.TokenList;
import net.mightypork.rcalc.Tokenizer;
import net.mightypork.rcalc.numbers.Fraction;
import net.mightypork.rcalc.tokens.*;

import java.util.*;

/**
 * Created by miao on 2016/9/11.
 */
public class ReversePolishNotationWithToken {

    /** Tokenizer instance */
    private Tokenizer tokenizer = new Tokenizer();


    private class TokenOperatorBase implements IOperatorToken {

        @Override
        public TokenList wrapInTokenList() {
            return new TokenList(this);
        }

    }

    /**
     * use IToken not IOperatorToken,for TokenParenthesisLeft/Right is not IOperatorToken
     */
    private static Map<Class<? extends IToken>,Integer> operationPriority = new HashMap<Class<? extends IToken>, Integer>(){
        {
            put(TokenOperatorBase.class,-1);    // the init element,just a flag that this is the least priority

            put(TokenOperatorAdd.class,1);put(TokenOperatorSubtract.class,1);
            put(TokenOperatorMultiply.class,2);put(TokenOperatorDivide.class,2);
            put(TokenOperatorDivideFraction.class,3); //fraction

            put(TokenParenthesisLeft.class,0);put(TokenParenthesisRight.class,0); //deal with parenthesis separately
        }
    };



    static boolean operationHigher(IToken a,IToken b){
        return operationPriority.get(a.getClass()) > operationPriority.get(b.getClass());
    }



    public static void main(String[] args) {

        System.out.println("hei this is main");

        //System.out.println(operationPriority.get(')'));


        String s = "402*8#9-7-297-3+4*4-9#9-2-3+94-4-6*1-77-6+3#9+1#5-5+3-9*2+1-7#3#8#14#5*3";
        //#1#7#9#5+3#9-9-4*6#2+4-50#54#7*9#9#7-2*1+5-2*63-43+8*4-20#9*3*40";
        //"-9-5-5-5#8*7#6#29*5+5+2+7*59#1#1#3#541#6-8+5#6#7#6-1#76*7-8-2-9+6#3+6";

        //[-1857738804709/1539772560]

        //String s = "2*3#4";


        IToken l = new TokenOperatorMultiply();
        IToken a = new TokenOperatorAdd();

        System.out.println("We got:" + operationHigher(l,a));



        ReversePolishNotationWithToken rpmwt = new ReversePolishNotationWithToken();

        TokenList newTokenList = rpmwt.rpnParseToken2FrationAndOperator(s);

        System.out.println(newTokenList);


        Fraction ans = rpmwt.solver(newTokenList);

        System.out.println(ans);


    }


    /**
     * RPN solver
     * @param input
     * @return
     */
    public Fraction solver(TokenList input){

        Stack<String> syntaxTree = new Stack<>();

        Stack<Fraction> fracStack = new Stack<>();

        Fraction tf = new Fraction(1);


        for(IToken token: input){

            if(token instanceof TokenOperator){ //operator,take care of the ORDER!!!

                String s2 = syntaxTree.pop();
                String s1 = syntaxTree.pop();

                Fraction f2 = fracStack.pop();
                Fraction f1 = fracStack.pop();


                String tmp =  "{"+token+","+s1+","+s2+"}";
                syntaxTree.push(tmp);


                if(token instanceof TokenOperatorAdd){
                    tf = f1.add(f2);
                }else if(token instanceof TokenOperatorSubtract){
                    tf = f1.subtract(f2);
                }else if(token instanceof TokenOperatorMultiply){
                    tf = f1.multiply(f2);
                }else if(token instanceof TokenOperatorDivide){
                    tf = f1.divide(f2);
                }


                fracStack.push(tf);

            }else {
                syntaxTree.push(token.toString());
                fracStack.push(new Fraction(token.toString()));
            }
        }

        System.out.println(fracStack);
        //return syntaxTree.pop();

        return fracStack.pop();
    }


    public TokenList rpnParseToken2FrationAndOperator(String input){
        TokenList tokenList = tokenizer.tokenize(input);


        return rpnParseToken2FrationAndOperator(tokenList);
    }




     private TokenList rpnParseToken2FrationAndOperator(TokenList list){
        Stack<IToken> operationStack = new Stack<>();   //
        operationStack.push(new TokenOperatorBase()); //init the operation stack

        TokenList rpnList = new TokenList();



         for(IToken token : list){
             if(token instanceof Fraction){ //already a fraction,just put it in
                 rpnList.add(token);
             }
             else if(token instanceof TokenParenthesisLeft){
                 rpnList.add(token);
             }
             else if(token instanceof TokenParenthesisRight){
                 while (operationStack.peek() instanceof TokenParenthesisLeft == false){
                     rpnList.add(operationStack.pop());
                 }
             }

             else {
                 if(operationHigher(token, operationStack.peek())){
                     operationStack.push(token);
                 }
                 else {
                     while (operationHigher(token, operationStack.peek()) == false){
                         rpnList.add(operationStack.pop());
                     }

                     operationStack.push(token);
                 }
             }

         }


         while (operationStack.peek() instanceof TokenOperatorBase == false){
             rpnList.add(operationStack.pop());
         }


         return rpnList;




    }

}
