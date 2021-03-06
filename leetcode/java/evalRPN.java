/*
 * evaluate the value of an arithmetic expression in Reverse Polish Notation. valid operators ar +,-,*,/.
 * test castes:
 * [2,1,+,3,*] -> (2+1)*3 = 9
 * [4,13,5,/,+] -> (4+13/5) = 6
 * */
import java.io.*;
import java.util.*;
import java.lang.IllegalArgumentException;
import java.util.EmptyStackException;

public class evalRPN{
    public int eval(String[] tokens){
        Stack<Integer> stk = new Stack<Integer>();
        int n = tokens.length;
        for(int i = 0; i < n; ++i){
            if(tokens[i].isEmpty())    continue;
            if(tokens[i].length() > 1 || Character.isLetterOrDigit(tokens[i].charAt(0))){
                stk.push(Integer.parseInt(tokens[i]));
                continue;
            }
            int x1 = 0, x2 = 0;
            switch (tokens[i].charAt(0)){
                case '+':
                    x1 = stk.pop();
                    x2 = stk.pop();
                    stk.push(x2 + x1);
                    break;
                case '-':
                    x1 = stk.pop();
                    x2 = stk.pop();
                    stk.push(x2 - x1);
                    break;
                case '*':
                    x1 = stk.pop();
                    x2 = stk.pop();
                    stk.push(x1 * x2);
                    break;
                case '/':
                    x1 = stk.pop();
                    x2 = stk.pop();
                    stk.push(x2 / x1);
                    break;
                default:
                    break;
            }
        }
        if(stk.isEmpty()){
            throw new IllegalArgumentException("invalid argument");
        }
        return stk.peek();
    }
    /* unit test code is in ../java_unittest/evalRPN_junit  */
}
