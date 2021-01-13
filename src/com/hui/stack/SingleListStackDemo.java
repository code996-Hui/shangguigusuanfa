package com.hui.stack;

import java.util.Scanner;

/**
 * @author 姚晶辉
 * @Description TODO
 * @date 2020/12/30 11:31
 * @Version 1.0
 */
public class SingleListStackDemo {
    public static void main(String[] args) {

        //先创建一个栈对象
        SingleListStack stack = new SingleListStack();
        String key = "";
        boolean loop = true;//控制是否退出
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show：显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：添加数据到栈");
            System.out.println("pop：取出数据");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个值");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    System.out.println("出战的数据是");
                    stack.pop();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");


    }



}
//创建栈结构
class  SingleListStack {
    public Node top=null;  //数组，模拟栈，数据就放在里面


    //栈空
    public boolean isEmpty() {
        return top==null;
    }


    //入栈
    public void push(int value) {
        //先判断栈是否满
        if (isEmpty()) {
            top=new Node(value);
        }
        else{
            Node xin=new Node(value);
            xin.next=top;
            top=xin;
        }
    }


    //出栈 将栈顶的数据返回
    public void pop() {
        if(top!=null){
            System.out.println(top.value);
            top=top.next;
        } else {
            System.out.println("meiyou");
        }
    }


    //遍历 需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }

        Node temp=top;
        while (true){
            if(temp.next==null){ //仅有一个元素的时候
                System.out.println(temp.value);
                break;
            }
            System.out.println(temp.value);
            temp=temp.next;
        }
    }
}

class Node{
    int value;
    Node next;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node(int value) {
        this.value = value;
    }
}
