public class LLNode
{
    Object item;
    LLNode next;
    public LLNode(Object item)
    {

        this.item = item;
        next = null;
    }

    public int HashCode()
    {
        return item.hashCode();
    }

    @Override
    public int hashCode()
    {
        return item.hashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if(other == null)
            return false;
        else
        {
            if (other instanceof LLNode)
            {
                LLNode temp = (LLNode) other;
                return temp.item.equals(item);
            }
        }
        return false;
    }

    public static void main (String args[])
    {
        LLNode x;
    }


}
