import java.util.LinkedList;
import java.util.Queue;

public class AvlTree
{
    enum errors
    {
        NOT_A_VALUE;
    }
    class Node
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
            return rTree.height - lTree.height;
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
            nodeItem.height = counter;
//        if(nodeItem.height > 1)
//        {
//            right(nodeItem, parent);
//        }
        return counter;
    }
    //AVL rotations
    private void left(Node item, Node parent)
    {

    }

    public void leftRight(Node inbalance, Node parent)
    {
        Node temp;
       if(inbalance.lTree == null)
       {
           if(parent == null)
           {
               temp = root;
               root = root.lTree.rTree;
               root.lTree = temp.lTree;
               root.rTree = temp;
               root.lTree.wipeNonData();
               root.rTree.wipeNonData();
           }
           else
           {
               parent.lTree = inbalance.rTree.lTree;
               Node careAbout = parent.lTree;
                careAbout.lTree = inbalance;
                careAbout.rTree = inbalance.rTree;
                careAbout.lTree.wipeNonData();
                careAbout.rTree.wipeNonData();
           }
       }
       else
       {
           Node focus = root.lTree.rTree, child;
           child = focus.retrieveChild();
           focus.lTree = root.lTree;
           root.lTree = null;
           focus.rTree = root;
           focus.lTree.rTree = child;
           root = focus;
       }
    }
    public void right(Node item)
    {
        Node temp = item.rTree;
        if(temp.lTree != null)
        {
            Node temp2 = temp.lTree;
            item.rTree = null;
            item.rTree = temp2;
            temp.lTree = item;
        }
        else
        {
            item.rTree = null;
            temp.lTree = item;
        }
        root =  temp;


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
        int arr[] = {2,1,4,3,5,6};
//        int arr[] = {4,2,6,1,3,5,7,8,9};
        for(int i = 0; i < arr.length; ++i)
            temp.insert(arr[i]);
        temp.right(temp.root);

        int x = 5;
        System.out.println(temp.getHeight());



    }



}
