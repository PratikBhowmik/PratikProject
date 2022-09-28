package javaInterviewQuestions;
import java.util.HashSet;
public class LinkedList
{
   /* public class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
            next = null;
        }
    }*/

    public static int countDuplicatesInALinkedList(ListNode head) {
        int count = 0;
        HashSet<Integer> temp = new HashSet<Integer>();
        while (head != null) {
            if (temp.contains(head.val)) {
                count++;
            } else {
                temp.add(head.val);
            }
            head = head.next;
        }
        return count;
    }

    public static void main(String[] args) {
    }
}
