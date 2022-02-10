package javaInterviewQuestions;

import java.util.Scanner;

public class FindVowels
{

    public static void main(String[] args)
    {

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int vowels = countVowels(str);
        System.out.println(vowels);
    }

    static int countVowels(String str)
    {
        int noofvowel = 0;
        for (char c: str.toCharArray())
        {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'){
                noofvowel++;
            }
        }
        return noofvowel;
    }
}
