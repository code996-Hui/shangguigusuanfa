package com.hui.linkedlist;

/**
 * @author 姚晶辉
 * @Description TODO
 * @date 2020/12/21 18:05
 * @Version 1.0
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode heroNode5 = new HeroNode(5, "李逵", "壮士");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode4);
        doubleLinkedList.add(heroNode5);

        doubleLinkedList.list();
        //修改节点
        System.out.println("修改测试");
        HeroNode newH = new HeroNode(4, "公孙胜", "如玉龙");
        doubleLinkedList.update(newH);
        doubleLinkedList.list();
        System.out.println("删除测试");
        doubleLinkedList.del(4);
        doubleLinkedList.list();
    }

}

class DoubleLinkedList {
    //初始化一个头结点，头结点不懂，不指向任何数据
    private HeroNode head = new HeroNode(0, "", "");

    //返回头结点
    public HeroNode getHead() {
        return head;
    }

    //遍历双向链表
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;//指向第一个有数据的节点
        while (true) {
            //到最后一个节点
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;

        }
    }

    //添加链表到最后一个节点
    public void add(HeroNode heroNode) {
        //头结点不能动，定义一个辅助节点
        HeroNode temp = head;
        while (true) {
            //一直遍历，直到找到节点末尾
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }


    //修改链表中的节点
    public void update(HeroNode newHeroNode) {
        //判断此时链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，修改失败");
        }
        //找到对应的节点
        HeroNode temp = head.next;
        Boolean flag = false;
        while (true) {
            if (temp == null) break;
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
//            temp.pre.next=newHeroNode;
//            temp.next.pre=newHeroNode;
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到编号为%d的节点\n", newHeroNode.no);
        }
    }

    //删除链表中的节点
    public void del(int no) {
        //判断此时链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，删除失败");
        }
        //找到对应的节点
        HeroNode temp = head.next;
        Boolean flag = false;
        while (true) {
            if (temp == null) break;
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }


        } else {
            System.out.printf("没有找到编号为%d的节点\n", no);
        }
    }

}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;
    public HeroNode pre;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +
                '}';
    }
}