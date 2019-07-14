import java.util.LinkedList;
import java.util.Queue;

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
            return Math.abs(val1  - val2 );
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
            traverse(root, null,  newData, 0);

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


        if(nodeItem.getAVLVal() > 1)
        {
            nodeItem = right(nodeItem);
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


    public Node right(Node item)
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
            int wt = 77;
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

    public void left(Node item)
    {
        Node temp = item.lTree;
        if(item.rTree != null)
        {
            item.lTree = null;
            item.lTree = temp.rTree;
            temp.rTree = item;
        }
        else
        {
            item.lTree = null;
            temp.rTree = item;
        }
    }

    public Node leftRight(Node item)
    {
        Node temp;
        temp = item.lTree.rTree;
        item.lTree.rTree = null;
        if(item.rTree != null)
        {
            temp.lTree = item.lTree;
            item.lTree = null;
            Node temp2 = temp.rTree;
            temp.rTree = item;
            item.rTree.lTree = temp2;
        }
        else
        {
             temp.lTree = item.lTree;
             item.lTree = null;
             temp.rTree = item;
        }
        return temp;
    }

    public Node rightLeft(Node item)
    {
        Node temp;
        temp = item.rTree.lTree;
        item.rTree.lTree = null;
        if(item.lTree != null)
        {
            temp.rTree = item.rTree;
            item.rTree = null;
            Node temp2 = temp.lTree;
            temp.lTree = item;
            item.lTree.rTree = temp2;
        }
        else
        {
            temp.rTree = item.rTree;
            item.rTree = null;
            temp.lTree = item;
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



    public static void main(String args[])
    {
        AvlTree temp = new AvlTree();

//        int arr[] = {1,2,3};
//                int arr[] = {4,2,8,1,3,6,10,5,7,9,11,12};
        int arr[] = {1,2,3,4,5};

        //        int arr[] = {4,2,6,1,3,5,7,8,9};
        for(int i = 0; i < arr.length; ++i)
            temp.insert(arr[i]);
        System.out.println("height is: " + temp.getHeight());
        int x = 5;

    }



}
