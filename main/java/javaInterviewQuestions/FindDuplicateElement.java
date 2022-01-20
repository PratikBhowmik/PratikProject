package javaInterviewQuestions;

import java.util.HashSet;
import java.util.Set;

public class FindDuplicateElement {
    public static void main(String[] args) {

        String[] names = {"Java", "Python", "C", "Java", "Ruby", "C++"};

        for (int i = 0; i <= names.length; i++) {
            for (int j = i + 1; j <= names.length; j++) {
                if (names[i].equals(names[j])) {
                    System.out.println(names[i]);
                }
            }
        }


        Set<String> set = new HashSet<String>();
        for (String name : names) {
            if (set.add(name) == false) {
                System.out.println(name);
            }
        }


    }
}
