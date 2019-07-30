public class CHashTable extends HashTables
{
    public CHashTable()
    {
        super();
        buckets = new LLNode[10];
    }
    public CHashTable(int size)
    {
        super(size);
        buckets = new LLNode[size];
    }

    public void add(Object data)
    {
        ++nodeCount;
//        if((nodeCount/capacity) > 0.75)
//        {
//
//        }
//        else
//        {
            int pos = getPosition(data);
            LLNode ref = (LLNode) buckets[pos], parent = null;


            while(ref != null)
            {
                if(ref.item.equals(data))
                    return;
                parent = ref;
                ref = ref.next;
            }
            if(parent == null)
                buckets[pos] = new LLNode(data);
            else
            {
                LLNode temp = ref.next;
                ref.next = new LLNode(data);
                ref.next.previous = ref;
                ref = ref.next;
                ref.next = temp;
                temp.previous = ref;
            }
//        }

    }

    //will return the position where the item belongs,
    private LLNode getLLPosition(LLNode data, Object target)
    {
        LLNode ref = data;
        while(ref != null)
        {
            if(ref.item.equals(target))
                break;
            ref = ref.next;
        }
        return ref;
    }

    public void remove(Object item)
    {
        int pos = getPosition(item);
        LLNode ref = getLLPosition((LLNode) buckets[pos], item);
        if(ref == null)
            return;
        else
        {
            if(ref.previous == null)
                buckets[pos] = ref.next;
            else
                ref.previous = ref.next;
        }

    }

    public boolean contains(Object item)
    {
        int pos = getPosition(item);
        LLNode ref = getLLPosition((LLNode) buckets[pos], item);
        return (ref != null);
    }

    public static void main(String args[])
    {
        CHashTable neo = new CHashTable();
        for(int i = 0; i < 5; ++i)
            neo.add(new Integer(i));
        int x = 5;
        for(int i = 0; i < 10; ++i)
        {
            System.out.println("bool value is: " + neo.contains(new Integer(i)));
        }
        System.out.println("");
        for(int i = 0; i < 10; ++i)
        {
            neo.remove(new Integer(i));
            System.out.println("bool value is: " + neo.contains(new Integer(i)));
        }


    }

}
