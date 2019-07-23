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


        }


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
