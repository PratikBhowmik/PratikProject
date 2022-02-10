package dataStructuresAndAlgorithm;
public class StackArray {
    int size;
    int arr[];
    int top;
    StackArray(int size){
        this.size = size;
        this.arr = new int [size];
        this.top = -1;
    }
    public void push(int element){
        if (!isFull()){
            top++;
            arr[top] = element;
            System.out.println("pushed element "+ element);
        }else {
            System.out.println("Stack is full now");
        }
    }
    public int pop(){
        if (!isEmpty()){
            int returnedTop = top;
            top--;
            System.out.println("popped element "+arr[returnedTop]);
            return arr[returnedTop];
        }else {
            System.out.println("Stack is empty");
            return -1;
        }
    }
    public int peek() {
        if (!this.isEmpty()) {
            return arr[top];
        }else {
            System.out.println("Stack is empty");
            return -1;
        }
    }
    public boolean isEmpty(){
        return (top == -1);
    }
    public boolean isFull(){
        return (size - 1 == top);
    }

    public static void main(String[] args) {
        StackArray sa = new StackArray(10);
        sa.pop();
        sa.push(100);
        sa.push(200);
        sa.push(300);
        sa.push(400);
        sa.pop();
        sa.pop();
        sa.pop();
        System.out.println(sa.peek());
    }
}
