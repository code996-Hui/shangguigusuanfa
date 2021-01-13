package com.hui;

/**
 * @author 姚晶辉
 * @Description TODO
 * @date 2021/1/11 17:25
 * @Version 1.0
 */
public class SwitchString {
    public static void main(String[] args) {
        method(null);
    }
    public static void method(String param) {
        //不进行null处理，会抛异常
        switch (param) {
            // 肯定不是进入这里
            case "sth":
                System.out.println("it's sth");
                break;
            // 也不是进入这里
            case "null":
                System.out.println("it's null");
                break;
            // 也不是进入这里
            default:
                System.out.println("default");
        }
    } }
