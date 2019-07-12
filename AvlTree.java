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
        nodeItem.height = ++counter;
//        if(nodeItem.height > 1)
//        {
//            right(nodeItem, parent);
//        }
        return counter;
    }
    //AVL rotations
    private void left(Node item)
    {


    }

    public void right(Node item, Node parent)
    {
        Node temp;
        if(item.rTree.lTree == null)
        {
            temp = item;
            if(parent == null)
                root = item.rTree;
            else
                parent = item.rTree;
            temp.lTree = temp.rTree = null;
            root.lTree = temp;
        }
        else
        {
            temp = root;
            root = root.rTree;
            Node temp2 = root.lTree;
            root.lTree = temp;
            root.lTree.rTree = temp2;
        }
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
        for(int i = 1; i < 7; ++i)
            temp.insert(i);
        temp.right(temp.root, null);
        int x = 5;
        System.out.println(temp.getHeight());



    }



}
