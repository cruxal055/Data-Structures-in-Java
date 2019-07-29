public class HashTables<T>
{
    private Object buckets[];
    private int nodeCount, capacity;


    public HashTables()
    {
        nodeCount = 0;
        buckets = new Object[10];
    }

    public HashTables(int initialSize)
    {
        nodeCount = 0;
        buckets = new Object[initialSize];
        capacity = initialSize;
    }

    public int size()
    {
        return nodeCount;
    }

    public int getCapacity()
    {
        return capacity;
    }


    public void add(T item)
    {

    }

    public void remove(T item)
    {

    }

    public boolean contains(T item)
    {
        return false;
    }


}
