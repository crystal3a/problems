/*
 * given a set of candidate numbers(C) and a target number(T), find all unique combinations where the candidate numbers sums to T
 * the same repeated number may be chosen from C unlimited number of times.
 * note:
 * 1.all numbers(including target) will be positive integers
 * 2.elements in a combination must be non-decending order
 * 3.solution set must not contain duplicate combinations.
 *
 * test data:
 * {2,3,6,7}, target=7, 
 * result: {7}, {2,2,3}
 * */
import java.io.*;
import java.util.*;

public class combinationsum{
    private ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    private Stack<Integer> stk = new Stack<Integer>();        //put candidate value
    
    private void plusSum(int[] candidates, int target){
        if(target == 0){
            res.add(new ArrayList<Integer>(stk));
            return;
        }
        for(int d : candidates){
            if(target < d)        break;
            if(!stk.isEmpty() && stk.peek() > d)        continue;
            stk.push(d);
            plusSum(candidates, target - d);
            stk.pop();
        }
        return;
    }

    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target){
        Arrays.sort(candidates); // sort in ascending order
        res.clear();
        stk.clear();
        plusSum(candidates, target);
        return res;
    }

    public void displayArrArr(ArrayList<ArrayList<Integer>> combinations){
        System.out.println("combinations:");
        for(ArrayList<Integer> arr : combinations){
            for(Integer i : arr){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public void test_01(){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("please input integer candidates:");
            String str = scan.nextLine().trim();
            if(str.isEmpty())    break;
            StringTokenizer t = new StringTokenizer(str, " ,");
            int[] arr = new int[t.countTokens()];
            int i=0;
            while(t.hasMoreTokens()){
                arr[i++] = Integer.parseInt(t.nextToken().toString());
            }
            
            System.out.println("please input integer target:");
            str = scan.nextLine().trim();
            if(str.isEmpty())    break;
            int x = Integer.parseInt(str);
            displayArrArr(combinationSum(arr, x));
        }
    }

    public static void main(String[] args){
        combinationsum cs = new combinationsum();
        cs.test_01();
        return;
    }
}
