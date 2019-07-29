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

    public void add(Object item)
    {
        ++nodeCount;
        if((nodeCount/capacity) > 0.75)
        {

        }
        else
        {
            
        }

    }

    //will return the position where the item belongs,
    private LLNode getLLPosition(Object item)
    {
        int pos = getPosition(item);
        LLNode ref = (LLNode)buckets[pos], parent = null;
        while(ref != null)
        {
            if(ref.equals(item))
                break;
            parent = ref;
            ref = ref.next;
        }
        return parent;
    }

    public void remove(Object item)
    {


    }

    public boolean contains(Object item)
    {

    }





}
