package com.hui.queue;

import java.util.ArrayList;

/**
 * @author 姚晶辉
 * @Description TODO
 * @date 2020/12/10 14:26
 * @Version 1.0
 */
public class CircleArrayQueue {
    private int maxSize;//数组的最大容量
    private int front;//队列头，指向队列的第一个元素，初始值为0
    private int rear;//队列尾，指向最后一个元素位置+1，初始值为0，留出一个空间最为队空的约定
    private int[] data;//数组用于存放队列

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
        front = 0;//默认也是0
        rear = 0;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满");
            return;
        }
        data[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        int value = data[front];
        front = (front + 1) % 10;
        return value;
    }

    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, data[i % maxSize]);
        }

    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }


    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return data[front];
    }


}
