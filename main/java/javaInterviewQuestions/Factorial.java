package javaInterviewQuestions;
public class Factorial {
    public static int factorial(int num){
        int fact = 1;
        if (num == 0)
            return 1;
        for (int i = 1 ; i <= num ; i++){
            fact = fact*i;
        }
        return fact;
    }
    public static void main(String[] args) {
        factorial(123);
    }
}
