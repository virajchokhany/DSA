import java.util.*;

public class questions {
    
    private class ListNode
    {
        int val;
        ListNode next;
        ListNode()
        {

        }
        ListNode(int val)
        {
            this.val=val;
        }
        ListNode(int val,ListNode next)
        {
            this.val=val;
            this.next=next;
        }
    }

    // LEETCODE 876
    public ListNode midNode(ListNode head)
    {
        if(head==null || head.next==null)
            return head;
        ListNode slow=head,fast=head;
        while(fast.next!=null && fast.next.next!=null)
        {
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    // LEETCODE 19
    public ListNode removeNthFromEnd(ListNode head, int n) 
    {
        if(head==null)
            return head;
        else if(head.next==null && n==1)
            return null;
        ListNode dummy=new ListNode();
        dummy.next=head;
        ListNode slow=dummy,fast=dummy;
        for(int i=0;i<=n;i++)
        {
            fast=fast.next;
        }
        while(fast!=null)
        {
            fast=fast.next;
            slow=slow.next;
        }
        slow.next=slow.next.next;
        return dummy.next;
    }

    public ListNode oddEven(ListNode head)
    {
        ListNode oh=new ListNode();
        ListNode eh=new ListNode();
        ListNode ot=oh,et=eh;

        ListNode curr=head;

        while(curr!=null)
        {
            if(curr.val%2!=0)
            {
                ot.next=curr;
                ot=curr;
            }
            else
            {
                et.next=curr;
                et=curr;
            }
            curr=curr.next;
        }

        ot.next=eh.next;
        et.next=null;
        return oh.next;

    }
    
}
