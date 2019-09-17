import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashTableTest
{
    @Test
    public void testInsertionAndContains()
    {
        CHashTable hTable = new CHashTable();
        Set<Integer> all = new HashSet<>();

        for(int i = 0; i < 10000; ++i)
        {
            Integer temp = new Integer( (int)(Math.random() * 1000) + 1);
            hTable.add(temp);
            all.add(temp);
        }
        Iterator<Integer> it = all.iterator();
        while(it.hasNext())
        {
            Integer x = it.next();
            assertTrue(hTable.contains(x));
        }
    }

    @Test
    public void testInsertionAndContains2()
    {
        CHashTable hTable = new CHashTable();
        Set<Integer> all = new HashSet<>();

        for(int i = 0; i < 10; ++i)
        {
            Integer temp = new Integer( (int)(Math.random() * 100) + 1);
            hTable.add(temp);
//            if(all.contains(temp))
//                System.out.print("found a duplicate: " + temp);
            all.add(temp);
        }
        Iterator<Integer> it = all.iterator();
        hTable.traverse();
        while(it.hasNext())
        {
            Integer x = it.next();
        }
        while(it.hasNext())
        {
            Integer x = it.next();
            assertTrue(hTable.contains(x));
        }
    }

    @Test
    public void testInsertionAndContains3()
    {
        CHashTable hTable = new CHashTable();
        Set<Integer> all = new HashSet<>();

        for(int i = 0; i < 100; ++i)
        {
            hTable.add(i);
            all.add(i);
        }
        Iterator<Integer> it = all.iterator();
        hTable.traverse();
        System.out.println("");
        while(it.hasNext())
        {
            Integer x = it.next();
            System.out.print(x + " ");
            assertTrue(hTable.contains(x));
        }
    }

    @Test
    public void testRemovalExt()
    {
        CHashTable hTable = new CHashTable();
        Set<Integer> all = new HashSet<>();

        for(int i = 0; i < 10000; ++i)
        {
            Integer temp = new Integer( (int)(Math.random() * 1000) + 1);
            hTable.add(temp);
            all.add(temp);
        }
        Iterator<Integer> it = all.iterator();
        hTable.traverse();
        for(int i = 0; i < 1000; ++i)
        {
            Integer temp = new Integer( (int)(Math.random() * 1000) + 1);
            hTable.remove(temp);
            assertTrue(!hTable.contains(temp));
        }
    }

    @Test
    public void testWithStrings1()
    {
        CHashTable hTable = new CHashTable();
        Set<String> all = new HashSet<>();
        for(int i = 0; i < 100; ++i)
        {
            int whereTo = (int)(Math.random() * 10)+ 1;
            String neo = new String();
            for(int j = 0; j < whereTo; ++j)
            {
                neo += (char) ('a' + (int)(Math.random() * 26));
            }
            all.add(neo);
            hTable.add(neo);

        }
        Iterator<String> it = all.iterator();
        while(it.hasNext())
        {
            String x = it.next();
            assertTrue(hTable.contains(x));
        }
    }
}
