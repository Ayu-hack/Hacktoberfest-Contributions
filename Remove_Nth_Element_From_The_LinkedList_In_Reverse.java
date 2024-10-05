package practice;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // Initializing two pointers slow and fast
    	//FIrst fast pointed moves n number of times and slow pointer will start when fast pointer reaches n
    	ListNode slow=head;
        ListNode fast=head;
        for(int i=0;i<n;i++)
        {
            fast=fast.next;
        }
        // If fast pointer reaches null then it means that the n is the size of the linked list hence returning from the second node.
        if(fast==null)
        {
            return head.next;
        }
        //When fast pointer is not null we move it till last element
        while(fast.next!=null)
        {
            slow=slow.next;
            fast=fast.next;
        }
        // Now pointing the slow pointer to the next of target node
        ListNode temp = slow.next;
        if(temp!=null)
        {
            slow.next=temp.next;
        }
        else
        {
            slow.next=null;
        }
        //Finally returning the head of the linked list
        return head;
    }
    public static void main(String args[])
    {
    	ListNode start = new ListNode(3);
    	ListNode check = start;
    	ListNode remove =start;
    	start.next = new ListNode(21);
    	start.next.next =  new ListNode (323);
    	start.next.next.next = new ListNode (456);
    	System.out.println("Before removing 2nd last node");
    	while(start != null)
    	{
    		System.out.print(start.val+" ");
    		start=start.next;
    	}
    	System.out.println();
    	System.out.println("After removing 2nd last node");
    	removeNthFromEnd(remove, 2);

    	while(check != null)
    	{
    		System.out.print(check.val+" ");
    		check=check.next;
    	}

    }
}
