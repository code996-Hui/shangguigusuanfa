package com.hui.stack;

/**
 * @author 姚晶辉
 * @Description TODO
 * @date 2020/12/30 13:16
 * @Version 1.0
 */
public class Calculator {

    public static void main(String[] args) {
        String expression = "5*5-7-3/3+8+5*6";
        //String expression = "100+10";
        //创建两个栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义相关的变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        String keepnum="";
        char ch = ' ';//将每次扫描到的char保存到ch
        while (true) {
            //一次得到每个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch)){//如果是运算符
                //判断当前符号栈是否为空
                if (!operStack.isEmpty()){
                    //符号栈不为空，进行操作符优先级比较，如果当前的操作符的优先级小于或者等于栈中的优先级。
                    //pop出优先级高的操作符
                    //需要pop出两个数进行运算，将得到结果压入然后将当前操作符压入
                    if (operStack.priority(ch)<=operStack.priority(operStack.peek())){
                       //优先级小
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();
                        res= numStack.cal(num1, num2, oper);
                        numStack.push(res);
//                        operStack.push(ch);
                        continue;
                    }else {

                        operStack.push(ch);
                    }

                }else {
                    //为空直接入栈
                    operStack.push(ch);
                }

            }else {




                //如果是数,应该进行多位数判断，再次往后扫描
                keepnum+=ch;
                if (index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepnum));
                }else {
                    //判断下一个是不是数字
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                         //如果下一位是运算符
                        numStack.push(Integer.parseInt(keepnum));
                        keepnum="";
                    }
                }
//                numStack.push(ch-48);
            }
            //让index+1，并判断是否为最后
            index++;
            if (index>=expression.length()){
                break;
            }
        }
        //表达式扫描完毕，依次弹栈并计算
        while (true){
            if (operStack.isEmpty()){
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res=numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.printf("表达式%s的计算结果为：%d",expression,numStack.pop());
        System.out.println(numStack.isEmpty());
    }

}

class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    //遍历栈
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("空的，不能遍历");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]:%d\n", i, stack[i]);
        }
    }
    //查看栈顶元素
    public int peek(){
        return stack[top];
    }


    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int val) {
        if (isFull()) {
            throw new RuntimeException("满了");
        }
        top++;
        stack[top] = val;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("空的");
        }
        int val = stack[top];
        top--;
        return val;
    }


    /**
     * 返回运算符的优先级，使用数字表示，数字越大，越高
     *
     * @param oper 操作符，操作数
     * @return
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//假设只有加减
        }
    }

    /**
     * 判断是不是一个运算符
     *
     * @param val 操作符
     * @return
     */
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}