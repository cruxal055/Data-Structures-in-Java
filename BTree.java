import java.util.LinkedList;
import java.util.Queue;

public class BTree
{
    enum errors
    {
        NOT_A_VALUE;
    }
    class node
    {
        public int data;
        public node lTree, rTree;
        node()
        {
            lTree = rTree = null;
            data = 0;
        }
        node(int data)
        {
            this.data = data;
            lTree = rTree = null;
        }
    }

    private node root;
    private int size;

    public BTree()
    {
        size = 0;
        root = null;
    }

    public void breadthTraversal()
    {
        if(root == null)
            return;
        Queue<node> allItems = new LinkedList<node>();
        allItems.add(root);
        node parent;
        while(!allItems.isEmpty())
        {
            parent = allItems.peek();
            if(parent.lTree != null)
                allItems.add(parent.lTree);
            if(parent.rTree != null)
                allItems.add(parent.rTree);
            System.out.print(allItems.peek().data + " ");
            allItems.remove();
        }
    }
    public BTree(int rootItem)
    {
        root = new node(rootItem);
        size = 1;
    }

    public void insert(int newData)
    {
        if(root == null)
        {
            root = new node(newData);
            ++size;
        }
        else
            traverse(root, newData);
    }

    private void traverse(node nodeItem, int newData)
    {
        if(newData < nodeItem.data)
        {
            if(nodeItem.lTree == null)
            {
                nodeItem.lTree = new node(newData);
                ++size;
            }
            else
                traverse(nodeItem.lTree, newData);
        }
        else
        {
            if(newData > nodeItem.data)
            {
                if(nodeItem.rTree == null)
                {
                    nodeItem.rTree = new node(newData);
                    ++size;
                }
                else
                    traverse(nodeItem.rTree, newData);
            }
        }
    }



    public void remove(int toRemove)
    {
        if(!removeTraverse(root, null, toRemove ))
            System.out.println("not a value");

    }

    private boolean removeTraverse(node child, node parent, int newData)
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
                        node reference = child.rTree, parentReference = child;
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

    private boolean containsTraverse(node nodeItem, int newData)
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

    //traversals

    public void inOrderTraversal()
    {
        inOrder(root);
    }

    private void inOrder(node current)
    {

        if(current == null)
            return;
        else
        {
            inOrder(current.lTree);
            System.out.println(current.data);
            inOrder(current.rTree);
        }
    }
    public void preOrderTraversal()
    {
        preOrder(root);
    }

    private void preOrder(node item)
    {
        if(item == null)
            return;
        else
        {
            System.out.println(item.data);
            preOrder(item.lTree);
            preOrder(item.rTree);
        }
    }

    public void postOrderTraversal()
    {
        postOrder(root);
    }

    private void postOrder(node item)
    {
        if(item == null)
            return;
        else
        {
            postOrder(item.lTree);
            postOrder(item.rTree);
            System.out.println(item.data);
        }
    }


    public static void main(String args[])
    {
////        int arr[] = {30, 10, 55, 20, 45, 70, 15, 25};
//        int arr[] = {3, 4, 5, 6, 7, 8, 9, 10};
//
//        BTree n = new BTree();
//        for(int i = 0; i < arr.length; ++i)
//            n.insert(arr[i]);
//
////        n.inOrderTraversal();
////        n.preOrderTraversal();
////          n.postOrderTraversal();
////        n.breadthTraversal();
////        System.out.println("size is: " + n.contains(14));
////        n.remove(10);
////        n.inOrderTraversal();
//        System.out.println(n.height());
//        System.out.println("hello world\n");
    }
}
