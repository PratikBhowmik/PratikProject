package javaInterviewQuestions;
public class ReverseString {
    public static void main(String[] args) {
        //1st method
        String s = "selenium";
        int len = s.length();
        String rev = "";
        for(int i = len - 1 ; i>=0 ; i--){
            rev = rev + s.charAt(i);
        }
        System.out.println(rev);




        //2nd method
        StringBuffer sb = new StringBuffer(s);
        System.out.println(sb.reverse());
    }
}
