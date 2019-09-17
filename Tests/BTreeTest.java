import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BTreeTest {

    @Test
    void contains()
    {
        BTree item = new BTree(5);
        assertEquals(true, item.contains(5));
        assertEquals(false, item.contains(6));
        assertFalse(item.contains(6));
    }

    @Test
    void getSize()
    {
        BTree item = new BTree(5);
        assertEquals(1, item.getSize());
    }


}