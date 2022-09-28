package javaInterviewQuestions;
public class SwapTwoString {
    public static void main(String[] args) {
        String a = "hello";
        String b = "world";

        //Append first
        a = a+b;
        System.out.println(a);
        b = a.substring(0 , a.length()-b.length());
        System.out.println(b);

        //Store string b in string a
        a = a.substring(b.length());
        System.out.println(a);

        //print the output in console
        System.out.println(a);
        System.out.println(b);
    }
}
