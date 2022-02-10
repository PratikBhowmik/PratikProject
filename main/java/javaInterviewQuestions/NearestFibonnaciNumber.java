package javaInterviewQuestions;

import java.util.Scanner;

public class NearestFibonnaciNumber {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-->0) {
            int n = sc.nextInt();
            int result = largestFibonacciNumber(n);
            System.out.println(result);
        }
        sc.close();
    }

    static int largestFibonacciNumber(int n){
        if (n==0){
            System.out.println(0);
        }
        int first = 0 , second = 1;
        int third = first+second;

        while (third <= n){
            first = second;
            second = third ;
            third = first + second;
        }
        int ans = Math.abs(third - n) >= Math.abs(second - n) ? second:third;
        return ans;
    }
}
