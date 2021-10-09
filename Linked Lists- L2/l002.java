
public class l002 
{
    public static class ListNode
    {
        int val;
        ListNode next;
        ListNode(int val)
        {
            this.val=val;
        }
    }    

    public static boolean isCyclePresent(ListNode head)
    {
        if(head==null || head.next==null)
            return false;
        
        ListNode slow=head,fast=head;
        while(fast!=null && fast.next!=null)
        {
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)
                return true;
        }
        return false;
    }

    public static ListNode startPointOfCycle(ListNode head)
    {
        if(head==null || head.next==null)
            return null;
        
        ListNode slow=head,fast=head;

        boolean isCycle=false;

        while(fast!=null && fast.next!=null)
        {
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)
            {
                isCycle=true;
                break;
            }
        }

        if(isCycle==false)
        {
            return null;
        }
        else
        {
            slow=head;
            while(slow!=fast)
            {
                slow=slow.next;
                fast=fast.next;
            }
            return slow;
        }
    }


    public ListNode getIntersectionNode(ListNode A, ListNode B) 
    {
        if(A==null || B==null)
            return null;
        ListNode curr=A;
        while(curr.next!=null)
        {
            curr=curr.next;
        }
        curr.next=B;
        ListNode ans=startPointOfCycle(A);
        curr.next=null;
        return ans;
    }


    public static void length(ListNode head)
    {
        if(head==null || head.next==null)
            return;

        ListNode slow=head,fast=head;
        while(fast!=null && fast.next!=null)
        {
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)
                break;
        }

        if(fast==null)
            return;
        
        int lengthA=0,lengthB=0,lengthC=0,lengthBPlusC=0,m=0;

        slow=head;
        
        ListNode fast_copy=fast;

        while(slow!=fast)
        {
            slow=slow.next;
            fast=fast.next;
            lengthA++;
            if(fast==fast_copy)
                m++;
        }
        slow=fast=fast_copy;

        do
        {
            slow=slow.next;
            lengthBPlusC++;
        } while(slow!=fast);
        

        if(lengthA!=0 && lengthA%lengthBPlusC==0)   ///  when slow doesnt enter the loop
        {
            m--;
            lengthB=lengthBPlusC;
        }
        else                    // when tail length is 0 and for normal case
        {
            m++;
            lengthC=lengthA-(m-1)*lengthBPlusC;
            lengthB=lengthBPlusC-lengthC;
        }

        System.out.println("Length of A "+lengthA+" length of B "+lengthB+" length of C "+lengthC+" length of B+C "+lengthBPlusC);
    }

}
