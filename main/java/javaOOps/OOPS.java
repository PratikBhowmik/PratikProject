package javaOOps;
public class OOPS {
    String colour;
    String type;

    public void pen(){
        System.out.println(this.colour);
        System.out.println(this.type);
    }

    public static void main(String[] args) {
        OOPS op1 = new OOPS();
        OOPS op2 = new OOPS();
        op1.pen();
        op2.pen();

    }
}
