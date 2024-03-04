package dsa.stacksDSA;

import java.util.Comparator;
import java.util.stream.Stream;

public class Stack {
    private final int Max = 1000;
    private int[] a = new int[Max];
    int top;

    Stack(){
        top=-1;
    }

    boolean isEmpty(){
        return top<0;
    }

    boolean push(int val){
        if(top>(Max-1)){
            System.out.println("Stack Overflow");
            return false;
        }else{
            a[++top] = val;
            System.out.println("pushed: "+val);
            return true;
        }
    }

    int pop(){
        if(top<0){
            System.out.println("No elements pop!");
            return -1;
        }else{
            int last = a[top--];
            return last;
        }
    }

    int peek(){
        if(top<0){
            System.out.println("No elements to peek!");
            return -1;
        }else{
            return a[top];
        }
    }

    void print(){
        for(int i=top;i>-1;i--){
            System.out.format("%d, ", a[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Stack stack=new Stack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.print();
        System.out.println(stack.pop());
        stack.print();
        System.out.println(stack.pop());
        stack.print();
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());

    }
}
