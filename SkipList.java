import java.util.ArrayList;

public class SkipList
{
    public class SkipListNode
    {
        public int data;
        SkipListNode next, below;

        public SkipListNode(int data)
        {
            this.data = data;
            next = below = null;
        }
    }

    private ArrayList<SkipListNode> levels;
    private int size;

    public SkipList()
    {
        levels = new ArrayList<SkipListNode>();
    }

    public void insert(int item)
    {
       ArrayList<SkipListNode> holder = new ArrayList<>();
        SkipListNode current = levels.get(levels.size()-1);
        while(current != null)
        {
            if(current.data == item)
                return;
            else
            {
                if(current.next.data > item)
                {
                    holder.add(current);
                    current = current.below;
                }
                else
                    current = current.next;
            }
        }
        adjustLinks(item, holder.get(holder.size()-1), null);
        int whatDigit;
        for(int i = holder.size()-2; i >= 0; --i)
        {
            whatDigit = (int) (Math.random() * 2);
            if(whatDigit == 0)
                return;
            else
            {
                adjustLinks(item, holder.get(i), holder.get(i+1));
                holder.set(i, holder.get(i).next);
            }
        }
        whatDigit = (int) (Math.random() * 2);
        if(whatDigit == 1)
            levels.add(generateDefault(item, holder.get(0)));
    }

    private SkipListNode generateDefault(int item, SkipListNode belowLink)
    {
        SkipListNode temp = new SkipListNode(Integer.MIN_VALUE);
        temp.next = new SkipListNode(item);
        temp.next.next = new SkipListNode(Integer.MAX_VALUE);
        temp.next.below = belowLink;
        return temp;
    }


    private void adjustLinks(int item, SkipListNode change, SkipListNode belowLink)
    {
        SkipListNode temp = change.next;
        change.next = new SkipListNode(item);
        change.next.next = temp;
        if(belowLink != null)
            change.next.below = belowLink;
    }

    public boolean contains(int item)
    {
        SkipListNode current = levels.get(levels.size()-1);
        while(current != null)
        {
            if(current.data == item)
                return true;
            else
            {
                if(current.next.data > item)
                    current = current.below;
                else
                    current = current.next;
            }
        }
        return false;
    }

    public void remove(int item)
    {

    }

}
