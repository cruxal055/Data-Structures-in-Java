import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class AvlTree
{
    enum errors
    {
        NOT_A_VALUE;
    }
    class Node implements Comparable<Node>
    {
        public int data;
        public Node lTree, rTree;
        public int height;
        Node()
        {
            lTree = rTree = null;
            data = 0;
            height = 0;
        }
        Node(int data)
        {
            this.data = data;
            lTree = rTree = null;
            height  = 0;
        }
        int getAVLVal()
        {
            int val1, val2;
            val1 = (rTree != null) ? rTree.height + 1 : 0;
            val2 = lTree != null ? lTree.height + 1 : 0;
            return val1  - val2 ;
        }

        Node retrieveChild()
        {
            return (lTree == null) ? rTree : lTree;
        }

        void wipeNonData()
        {
            height = 0;
            lTree = rTree = null;
        }

        public int compareTo(Node other)
        {
            return other.data - data;
        }


    }

    private Node root;
    private int size;

    public AvlTree()
    {
        size = 0;
        root = null;
    }

    public AvlTree(int rootItem)
    {
        root = new Node(rootItem);
        size = 1;
    }

    public void insert(int newData)
    {
        if(root == null)
        {
            root = new Node(newData);
            ++size;
        }
        else
        {
            if(contains(newData))
                return;
            traverse(root, null, newData, 0);
        }

    }


    private int traverse(Node nodeItem, Node parent, int newData, int counter)
    {
        if(newData < nodeItem.data)
        {
            if(nodeItem.lTree == null)
            {
                nodeItem.lTree = new Node(newData);
                ++size;
            }
            else
            {
                counter = traverse(nodeItem.lTree, nodeItem, newData, counter);
            }
        }
        else
        {
            if(newData > nodeItem.data)
            {
                if(nodeItem.rTree == null)
                {
                    nodeItem.rTree = new Node(newData);
                    ++size;
                }
                else
                {
                    counter = traverse(nodeItem.rTree, nodeItem, newData, counter);
                }
            }
        }

        ++counter;
        if(nodeItem.height < counter)
        {
            nodeItem.height = counter;
        }


        if(Math.abs(nodeItem.getAVLVal()) > 1)
        {

            if(nodeItem.data < newData) //right
            {
                if(nodeItem.rTree.data < newData)
                {
                    nodeItem = right(nodeItem);
                }
                else
                {
                    nodeItem = rightLeft(nodeItem);
                }
            }
            else //left
            {
                if(nodeItem.lTree.data > newData)
                {
                    nodeItem = left(nodeItem);
                }
                else
                {
                    nodeItem = leftRight(nodeItem);
                }
            }

            if(parent == null)
                root = nodeItem;
            else
            {
                if (nodeItem.compareTo(parent) > 0)
                    parent.lTree = nodeItem;
                else
                    parent.rTree = nodeItem;
            }
            counter = nodeItem.height;
        }

        return counter;
    }
    //AVL rotations


    private Node right(Node item)
    {
        Node temp = item.rTree;
        if(temp.lTree != null)
        {
            Node temp2 = temp.lTree;
            item.rTree = null;
            item.rTree = temp2;
            temp.lTree = item;
            Node lTree = temp.lTree;

            lTree.height = (lTree.rTree.height + 1) > (lTree.lTree.height + 1) ?
                    lTree.rTree.height + 1 : lTree.lTree.height + 1;
            temp.height = (temp.rTree.height + 1) > (temp.lTree.height + 1) ?
                    (temp.rTree.height + 1) : (temp.lTree.height + 1);
        }
        else
        {
            item.rTree = null;
            temp.lTree = item;
            item.height = 0;
            temp.height = 1;
        }
        return temp;
    }

    private Node left(Node item)
    {

        Node temp = item.lTree;
        if(item.rTree != null)
        {
            item.lTree = null;
            item.lTree = temp.rTree;
            item.height = (item.rTree.height + 1) >=  (item.lTree.height + 1)
                    ? (item.rTree.height + 1) :  (item.lTree.height + 1);
            temp.rTree = null;
            temp.rTree = item;
            temp.height = (temp.rTree.height + 1) >= (temp.lTree.height + 1)
                    ?  (item.lTree.height + 1) :  (temp.lTree.height + 1);

            temp.lTree.height = (temp.lTree.rTree.height + 1) >= (temp.lTree.lTree.height + 1)
                    ?  (temp.lTree.rTree.height + 1) : (temp.lTree.lTree.height + 1);
        }
        else
        {
            item.lTree = null;
            temp.rTree = item;
            item.height = 0;
            temp.height = 1;
        }
        return temp;
    }



    private void retreiveHeight(Node item)
    {
      if(item.rTree == null)
          item.height = (item.lTree.height + 1);
      else
      {
          if(item.lTree == null)
              item.height = item.rTree.height + 1;
          else
          {
              item.height = (item.lTree.height + 1) >= (item.rTree.height + 1)
                      ? (item.lTree.height + 1)  : (item.rTree.height + 1);
          }
      }
    }

    private Node leftRight(Node item)
    {
        Node temp;
        temp = item.lTree.rTree;
        item.lTree.rTree = null;
        if(item.rTree != null)
        {
            Node temp1 = temp.lTree, temp2 = temp.rTree;
            temp.lTree = item.lTree;
            item.lTree = null;
            temp.rTree = item;
            if(temp1 != null)
                temp.lTree.rTree = temp1;
            if(temp2 != null)
                temp.rTree.lTree = temp2;
            retreiveHeight(temp.lTree);
            retreiveHeight(temp.rTree);

            temp.height = (temp.lTree.height + 1) >= (temp.rTree.height + 1)
                    ? (temp.lTree.height + 1)  : (temp.rTree.height + 1);

        }
        else
        {
             temp.lTree = item.lTree;
             item.lTree = null;
             temp.rTree = item;
             temp.height = 1;
             temp.lTree.height = temp.rTree.height = 0;
        }
        return temp;
    }

    private Node rightLeft(Node item)
    {
        Node temp;
        temp = item.rTree.lTree;
        item.rTree.lTree = null;
        if(item.lTree != null)
        {
          Node temp1 = temp.lTree, temp2 = temp.rTree;
          temp.rTree = item.rTree;
          item.rTree = null;
          temp.lTree = item;
            if(temp1 != null)
                temp.lTree.rTree = temp1;
            if(temp2 != null)
                temp.rTree.lTree = temp2;
            retreiveHeight(temp.lTree);
            retreiveHeight(temp.rTree);
            temp.height = (temp.lTree.height + 1) >= (temp.rTree.height + 1)
                    ? (temp.lTree.height + 1)  : (temp.rTree.height + 1);
        }
        else
        {
            temp.rTree = item.rTree;
            item.rTree = null;
            temp.lTree = item;
            temp.height = 1;
            temp.rTree.height = temp.lTree.height = 0;
        }
        return temp;
    }




    public void remove(int toRemove)
    {
        if(!removeTraverse(root, null, toRemove ))
            System.out.println("not a value");

    }

    private boolean removeTraverse(Node child, Node parent, int newData)
    {

        if(child == null) {
            System.out.println("returned\n");
            return false;

        }
        if(newData < child.data)
        {

            parent = child;
            child = child.lTree;
        }
        else
        {
            if(newData > child.data)
            {
                parent = child;
                child = child.rTree;
            }
            else
            {
                int count = 0;

                count+=((child.lTree != null) ? 1 : 0);
                count+=((child.rTree != null) ? 1 : 0);
                switch(count)
                {
                    case 0:
                        parent.lTree = parent.rTree = null;
                        break;
                    case 1:
                        if(child.lTree != null)
                        {
                            child.data = child.lTree.data;
                            child.lTree = null;
                        }
                        else
                        {
                            child.data = child.rTree.data;
                            child.rTree = null;
                        }
                        break;
                    case 2:
                        Node reference = child.rTree, parentReference = child;
                        while(reference.lTree != null)
                        {
                            parentReference = reference;
                            reference = reference.lTree;

                        }
                        child.data = parentReference.lTree.data;
                        parentReference.lTree = null;
                        break;
                    default:
                }
                return true;
            }
        }
        return removeTraverse(child, parent, newData);
    }
    public boolean contains(int itemToCheck)
    {
        return containsTraverse(root, itemToCheck);
    }

    private boolean containsTraverse(Node nodeItem, int newData)
    {
        if(nodeItem == null)
            return false;
        if(newData < nodeItem.data)
        {
            nodeItem = nodeItem.lTree;
        }
        else
        {
            if(newData > nodeItem.data)
                nodeItem = nodeItem.rTree;
            else
                return true;
        }
        return containsTraverse(nodeItem, newData);
    }

    public int getSize()
    {
        return size;
    }

    public int getHeight()
    {
        return root.height;
    }


    public ArrayList<Integer> inOrderTraversal()
    {
        ArrayList<Integer> item = new ArrayList<>();
        inOrder(root, item);
        return item;
    }

    private void inOrder(Node current, ArrayList<Integer> allItems)
    {

        if(current == null)
            return;
        else
        {
            inOrder(current.lTree, allItems);
            allItems.add(current.data);
            inOrder(current.rTree, allItems);
        }
    }





    public static void main(String args[])
    {
//        AvlTree temp = new AvlTree();
////
//////        int arr[] = {1,2,3};
//                int arr[] = {4,2,8,1,3,6,10,5,7,9,11,12};
////        int arr[] = {1,2,3,4,5, 6};
////
////        //        int arr[] = {4,2,6,1,3,5,7,8,9};
//        for(int i = 0; i < arr.length; ++i)
//            temp.insert(arr[i]);
//        temp.inOrderTraversal();
////        System.out.println("height is: " + temp.getHeight());
////        int x = 5;

        AvlTree tester = new AvlTree();
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 90; i <= 100; ++i)
        {
            arr.add(i);
        }
        for(int i = 100; i >= 90; --i) {
            tester.insert(i);
        }
//        assertEquals(arr,tester.inOrderTraversal());

    }



}
