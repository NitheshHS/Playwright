package dsa.stacksDSA;

public class StackLinked {
    private StackNode root;
    static class StackNode{
        private int data;
        private StackNode next;
        StackNode(int data){
            this.data=data;
        }
    }

    public boolean isEmpty(){
        return root==null;
    }

    public void push(int data){
        if(root == null){
            root = new StackNode(data);
        }else{
            StackNode temp=root;
            root = new StackNode(data);
            root.next = temp;
        }
        System.out.println("pushed: "+data);
    }

    public int pop(){
        int poppedElement = -1;
        if(root==null){
            System.out.println("Stack elements empty!");
        }else{
            poppedElement = root.data;
            root = root.next;
            System.out.println("Popped element: "+poppedElement);
        }
        return poppedElement;
    }

    public void print(){
        if(root==null){
            System.out.println("Empty stack!");
        }else{
            StackNode temp=root;
            while(root!=null){
                System.out.format("%d, ", root.data);
                root=root.next;
            }
            root = temp;
            System.out.println();
        }
    }

    public int peek(){
        if(root==null){
            System.out.println("Empty Stack!");
        }else{
            System.out.println("current stack node: "+root.data);
            return root.data;
        }
        return -1;
    }

    public static void main(String[] args) {
        StackLinked linked=new StackLinked();
        linked.push(10);
        linked.push(20);
        linked.push(30);
        linked.push(40);
        linked.push(50);
        linked.print();
        linked.pop();
        linked.print();
        linked.pop();
        linked.print();
        System.out.println(linked.peek());
    }
}
