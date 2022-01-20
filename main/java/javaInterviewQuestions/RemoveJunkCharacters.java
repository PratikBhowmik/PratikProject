package javaInterviewQuestions;
public class RemoveJunkCharacters {
    public static void main(String[] args) {

        String s = "t50mt50tkto50tt55&&&((())))____2222$$$$$$$%%%%%";
        s = s.replaceAll("[a-zA-Z0-9]" , "");
        System.out.println(s);

    }
}
