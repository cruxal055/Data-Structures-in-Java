public class Heap
{
    private int arr[];
    private int currIndex, capacity;

    public Heap()
    {
        arr = new int[10];
        capacity = 10;
        currIndex = 0;
    }

    public Heap(int item)
    {
        arr = new int[10];
        capacity = 10;
        arr[currIndex++] = item;
    }

    public void add(int item)
    {
        arr[currIndex++] = item;
        int parent = getParent(currIndex - 1), child = currIndex - 1;
        while (parent > 0)
        {
            if (arr[parent] > arr[child])
            {
                swap(parent,child);
                child = parent;
                parent = getParent(parent);
            }
            else
                break;
        }
        if(capacity == currIndex)
            expand();
    }

    public int remove()
    {
        //throw error if it is not there.
        //make sure the size is big enough to do this sorta stuff mkay?
        
        int toReturn = arr[0];
        arr[0] = arr[currIndex-1];
        arr[currIndex-1] = 0;
        --currIndex;

        return toReturn;
    }

    private int getExistingChild(int index)
    {
        return (inHeap(getLChild(index))) ? getLChild(index) : getRChild(index);
    }

    private int childCount(int index)
    {
        int counter = 0;
        counter+=(inHeap(getLChild(index)) ? 1 : 0);
        counter+=(inHeap(getRChild(index)) ? 1 : 0);
        return counter;
    }

    private boolean inHeap(int index)
    {
        return index < currIndex;
    }

    private void expand()
    {
        int temp[] = new int[capacity * 2];
        for(int i = 0; i  < capacity; ++i)
            temp[i] = arr[i];
        capacity*=2;
        arr = temp;
    }


    private void swap(int index1, int index2)
    {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    private int getLChild(int index) { return 2 * index + 1; }

    private int getRChild(int index) { return 2 * index + 2; }

    private int getParent(int index) { return (index - 1)/2 ;}

    public int size() { return currIndex-1; }


}
