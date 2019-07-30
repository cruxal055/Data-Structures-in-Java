public class HashTables
{
    protected Object buckets[];
    protected int nodeCount, capacity;

    public HashTables()
    {
        nodeCount = 0;
        capacity = 10;
    }

    public HashTables(int initialSize)
    {
        nodeCount = 0;
        capacity = initialSize;
    }

    public int getSize()
    {
        return nodeCount;
    }

    public int getCapacity()
    {
        return capacity;
    }

    protected int getPosition(Object data)
    {
        return data.hashCode() % capacity;
    }

    public void add(Object item)
    {
        buckets[getPosition(item)] = item;
    }

    public void remove(Object item)
    {
        buckets[getPosition(item)] = null;
    }

    public boolean contains(Object item)
    {
        int pos = getPosition(item);
        if(buckets[pos] != null)
            return (buckets[pos].equals(item));
        return false;
    }

    public static void main(String args[])
    {
        HashTables x = new HashTables();
        System.out.println(x.getCapacity());
    }



}
