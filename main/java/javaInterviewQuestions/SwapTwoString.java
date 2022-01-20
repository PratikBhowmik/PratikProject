package javaInterviewQuestions;
public class SwapTwoString {
    public static void main(String[] args) {
        String a = "hello";
        String b = "world";
        //Append first
        a = a+b;
        b = a.substring(0 , a.length()-b.length());
        //Store string b in string a
        a = a.substring(b.length());
        System.out.println("The value of a and b after swapping");
        System.out.println(a);
        System.out.println(b);
    }
}
