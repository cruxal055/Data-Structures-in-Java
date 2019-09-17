import org.junit.jupiter.api.Test;
import java.awt.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SkipListTest
{
    @Test
    void testInsertion()
    {
        SkipList item = new SkipList();
        Set<Integer> container = new HashSet<Integer>();
        for(int i = 0;  i < 1000; ++i)
        {
            int x = (int)(Math.random() * 1000000) + 1;
            item.insert(x);
            container.add(x);
        }
        testContains(item, container);
        testRemoval(item, container);

    }

    @Test
    void testContains(SkipList item, Set<Integer> container)
    {
        Iterator<Integer> it = container.iterator();
        int temp;
        while(it.hasNext())
        {
            temp = it.next();
            assertTrue(container.contains(temp));
        }
    }

    @Test
    void testRemoval(SkipList item, Set<Integer> container)
    {
        for(int i = 0; i < 100; ++i)
        {
            int x = (int)(Math.random() * 1000000) + 1;
            item.remove(x);
            assertFalse(item.contains(x));
        }

    }

}
