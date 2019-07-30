import java.util.ArrayList;
import java.util.Collections;

//this version of the hash table utilizes seperate chaining to handle collisions
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
        {
            if(ref != null)
            {
                ref.next = new LLNode(data);
                ref.next.previous = ref;
            }
            else
            {
                buckets[pos] = new LLNode(data);
            }
        }
        else
        {
            if(ref == null)
            {
                parent.next = new LLNode(data);
                parent.next.previous = parent;
            }
            else
            {
                LLNode temp = ref.next;
                ref.next = new LLNode(data);
                ref.next.previous = ref;
                ref = ref.next;
                ref.next = temp;
                temp.previous = ref;
            }
        }

        ++nodeCount;

//            buckets[pos] = new LLNode(data);
//
////        System.out.println("at " + data +  " the LF is " +  (double)nodeCount/capacity);

        if( (double)nodeCount/capacity > 0.75)
            expand();

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

    private void expand()
    {
        Object neo[] = new Object[capacity * 2];
        LLNode ptr;
        int whereToEnd = capacity;
        nodeCount = 0;
        capacity*=2;
        Object temp[] = buckets;
        buckets = neo;

        for(int i = 0; i < whereToEnd; ++i)
        {
            ptr = (LLNode)temp[i];
            while(ptr != null)
            {
                add(ptr.item);
                ptr = ptr.next;
            }
        }
    }

    public ArrayList<Object> traverse()
    {
        LLNode temp;
        ArrayList<Object> toReturn = new ArrayList<>();

        for(int i = 0; i < capacity; ++i)
        {
            temp = (LLNode)buckets[i];
            while(temp != null)
            {
                toReturn.add(temp.item);
                temp = temp.next;
            }
        }

        return toReturn;

    }




    public static void main(String args[])
    {
        CHashTable neo = new CHashTable();
        for(int i = 0; i < 10; ++i)
            neo.add(new Integer(i));
        System.out.println("capacity is: " + neo.getCapacity());
        System.out.println("size is " + neo.getSize());

    }

}
