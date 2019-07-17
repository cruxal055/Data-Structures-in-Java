import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class AvlTree
{
    enum errors
    {
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
        public int getAVLVal()
        {
            int val1, val2;
            val1 = ((rTree != null) ? rTree.height + 1 : 0);
            val2 = ((lTree != null) ? lTree.height + 1 : 0);
            return val1  - val2 ;
        }

        public Node getBiggerChild()
        {
            if(lTree == null)
                return rTree;
            else
            {
                if(rTree == null)
                    return lTree;
                else
                {
                    return (rTree.height >= lTree.height) ? rTree : lTree;
                }
            }

        }

        public Node retrieveChild()
        {
            return (lTree == null) ? rTree : lTree;
        }

        int childCount()
        {
            int counter = 0;
            if(lTree != null)
                ++counter;
            if(rTree != null)
                ++counter;
            return counter;
        }

        public int compareTo(Node other)
        {
            return other.data - data;
        }

        public void updateHeight()
        {
            if(lTree == null || rTree == null)
            {
                if(lTree == null && rTree == null)
                    height = 0;
                else
                    height = (lTree == null ? rTree.height + 1 : lTree.height + 1);
            }
            else
            {
                int height = (rTree.height + 1) >= (lTree.height + 1) ?
                        rTree.height + 1 : lTree.height + 1;
                if(height != height)
                    height = height;
            }
        }


    }

    public Node root;
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
        counter = isBalanced(nodeItem, parent, newData, counter);
        return counter;
    }
    //AVL rotations

    //checks if balanced, if not balance it
    private int isBalanced(Node nodeItem, Node parent, int newData, int counter)
    {
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
            return nodeItem.height;
        }
        return counter;
    }

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

            lTree.height = retrieveHeightSingles(lTree);
            temp.height = retrieveHeightSingles(temp);
        }
        else
        {
            item.rTree = null;
            temp.lTree = item;
            item.height = 0;
            temp.height = 1;
        }
        int x = 100;
        return temp;
    }

    private Node left(Node item)
    {
        int homo = 3;
        Node temp = item.lTree;
        int zd = 4;
//        if(item.rTree != null)
        if(item.rTree != null)
        {
            item.lTree = null;
            item.lTree = temp.rTree;
            item.height = retrieveHeightSingles(item);

            temp.rTree = null;
            temp.rTree = item;
            temp.height = retrieveHeightSingles(item);


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

    private int retrieveHeightSingles(Node item)
    {
        if(item.rTree  == null|| item.lTree == null)
        {
            return item.rTree == null  ? item.lTree.height + 1 : item.rTree.height + 1;
        }
        else
        {
            return (item.rTree.height + 1) >=  (item.lTree.height + 1)
                    ? (item.rTree.height + 1) :  (item.lTree.height + 1);
        }
    }




    private void retreiveHeight(Node item)
    {
        if(item.lTree == null && item.rTree == null)
            item.height = 0;
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
            if(temp.lTree != null)
                retreiveHeight(temp.lTree);
            if(temp.rTree != null)
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
        removeTraverse(root, null, toRemove );
        --size;


    }

    private void removeTraverse(Node child, Node parent, int newData)
    {

        if(child == null)
        {
            throw new IllegalArgumentException("Value not Found in Tree");
        }
        if(newData < child.data)
        {
//            parent = child;
//            child = child.lTree;
            removeTraverse(child.lTree, child, newData);
        }
        else
        {
            if(newData > child.data)
            {
//                parent = child;
//                child = child.rTree;
                removeTraverse(child.rTree, child, newData);
            }
            else
            {
                int count = 0;
                count+=((child.lTree != null) ? 1 : 0);
                count+=((child.rTree != null) ? 1 : 0);
                switch(count)
                {
                    case 0:
                        if(parent.data > newData)
                            parent.lTree = null;
                        else
                            parent.rTree = null;
                        if(parent.rTree == null && parent.lTree == null)
                            parent.height = 1;
                        else
                            parent.height = 0;
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
                        child.height = 0;
                        break;
                    case 2:
                        Deque<Node> route = new LinkedList<Node>();
                        Node reference = child.rTree, parentReference = child;
                        route.add(child);
                        route.add(child.rTree);
                        while(reference.lTree != null)
                        {
                            parentReference = reference;
                            reference = reference.lTree;
                            route.add(reference);
                        }
                        int temp = child.data;
                        child.data = reference.data;
                        reference.data = temp;
                        int x = 5;

                        if(reference.childCount() == 1)
                        {

                            reference.data = reference.rTree.data;
                            reference.rTree = null;
                            reference.height = 0;
                        }
                        else
                        {
                            if(parentReference.lTree == reference)
                            {
                                parentReference.lTree = null;
                            }
                            else
                            {
                                parentReference.rTree = null;
                            }
                            parentReference.updateHeight();
                        }
//                        child.updateHeight();
                        Node temp0, temp1;
                        while(!route.isEmpty())
                        {
                            temp0 = route.getLast();
                            route.removeLast();
                            if(route.isEmpty())
                            {
                                System.out.println("emptied " + temp0.data);
                                removalReBalance(temp0, null);
                            }
                            else
                            {
                                temp1 = route.getLast();
                                System.out.println("emptied " + temp0.data + " and " + temp1.data);
                                route.removeLast();
                                removalReBalance(temp0,temp1);

                            }
                        }
                        break;
                    default: //here for good practice
                }
            }
        }
        child.updateHeight();
        removalReBalance(child, parent);
    }


    private void removalReBalance(Node nodeItem, Node parent)
    {
        Node bigger = nodeItem.getBiggerChild();
        Node grandfather;
        if(Math.abs(nodeItem.getAVLVal()) > 1)
        {
            if (bigger == nodeItem.lTree) //L
            {
                grandfather = bigger.getBiggerChild();


                if (grandfather == bigger.lTree || bigger.getAVLVal() == 0) //LL
                {
                    System.out.println("left left");
                    int r = 4;
                    nodeItem = left(nodeItem);
                    int y = 5;
                }
                else //LR
                {
                    System.out.println("left right");
                    nodeItem = leftRight(nodeItem);
                }
            }
            else  //R
            {

                int x = 5;
                grandfather = bigger.getBiggerChild();
                if (grandfather == bigger.rTree || bigger.getAVLVal() == 0)
                {
                    System.out.println("right right");
                    nodeItem = right(nodeItem);
                }
                else
                {
                    System.out.println("rift left");
                    nodeItem = rightLeft(nodeItem);
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
        }
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
        Integer arr[] = new Integer[6];
        arr[0] = 20;
        arr[1] = 30;
        arr[2] = 34;
        arr[3] = 37;
        arr[4] = 45;
        arr[5] = 70;
//        AvlTree tester = new AvlTree(45);
                AvlTree tester = new AvlTree(45);

        tester.insert(30);
        tester.insert(20);
        tester.insert(37);
        tester.insert(34);
        tester.insert(50);
        tester.insert(70);
        tester.insert(81);
        tester.remove(50);
        System.out.println("done");
        int x = 5;

    }

}
