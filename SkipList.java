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
        if(levels.isEmpty())
        {
            levels.add(generateDefault(item, null));
            return;
        }
       ArrayList<SkipListNode> holder = new ArrayList<>();
//        SkipListNode current = levels.get(levels.size()-1);
        SkipListNode current = levels.get(0);
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
        ++size;
        adjustLinks(item, holder.get(holder.size()-1), null);
        holder.set(holder.size()-1, holder.get(holder.size()-1).next);
        int whatDigit;
        //may potentially give us a negative item
        //adjusts even level of the skip list
        if(levels.size() > 1)
        {
            for (int i = holder.size() - 2; i >= 0; --i)
            {
                whatDigit = (int) (Math.random() * 2);
                if (whatDigit == 0)
                    return;
                else
                {
                    adjustLinks(item, holder.get(i), holder.get(i + 1));
                    holder.set(i, holder.get(i).next);
                }
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
        SkipListNode current = levels.get(0);
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

    public int getSize()
    {
        return size;
    }

    public void remove(int item)
    {
        SkipListNode current = levels.get(0);
        SkipListNode parent = null;
        boolean contains = false;
        while(current != null)
        {
            if(current.data == item)
            {
                contains = true;
                break;
            }
            else
            {
                parent = current;
                if(current.next.data > item)
                {
                    current = current.below;
                }
                else
                    current = current.next;
            }
        }
        if(contains)
        {
            SkipListNode temp;
            while (current != null)
            {
                temp = current.below;
                parent.next = current.next;
                current = temp;
            }
        }
    }

    public static void main(String args[])
    {
        SkipList item = new SkipList();
        for(int i = 0; i  < 10; ++i)
            item.insert(i);
        int x = 5;
        for(int i = 0; i  < 10; ++i)
            System.out.println(item.contains(i));
        System.out.println("do i have 0? " + item.contains(0));
        item.remove(0);
        System.out.println(item.contains(0));
        System.out.println(item.contains(1));

    }

}
