import java.util.ArrayList;

public class OHashTable extends HashTables
{
    //this version of the hash table uses open addressing

    private class index
    {
        public boolean occupied;
        public Object data;

        public index()
        {
            data = null;
            occupied = false;
        }

        public index(Object data)
        {
            this.data = data;
            occupied = true;
        }

        public index(index other)
        {
            if(!(this == other))
            {
                this.data = other.data;
                this.occupied = other.occupied;
            }
        }
    }

    public OHashTable()
    {
        super();
        buckets = new index[10];
    }

    public OHashTable(int initialSize)
    {
        super(initialSize);
        buckets = new index[initialSize];
    }

    public void add(Object data)
    {
        int pos = getPosition(data);
        while(buckets[pos] != null && ((index)buckets[pos]).occupied)
        {
            if(((index)buckets[pos]).data.equals(data))
                return;
            pos = (++pos%capacity);
        }

        ++nodeCount;

        if(buckets[pos] == null)
            buckets[pos] = new index(data);
        else
            ((index)buckets[pos]).data = data;

        if((double) nodeCount/capacity >= 0.75)
            expand();
    }

    public void expand()
    {
        System.out.println("needed to expand at " + capacity);
        index temp[] = (index[]) buckets;
        int end = capacity;
        capacity*=2;
        nodeCount = 0;
        buckets =  new index[capacity * 2];

        for(int i = 0 ; i < end; ++i)
            add(temp[i].data);
    }

    private int search(Object data)
    {
        int pos = getPosition(data);
        while(buckets[pos] != null && !((index)buckets[pos]).data.equals(data))
            pos = (++pos%capacity);
        return pos;
    }

    public void remove(Object data)
    {
        int pos =  search(data);
        if(buckets[pos] == null)
            return;
        else
        {
            --nodeCount;
            ((index) buckets[pos]).occupied = false;
        }
    }

    public boolean contains(Object data)
    {
        int pos = search(data);
        return buckets[pos] != null;
    }
}
