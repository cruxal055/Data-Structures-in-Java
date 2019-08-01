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

        if(capacity == nodeCount)
        {
            expand();

        }
    }

    public void expand()
    {
        index temp[] = new index[capacity * 2];
        capacity*=2;
        int tempPos;
        for(int i = 0 ; i < capacity/2; ++i)
        {
           tempPos = getPosition(((index)buckets[i]).data);
           temp[tempPos] = buckets[i];
        }
    }




}
