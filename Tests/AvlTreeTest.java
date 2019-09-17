import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class AvlTreeTest
{
    @Test
    void testHeight()
    {
        AvlTree item = new AvlTree(3);
        item.insert(2);

        item.insert(5);
        item.insert(7);
        item.insert(8);
        assertEquals(2,item.getHeight());
    }

    @Test
    void testsize()
    {
        AvlTree item = new AvlTree(3);
        item.insert(4);
        assertEquals(2,item.getSize());
    }
    @Test
    void testLeftBalancing()
    {
        AvlTree tester = new AvlTree();
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 90; i <= 100; ++i)
        {
            arr.add(i);
        }
        for(int i = 100; i >= 90; --i) {
            tester.insert(i);
        }
        assertEquals(arr,tester.inOrderTraversal());

    }


    @Test
    void testGeneralBalancing2()
    {


        // 384 305 978 34 578 959 337 842 670 95 285 260 259 412 891 564 711 812 584 28
        System.out.print("inserted: " );
            AvlTree item = new AvlTree();
            ArrayList<Integer> sorted = new ArrayList<>();
            Set<Integer> allNumeros = new TreeSet<>();
            int arr[] = {384,305, 978, 34, 578, 959, 337, 842, 670, 95, 285, 260, 259, 412, 891, 564, 711, 812, 584, 28};
            for(int i = 0; i < arr.length; ++i)
            {
                System.out.println("currently at " + arr[i]);
                allNumeros.add(arr[i]);
                item.insert(arr[i]);
                assertEquals(item.inOrderTraversal(), new ArrayList<Integer>(allNumeros));
            }
    }
        //}

    //418 3 978 803 960 729 132 622 849 45 798 352 936 878 426 145 899 357 37 430
    @Test
    void testGeneralBalancing3()
    {


        // 384 305 978 34 578 959 337 842 670 95 285 260 259 412 891 564 711 812 584 28
        System.out.print("inserted: " );
        AvlTree item = new AvlTree();
        ArrayList<Integer> sorted = new ArrayList<>();
        Set<Integer> allNumeros = new TreeSet<>();
        int arr[] = {418 ,3, 978, 803, 960 ,729 ,132 ,622 ,849 ,45 ,798, 352 ,936 ,878 ,426 ,145, 899 ,357 ,37 ,430};
        for(int i = 0; i < arr.length; ++i)
        {
            System.out.println("currently at " + arr[i]);
            allNumeros.add(arr[i]);
            item.insert(arr[i]);
            assertEquals(item.inOrderTraversal(), new ArrayList<Integer>(allNumeros));
        }
    }




    @Test
    void testLeftRightBalancing()
    {
        AvlTree tester = new AvlTree();
        ArrayList<Integer> items = new ArrayList<>();

        int arr[] = {55,49,100,40,53,50};
        int toCheck[] = {40, 49, 50, 53, 55, 100};
        for(int  i  = 0; i < arr.length; ++i)
            tester.insert(arr[i]);
        for(int  i  = 0; i < toCheck.length; ++i)
            items.add(toCheck[i]);
        assertEquals(items, tester.inOrderTraversal());
    }

    @Test
    void testRightLeftBalancing()
    {
        AvlTree tester = new AvlTree();
        ArrayList<Integer> items = new ArrayList<>();

        int arr[] = {139, 100, 147, 145, 150, 146};
        int toCheck[] = {100, 139,145, 146, 147, 150};
        for(int  i  = 0; i < arr.length; ++i)
            tester.insert(arr[i]);
        for(int  i  = 0; i < toCheck.length; ++i)
            items.add(toCheck[i]);
        assertEquals(items, tester.inOrderTraversal());
    }

    @Test
    void worstCaseR()
    {
        AvlTree tester = new AvlTree();
        ArrayList<Integer> items = new ArrayList<>();
        for(int i = 0; i < 10000000; ++i)
        {
            tester.insert(i);
            items.add(i);
        }
        assertEquals(items,tester.inOrderTraversal());
    }
    @Test
    void worstCaseL()
    {
        AvlTree tester = new AvlTree();
        ArrayList<Integer> items = new ArrayList<>();
        for(int i = 10000000; i >= 0; --i)
        {
            tester.insert(i);
            items.add(i);
        }
        Collections.sort(items);
        assertEquals(items,tester.inOrderTraversal());
    }

    @Test
    void givesError()
    {
        AvlTree tester = new AvlTree();
        int arr[] = {306, 840, 745, 285, 513, 741, 602, 623, 99, 821};
        int arr2[] = {99, 285, 306, 513, 602, 623, 741, 745, 821, 840};
        Integer iArr[]  = new Integer[10];

        for(int i = 0; i < arr.length; ++i)
        {
            tester.insert(arr[i]);
            iArr[i] = arr2[i];
        }
        assertArrayEquals(tester.inOrderTraversal().toArray(), iArr);
    }

    @Test
    void testRemoval()
    {

        for(int o = 0; o < 1000; ++o)
        {
            AvlTree tester = new AvlTree();
            Set<Integer> stuff = new TreeSet<Integer>();
            for (int i = 0; i < 1000; ++i)
            {
                tester.insert(i);
                stuff.add(i);
            }
            System.out.print("Attempting to remove: ");
            for (int i = 0; i < 50; ++i) {
                int x = (int) (Math.random() * 10) + 1;
                if (stuff.contains(x))
                {
                    System.out.print(x + " ");
                    tester.remove(x);
                    stuff.remove(x);
                    assertArrayEquals(stuff.toArray(), tester.inOrderTraversal().toArray());
                }
            }
            System.out.println();
        }
    }


    @Test
    void testRemovalEXTREME()
    {

        for(int o = 0; o < 100; ++o)
        {
            AvlTree tester = new AvlTree();
            Set<Integer> stuff = new TreeSet<Integer>();
            for (int i = 0; i <= 100; ++i)
            {
                tester.insert(i);
                stuff.add(i);
            }
            for (int i = 0; i < 100; ++i) {
                int x = (int) (Math.random() *1000000 ) + 1;
                if (stuff.contains(x))
                {
                    tester.remove(x);
                    stuff.remove(x);
                    assertArrayEquals(stuff.toArray(), tester.inOrderTraversal().toArray());
                }
            }
        }

    }

    //1 7 8

    @Test
    void removegivesError0()
    {
//        inserted: 0 1 2 3 4 5 6 7 8 9 Attempting to remove: 3 7
        AvlTree tester = new AvlTree();
        Set<Integer> stuff = new TreeSet<Integer>();
        for (int i = 0; i < 10; ++i) {
            System.out.print(i + " ");
            tester.insert(i);
            stuff.add(i);
        }
        System.out.println();
        stuff.remove(1);
        tester.remove(1);
        int a = 9;
        stuff.remove(7);
        tester.remove(7);
        int b = 9;
        stuff.remove(8);
        tester.remove(8);
        int c = 10;
//        int x = 10000;
        assertArrayEquals(stuff.toArray(), tester.inOrderTraversal().toArray());
    }


    @Test
    void removegivesError()
    {
//        inserted: 0 1 2 3 4 5 6 7 8 9 Attempting to remove: 3 7
        AvlTree tester = new AvlTree();
        Set<Integer> stuff = new TreeSet<Integer>();
        for (int i = 0; i < 10; ++i) {
            System.out.print(i + " ");
            tester.insert(i);
            stuff.add(i);
        }
        int x = 500;
        System.out.println();
        stuff.remove(1);
        tester.remove(1);
        stuff.remove(2);
        tester.remove(2);
        int a = 5;
//        System.out.println("removing 2\n");
//        int b = 6;
////        System.out.println("removing 7\n");
        stuff.remove(7);
        tester.remove(7);
//        int x = 10000;
        assertArrayEquals(stuff.toArray(), tester.inOrderTraversal().toArray());
    }



    @Test
    void removegivesError3()
    {
//        i    //9 6 7
        AvlTree tester = new AvlTree();
        Set<Integer> stuff = new TreeSet<Integer>();
        for (int i = 0; i < 10; ++i) {
            System.out.print(i + " ");
            tester.insert(i);
            stuff.add(i);
        }
        System.out.println();
        stuff.remove(9);
        tester.remove(9);
        int okc = 4;
        stuff.remove(6);
        tester.remove(6);
        int y = 555;
        stuff.remove(7);
        tester.remove(7);


        int x = 10000;
        assertArrayEquals(stuff.toArray(), tester.inOrderTraversal().toArray());
    }


    //8 9 7 6
    @Test
    void removegivesError4()
    {
        AvlTree tester = new AvlTree();
        Set<Integer> stuff = new TreeSet<Integer>();
        for (int i = 0; i < 10; ++i) {
            System.out.print(i + " ");
            tester.insert(i);
            stuff.add(i);
        }
        System.out.println();
        stuff.remove(8);
        tester.remove(8);
        stuff.remove(9);
        tester.remove(9);
        stuff.remove(7);
        tester.remove(7);
        int x = 9;
        stuff.remove(6);
        tester.remove(6);


        int y = 10000;
        assertArrayEquals(stuff.toArray(), tester.inOrderTraversal().toArray());
    }


    @Test
    void removegivesError2()
    {
//        inserted: 0 1 2 3 4 5 6 7 8 9 Attempting to remove: 3 7
        AvlTree tester = new AvlTree();
        Set<Integer> stuff = new TreeSet<Integer>();
        for (int i = 0; i < 10; ++i) {
            System.out.print(i + " ");
            tester.insert(i);
            stuff.add(i);
        }
        System.out.println();
        // 3 4 5 6
        stuff.remove(3);
        tester.remove(3);
        stuff.remove(4);
        tester.remove(4);
        int wtf = 9;
        stuff.remove(5);
        tester.remove(5);
        stuff.remove(6);
        tester.remove(6);
        int x = 10000;
        assertArrayEquals(stuff.toArray(), tester.inOrderTraversal().toArray());
    }

    //5 6 3 4
    @Test
    void removegivesError6()
    {
        AvlTree tester = new AvlTree();
        Set<Integer> stuff = new TreeSet<Integer>();
        for (int i = 0; i < 10; ++i)
        {
            System.out.print(i + " ");
            tester.insert(i);
            stuff.add(i);
        }
        System.out.println();
        System.out.println("attempting to remove 5");
        stuff.remove(5);
        tester.remove(5);
        int a = 3;

        System.out.println("attempting to remove 6");
        stuff.remove(6);
        tester.remove(6);
        int b = 3;

        System.out.println("attempting to remove 3");
        stuff.remove(3);
        tester.remove(3);
        int x = 99;

        System.out.println("attempting to remove 4");
        stuff.remove(4);
        tester.remove(4);




        int y = 10000;
        assertArrayEquals(stuff.toArray(), tester.inOrderTraversal().toArray());
    }




    @Test
    void nullPtrError()
    {
        AvlTree tester = new AvlTree();
        int arr[] = {719, 511, 952, 159, 365, 12};
        int correct[] = {12, 159, 365, 511, 719, 952};
        Integer refVer[] = new Integer[6];
        for(int i = 0; i < arr.length; ++i)
        {
            tester.insert(arr[i]);
            refVer[i] = correct[i];

        }
        assertArrayEquals(tester.inOrderTraversal().toArray(), refVer);
    }

    @Test
    void testRemoval10()
    {
        AvlTree tester = new AvlTree();
        Set<Integer> stuff = new TreeSet<Integer>();
        for (int i = 0; i < 10; ++i) {
            System.out.print(i + " ");
            tester.insert(i);
            stuff.add(i);
        }
        System.out.println();
        System.out.println("attempting to remove 5");
        stuff.remove(8);
        tester.remove(8);
        int a = 3;

        System.out.println("attempting to remove 6");
        stuff.remove(9);
        tester.remove(9);

        System.out.println("attempting to remove 3");
        stuff.remove(3);
        tester.remove(3);

        System.out.println("attempting to remove 4");
        stuff.remove(4);
        tester.remove(4);




        int y = 10000;
        assertArrayEquals(stuff.toArray(), tester.inOrderTraversal().toArray());
    }


    @Test
    void testGeneralBalancing()
    {


//            for (int i = 0; i < 100; ++i)
//            {
        for(int i = 0; i < 100;++i)
        {
            AvlTree item = new AvlTree();
            ArrayList<Integer> sorted = new ArrayList<>();
            Set<Integer> allNumeros = new TreeSet<>();
            System.out.print("the size of the tree is: " + item.getSize() + " inserted : ");
            for (int j = 0; j < 10; ++j)
            {
                int x = (int) (Math.random() * 100000) + 1;
                if(allNumeros.contains(x))
                    continue;
                else
                {
                    sorted.add(x);
                    allNumeros.add(x);
                    item.insert(x);
                    System.out.print(x + " , ");
                }
            }
            System.out.println();
            assertEquals(item.inOrderTraversal(), new ArrayList<Integer>(allNumeros));
        }
        //}
    }

    // 50426 , 61086 , 91687 , 24867 , 6438 , 17884 , 22796 };//, 29530 , 25277 , 40284
    @Test
    void testGeneralBalancing4()
    {
        int arr[] = {  5204 , 40285 , 93973 , 84886 , 8442, 37990 , 65785 , 46505 };

            AvlTree item = new AvlTree();
            ArrayList<Integer> sorted = new ArrayList<>();
            Set<Integer> allNumeros = new TreeSet<>();
            for (int j = 0; j < arr.length; ++j)
            {
                System.out.print("inserted " + arr[j] + " ");
                    sorted.add(arr[j]);
                    allNumeros.add(arr[j]);
                    item.insert(arr[j]);
                if(arr[j] == 17884)
                {
                    int x = 9;
                }
                System.out.println();
            }
        ArrayList<Integer> sorted2 = new ArrayList<>();
        sorted2 = item.inOrderTraversal();
            assertEquals(item.inOrderTraversal(), new ArrayList<Integer>(allNumeros));

        //}
    }


    @Test
    void testRemovalOneChildren()
    {
        Integer arr[] = new Integer[7];
        arr[0] = 20;
        arr[1] = 30;
        arr[2] = 34;
        arr[3] = 37;
        arr[4] = 45;
        arr[5] = 70;
        arr[6] = 81;
        AvlTree tester = new AvlTree(45);
        tester.insert(30);
        tester.insert(20);
        tester.insert(37);
        tester.insert(34);
        tester.insert(50);
        tester.insert(70);
        tester.insert(81);
        tester.remove(50);
        int x = 50;
        assertArrayEquals(tester.inOrderTraversal().toArray(), arr);
    }


    @Test
    void runs()
    {
        AvlTreeTest x = new AvlTreeTest();
    }

    @Test
    void testRemovalTwoChildren()
    {
        Integer arr[] = new Integer[7];
        arr[0] = 20;
        arr[1] = 30;
        arr[2] = 34;
        arr[3] = 37;
        arr[4] = 45;
        arr[5] = 47;
        arr[6] = 70;
        AvlTree tester = new AvlTree(45);
        tester.insert(30);
        tester.insert(20);
        tester.insert(37);
        tester.insert(34);
        tester.insert(50);
        tester.insert(47);
        tester.insert(70);
        tester.remove(50);
        assertArrayEquals(arr, tester.inOrderTraversal().toArray());
        assertEquals(tester.getHeight(),2);
    }



}
