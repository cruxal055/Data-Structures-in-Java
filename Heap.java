import java.util.Set;
import java.util.TreeSet;

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

    public boolean isEmpty()
    {
        return currIndex == 0;
    }

    public void add(int item)
    {
        arr[currIndex++] = item;
        int parent = getParent(currIndex - 1), child = currIndex - 1;

        while (parent >= 0)
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
        if(currIndex == 0)
            throw new Error("heap is empty");

        int toReturn = arr[0];

        arr[0] = arr[currIndex-1];
        arr[currIndex-1] = 0;
        --currIndex;


        int pos = 0, numOfChildren, temp;
        while(true)
        {
            numOfChildren = childCount(pos);
            int x = 5;

            if(numOfChildren >= 1)
            {
                if(numOfChildren == 2)
                {
                    if(biggerThanBothChildren(pos))
                        temp = smallerOfTheTwoChildren(pos);
                    else
                        break;
                }
                else
                {
                    if(arr[pos] > arr[getLChild(pos)])
                        temp = getLChild(pos);
                    else
                        break;
                }
                swap(temp, pos);
                pos = temp;
            }
            else
                break;
        }
        return toReturn;
    }

    private int smallerOfTheTwoChildren(int index)
    {

        return (arr[getRChild(index)] < arr[getLChild(index)]) ? getRChild(index) : getLChild(index);
    }

    private boolean biggerThanBothChildren(int index)
    {
        return arr[getRChild(index)] < arr[index] || arr[getLChild(index)] < arr[index];
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

    public int size()
    {
        if(currIndex == 0)
            return 0;
        return currIndex;
    }


    public static void main(String args[])
    {
        Heap item = new Heap();

        Set<Integer> allItems = new TreeSet<>();

        for(int i = 0; i < 50; ++i)
            item.add(i);
//        item.remove();

        int y = 5;

        for(int i = 0; i < 50; ++i)
            System.out.println(item.remove());


    }

}
