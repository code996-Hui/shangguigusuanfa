package com.hui.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author 姚晶辉
 * @Description TODO
 * @date 2021/1/6 17:51
 * @Version 1.0
 */
public class PolandNotation {
    public static void main(String[] args) {
        //  完成中缀表达式转后缀表达式
        //说明：1 因为扫描字符串，直接对str操作不方便，因此现将中缀表达式转成对应的中缀表达式的list
        String expression = "1+((2+3)*4)-5";
        List<String> list = toInfixExpression(expression);
        System.out.println(list);






        //先定义逆波兰表达式
        //（3+4）*5-6=》34+5*6-
//        String suffixExpression="30 4 + 5 * 6 -";
        String suffixExpression="4 5 * 8 - 60 + 8 2 / +";
        //1.现将后缀表达式切割放到ArrayList中
     // List<String> rpnList= getListString(suffixExpression);
        //System.out.println(rpnList);
        //完成对逆波兰表达式的计算
       // int res = calculater(rpnList);
       // System.out.println(res);
    }

    private static List<String> toInfixExpression(String expression) {
        ArrayList<String> list = new ArrayList<>();
        String str;//用来拼接多位数
        char ch;
        for (int i = 0; i < expression.length(); i++) {
            ch=expression.charAt(i);
            //如果ch是一个非数字
             if (ch<48||ch>57){
                 list.add(ch+"");
             }else {
                 str="";

                 while (i<expression.length()&&( ch=expression.charAt(i))>=48&&( ch=expression.charAt(i))<=57){

                     str+=ch;
                     i++;

                 }
                 list.add(str);
                 if (i<expression.length()) i--;
             }

        }
        return list;
    }

    private static int calculater(List<String> rpnList) {
        //创建一个栈
        Stack<String> stack = new Stack<>();
        //遍历逆波兰表达式集合
        for (String item : rpnList) {
            //使用正则表达式来取数
            if (item.matches("\\d+")){//匹配的是多位数子
                //入栈
                stack.push(item);
            }else {
                //pop出两个数进行计算
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")){
                    res=num1+num2;
                }else if (item.equals("-")){
                    res=num2-num1;
                }else if (item.equals("*")){
                    res=num1*num2;
                }else if (item.equals("/")){
                    res = num2/num1;
                }else {
                    throw new RuntimeException("运算符有错误");
                }
                //把运算结果压入栈
                stack.push(res+"");

            }
        }



        return  Integer.parseInt(stack.pop());
    }

    private static List<String> getListString(String suffixExpression) {
        ArrayList<String> list = new ArrayList<>();
        String[] split = suffixExpression.split(" ");
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }


}
