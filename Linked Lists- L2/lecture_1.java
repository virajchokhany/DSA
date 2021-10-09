
import java.util.*;

class lecture_1
{
    public static class ListNode
    {
        int val=0;
        ListNode next=null;
        ListNode random=null;
        ListNode(int val)
        {
            this.val=val;
        }
    }

    public static ListNode midNode(ListNode head)
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

    public static ListNode reverse(ListNode head)
    {
        if(head==null || head.next==null)
            return head;
        ListNode curr=head,prev=null,next=null;
        while(curr!=null)
        {
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }

    public static boolean isPalindrome(ListNode head)
    {
        if(head==null || head.next==null)
            return true;
        ListNode mid=midNode(head);
        ListNode nHead=mid.next;
        mid.next=null;
        nHead=reverse(nHead);
        ListNode c1=head,c2=nHead;
        boolean ans=true;
        while(c2!=null)
        {
            if(c1.val!=c2.val)
            {
                ans=false;
                break;
            }
            c1=c1.next;
            c2=c2.next;
        }
        nHead=reverse(nHead);
        mid.next=nHead;
        return ans;
    }

    public static void fold(ListNode head)
    {
        if(head==null || head.next==null)
            return;
        
        ListNode mid=midNode(head);
        ListNode nHead=mid.next;
        mid.next=null;
        nHead=reverse(nHead);
        ListNode c1=head,c2=nHead,f1=null,f2=null;

        while(c2!=null)
        {
            f1=c1.next;
            f2=c2.next;
            c1.next=c2;
            c2.next=f1;
            c1=f1;
            c2=f2;
        }
    }

    public static void unfold(ListNode head)
    {
        if(head==null || head.next==null)
            return;
        
        ListNode c1=head,c2=head.next,x=c2;

        while(c2!=null && c2.next!=null)
        {
            c1.next=c2.next;
            c1=c1.next;
            c2.next=c1.next;
            c2=c2.next;
        }
        c1.next=null;
        x=reverse(x);
        c1.next=x;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) 
    {
        ListNode dummy=new ListNode(-1);
        ListNode prev=dummy,c1=l1,c2=l2;
        while(c1!=null && c2!=null)
        {
            if(c1.val<c2.val)
            {
                prev.next=c1;
                c1=c1.next;
            }
            else
            {
                prev.next=c2;
                c2=c2.next;
            }
            prev=prev.next;
        }
        if(c1!=null)
            prev.next=c1;
        else
            prev.next=c2;
        return dummy.next;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) 
    {
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode slow=dummy,fast=dummy;
        for(int i=1;i<=n+1;i++)
            fast=fast.next;
        while(fast!=null)
        {
            slow=slow.next;
            fast=fast.next;
        }
        ListNode rn=slow.next;
        slow.next=rn.next;
        rn.next=null;
        return dummy.next;
    }

    public static ListNode segregateEvenOdd(ListNode head) 
    {
        if(head==null || head.next==null)
            return head;
        ListNode even=new ListNode(-1);
        ListNode odd=new ListNode(-1);
        ListNode ep=even,op=odd,curr=head;
        while(curr!=null)
        {
            if((curr.val&1)!=0) // odd
            {
                op.next=curr;
                op=op.next;
            }
            else
            {
                ep.next=curr;
                ep=ep.next;
            }
            curr=curr.next;
        }
        op.next=null;
        ep.next=odd.next;
        return even.next;
    }

    public static ListNode segregate01(ListNode head) 
    {
        if(head==null || head.next==null)
            return head;
        ListNode zero=new ListNode(-1);
        ListNode one=new ListNode(-1);
        ListNode zp=zero,op=one;
        ListNode curr=head;
        while(curr!=null)
        {
            if(curr.val==0)
            {
                zp.next=curr;
                zp=zp.next;
            }
            else
            {
                op.next=curr;
                op=op.next;
            }
            curr=curr.next;
        }
        zp.next=one.next;
        return zero.next;
    }

    public static ListNode segregate012(ListNode head) 
    {
        if(head==null || head.next==null)
            return head;
        
        ListNode zero=new ListNode(-1);
        ListNode one=new ListNode(-1);
        ListNode two=new ListNode(-1);
        ListNode zp=zero,op=one,tp=two,curr=head;
        while(curr!=null)
        {
            int val=curr.val;
            if(val==0)
            {
                zp.next=curr;
                zp=zp.next;
            }
            else if(val==1)
            {
                op.next=curr;
                op=op.next;
            }
            else
            {
                tp.next=curr;
                tp=tp.next;
            }
            curr=curr.next;
        }
        tp.next=null;
        op.next=two.next;
        zp.next=one.next;
        return zero.next;
    }

    public static ListNode mergeSort(ListNode head) 
    {
        if(head==null || head.next==null)
            return head;
        ListNode mid=midNode(head);
        ListNode nhead=mid.next;
        mid.next=null;
        head=mergeSort(head);
        nhead=mergeSort(nhead);
        return mergeTwoLists(head, nhead);
    }

    public static ListNode mergeKLists(ListNode[] lists) 
    {
        PriorityQueue<ListNode> pq=new PriorityQueue<>((a,b)->{return a.val-b.val;});
        for(int i=0;i<lists.length;i++)
        {
           if(lists[i]!=null)
                pq.add(lists[i]);
        }
        ListNode dummy=new ListNode(-1);
        ListNode prev=dummy;
        while(pq.size()>0)
        {
            ListNode node=pq.remove();
            prev.next=node;
            prev=prev.next;
            if(node.next!=null)
                pq.add(node.next);
        }
        return dummy.next;
    }


    // USING DIVIDE AND CONQUER
    public static ListNode mergeKLists_01_helper(ListNode lists[],int lo,int hi)
    {
        if(lo==hi)
            return lists[lo];
        else if(lo>hi)
            return null;

        int mid=lo+(hi-lo)/2;
        ListNode c1=mergeKLists_01_helper(lists,lo,mid);
        ListNode c2=mergeKLists_01_helper(lists,mid+1,hi);
        return mergeTwoLists(c1, c2);
    } 
    public static ListNode mergeKLists_01(ListNode[] lists) 
    {
        int n=lists.length;
        return mergeKLists_01_helper(lists,0,n-1);
    }
    
    private static ListNode tt=null,th=null;
    private static void addFirst(ListNode node)
    {
        if(th==null)
        {
            th=tt=node;
        }
        else
        {
            node.next=th;
            th=node;
        }
    }
    
    private static int size(ListNode head)
    {
        ListNode curr=head;
        int count=0;
        while(curr!=null)
        {
            curr=curr.next;
            count++;
        }
        return count;
    }
    
    public static ListNode reverseInKGroup(ListNode head, int k) 
    {
        int length=size(head);
        if(k>length || k==0)
            return head;
        ListNode oh=null,ot=null;
        ListNode curr=head;

        while(length>=k)
        {
            int times=k;

            while(times-->0)
            {
                ListNode forw=curr.next;
                curr.next=null;
                addFirst(curr);
                curr=forw;
            }

            if(oh==null)
            {
                oh=th;
                ot=tt;
            }
            else
            {
                ot.next=th;
                ot=tt;
            }
            th=tt=null;
            length-=k;
        }

        ot.next=curr;
        return oh;
    }

    public static ListNode reverseInRange(ListNode head, int n, int m) 
    {
        if(head==null || head.next==null || n==m)
        return head;
    
        ListNode dummy=new ListNode(-10);
        dummy.next=head;
        int i=0;
        ListNode curr=dummy,next=null;
        ListNode prev=null;
        while(true)
        {
            if(i<n)
            {
                prev=curr;
            }
            else if(i>=n && i<=m)
            {
                next=curr.next;
                curr.next=null;
                addFirst(curr);
                curr=next;
                i++;
                continue;
            }
            else
                break;
            i++;
            curr=curr.next;
        }
        prev.next=th;
        tt.next=curr;
        return dummy.next;
    }

    public static ListNode removeDuplicates(ListNode head) 
    {
        if(head==null || head.next==null)
            return head;
        
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode slow=dummy,fast=dummy;

        while(fast!=null)
        {
            if(slow.val==fast.val)
            {
                fast=fast.next;
            }
            else
            {
                ListNode next=slow.next;
                while(next!=fast)
                {
                    ListNode nnext=next.next;
                    next.next=null;
                    next=nnext;
                }
                slow.next=fast;
                slow=fast;
            }
        }
        slow.next=null;
        return dummy.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) 
    {
        l1=reverse(l1);
        l2=reverse(l2);
        ListNode c1=l1,c2=l2;
        int sum=0,carry=0;
        ListNode dummy=new ListNode(-1);
        ListNode prev=dummy;

        while(c1!=null || c2!=null || carry!=0)
        {
            int x=(c1!=null?c1.val:0)+(c2!=null?c2.val:0)+carry;
            sum=x%10;
            carry=x/10;

            ListNode node=new ListNode(sum);
            prev.next=node;
            prev=prev.next;
            if(c1!=null)
                c1=c1.next;
            if(c2!=null)
                c2=c2.next;
        }
        dummy.next=reverse(dummy.next);
        l1=reverse(l1);
        l2=reverse(l2);
        return dummy.next;
    }

    public static boolean isBiggerList(ListNode l1,ListNode l2)
    {
        int len1=size(l1);
        int len2=size(l2);
        if(len1>len2)
            return true;
        else if(len1<len2)
            return false;
        
        while(l1!=null)
        {
            if(l1.val>l2.val)
                return true;
            else if(l1.val<l2.val)
                return false;
            l1=l1.next;
            l2=l2.next;
        }
        return false;
    }
    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) 
    {
        boolean isBigger=isBiggerList(l1,l2);
        l1=reverse(l1);
        l2=reverse(l2);
        int borrow=0;
        ListNode dummy=new ListNode(-1);
        ListNode prev=dummy;
        ListNode c1=l1,c2=l2;
        if(!isBigger)
        {
            c1=l2;
            c2=l1;
        }
        while(c1!=null || c2!=null || borrow!=0)
        {
            int diff=c1.val-borrow-(c2!=null?c2.val:0);
            if(diff>=0)
            {
                ListNode node=new ListNode(diff);
                prev.next=node;
                prev=node;
                borrow=0;
            }
            else
            {
                int x=10+diff;
                ListNode node=new ListNode(x);
                prev.next=node;
                prev=node;
                borrow=1;
            }
            c1=c1.next;
            if(c2!=null)
                c2=c2.next;
        }
        dummy.next=reverse(dummy.next);
        l1=reverse(l1);
        l2=reverse(l2);

        ListNode curr=dummy.next;
        while(curr!=null && curr.val==0)
        {
            ListNode next=curr.next;
            curr.next=null;
            curr=next;
        }
        dummy.next=curr;
        if(dummy.next==null)
        {
            dummy.next=new ListNode(0);
        }
        return dummy.next;
    }

    private static ListNode multiply(ListNode head,int x)
    {
        ListNode dummy=new ListNode(-1);
        ListNode prev=dummy;
        ListNode c1=head;
        int carry=0;
        while(c1!=null || carry!=0)
        {
            int mul=(c1!=null?c1.val:0)*x+carry;
            prev.next=new ListNode(mul%10);
            prev=prev.next;
            carry=mul/10;
            if(c1!=null)
                c1=c1.next;
        }
        return dummy.next;
    }

    
    private static void addLists(ListNode ans,ListNode list)
    {
        int carry=0;
        ListNode dummy=new ListNode(-1),prev=dummy;
        while(list!=null || ans!=null || carry!=0)
        {
            int sum=(list!=null?list.val:0)+(ans!=null?ans.val:0)+carry;
            carry=sum/10;
            sum%=10;
            prev.next=new ListNode(sum);
            if(ans!=null)
                ans=ans.next;
            if(list!=null)
                list=list.next;
        }
    }
    public static ListNode multiplyTwoLL(ListNode l1, ListNode l2) 
    {
        l1=reverse(l1);
        l2=reverse(l2);
        ListNode dummy=new ListNode(-1),prev=dummy;
        ListNode c2=l2;
        
        while(c2!=null)
        {
            int x=c2.val;
            ListNode list=multiply(l1,x);
            addLists(prev.next,list);

        }

        return null;
    }

    public static ListNode copyRandomList(ListNode head) 
    {
        if(head==null)
            return null;

        ListNode curr=head;
        while(curr!=null)
        {
            ListNode next=curr.next;
            curr.next=new ListNode(curr.val);
            curr.next.next=next;
            curr=next;
        }

        curr=head;
        while(curr!=null)
        {
            ListNode random=curr.random;
            ListNode next=curr.next;
            if(random!=null)
            {
                random=random.next;
            }
            next.random=random;
            curr=curr.next.next;
        }

        curr=head;
        ListNode dummy=curr.next;
        while(curr.next!=null)
        {
            ListNode next=curr.next;
            curr.next=next.next;
            curr=next;
        }
        return dummy;
    }
    public static void main(String[] args) {
        
    }
}