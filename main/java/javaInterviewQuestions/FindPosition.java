package javaInterviewQuestions;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.Scanner;
public class FindPosition {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int x = sc.nextInt();
        int result = findElement(n, arr, x);
        System.out.println(result);
    }
    static int findElement(int n, int[] arr, int x)
    {
        return 0;
    }
}
