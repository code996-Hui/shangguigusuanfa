package com.hui.linkedlist;

/**
 * @author 姚晶辉
 * @Description 约瑟夫问题，环形链表，也可用数组取模来处理
 * @date 2020/12/22 15:04
 * @Version 1.0
 */
public class Josepfu {

    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.list();
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点，当年没有编号
    private Boy first = null;

    //添加小孩节点构成环形
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums值不正确");
            return;
        }
        Boy curBoy = null;//辅助变量

        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(boy);
                curBoy = boy;
                continue;
            }
            curBoy.setNext(boy);
            curBoy = boy;
            curBoy.setNext(first);
        }
    }

    //遍历环形链表
    /*
    * 1.curNode指向第一个头
    * 2.next！=first，一直遍历输出
    * */
    public void list(){
        //判断是否为空
        if (first==null){
            System.out.println("当前链表为空");
        }
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号为%d\n",curBoy.getNo());
            curBoy = curBoy.getNext();
            if (curBoy==first){
                break;
            }
        }
    }

    //解决约瑟夫出圈问题

    /**
     *
     * @param startNo 从那个编号开始
     * @param countNum 数几个数出列
     * @param nums 一共有几个人
     */
    public void countBoy(int startNo,int countNum,int nums){
        //校验数据
        if (startNo>nums||startNo<1||first==null){
            System.out.println("数据有误，请重新输入");
            return;
        }

        //创建辅助指针,指向起始节点的前一个节点
//        Boy helper =first;
//        while (true){
//            if (helper.getNext()==first) return;
//            helper = helper.getNext();
//        }
        //创建辅助指针,指向起始节点的前一个节点
        Boy helper =null;
        while (true){
            if (first.getNext().getNo()==startNo){
                helper=first;
                first=first.getNext();
                break;
            }
            first=first.getNext();
        }
        //开始出圈
        //逻辑清楚，再写代码
        while (true){
            if (first==helper) {
                System.out.printf("最后一个为%d\n",first.getNo());
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                helper=first;
                first=first.getNext();
            }
            System.out.printf("出圈节点为%d\n",first.getNo());
            first=first.getNext();
            helper.setNext(first);
        }



    }
}


//创建一个boy类，表示节点类型
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
