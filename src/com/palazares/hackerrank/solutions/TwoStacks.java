package com.palazares.hackerrank.solutions;

import java.util.Scanner;
import java.util.Stack;

public class TwoStacks {
    private static class MyQueue<T> {
        private Stack<T> enqueue = new Stack<>();
        private Stack<T> dequeue = new Stack<>();

        void enqueue(T elem){
            enqueue.push(elem);
        }

        T dequeue(){
            if(!dequeue.empty()){
                return dequeue.pop();
            }
            while(!enqueue.empty()){
                dequeue.push(enqueue.pop());
            }
            return dequeue.pop();
        }

        T peek(){
            if(!dequeue.empty()){
                return dequeue.peek();
            }
            while(!enqueue.empty()){
                dequeue.push(enqueue.pop());
            }
            return dequeue.peek();
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}
