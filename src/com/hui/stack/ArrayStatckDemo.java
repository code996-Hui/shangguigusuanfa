package com.hui.stack;

import java.util.Scanner;

/**
 * @author 姚晶辉
 * @Description TODO
 * @date 2020/12/29 15:47
 * @Version 1.0
 */
public class ArrayStatckDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        boolean loop = true;
        String key = "";
        Scanner sc = new Scanner(System.in);
        while (loop) {
            System.out.println("show  遍历");
            System.out.println("exit 退出");
            System.out.println("push 压栈");
            System.out.println("pop 弹栈");
            System.out.println("请输入你的选择");
            key = sc.next();
            switch (key) {
                case "show":
                    try {
                        stack.show();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    sc.close();
                    loop = false;

                    break;
                case "push":
                    try {
                        System.out.println("请输入一个数字");
                        int val = sc.nextInt();
                        stack.push(val);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "pop":
                    try {
                        System.out.printf("弹出的为:%d\n", stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                default:
                    break;
            }
        }

    }
}

class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    //遍历栈
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("空的，不能遍历");
        }
        for (int i = top; i>= 0; i--) {
            System.out.printf("stack[%d]:%d\n", i, stack[i]);
        }
    }


    //构造器
    public ArrayStack(int maxSize) {
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
}