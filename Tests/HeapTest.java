import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;


public class HeapTest
{
    @Test
    @DisplayName("testing general addition and removal")
    void testSort()
    {
        Heap item = new Heap();
        Set<Integer> allItems = new TreeSet<>();
        Integer x;
        for(int i = 0; i < 50; ++i)
        {
            x = (int) (Math.random() * 10000) + 1;
            if(allItems.contains(x))
                continue;
            allItems.add(x);
            item.add(x);
        }

        Integer arr[] = new Integer[50];
        for(int i = 0; i < 50; ++i)
        {
            arr[i] = item.remove();
            System.out.println(arr[i]);
        }
        assertArrayEquals(arr, allItems.toArray());
    }

    @Test
    @DisplayName("testing removing when empty")
    void emptyRemoval()
    {
        Heap item = new Heap();
        try
        {
            item.remove();
        }
        catch(Error e)
        {
            System.out.println("empty");
            assertEquals("heap is empty", e.getMessage());
        }
    }

    @Test
    @DisplayName("testing emptiness")
    void testEmpty()
    {
        Heap item = new Heap();
        assertTrue(item.isEmpty());
        item.add(100);
        assertFalse(item.isEmpty());
    }

    @Test
    @DisplayName("testing size")
    void testSize()
    {
        Heap item = new Heap();
        assertEquals(0, item.size());
        for(int i = 0; i < 100; ++i)
            item.add(i);
        assertEquals(100, item.size());
        for(int i = 0; i < 100; ++i)
            item.add(i);
        assertEquals(200, item.size());
    }
}
