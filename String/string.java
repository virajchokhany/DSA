public class string
{
    public static String removeDuplicates(String s)
    {
        String ans="";
        int i=0;
        while(i<s.length())
        {
            int j=i;
            while(j<s.length() && s.charAt(i)==s.charAt(j))
            {
                j++;
            }
            ans+=s.charAt(i);
            i=j;
        }
        return ans;
    }

    public static void main(String[] args) 
    {
        System.out.println(removeDuplicates("aabbbccddeeeeef"));
    }
}