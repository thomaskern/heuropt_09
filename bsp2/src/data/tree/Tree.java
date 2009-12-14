package data.tree;

import data.Node;

import java.util.Stack;               // for Stack class

public class Tree {

    class TreeNode {

        public Node dData;           // data item
        public TreeNode leftChild;         // this node's left child
        public TreeNode rightChild;        // this node's right child

        public String toString()      // display ourself
        {
            String s = "{";
            s += dData.getId();
            s += ", ";
            s += dData;
            s += "} ";
            return s;
        }

        public int getId() {
            return dData.getId();
        }
    }


    private TreeNode root;             // first node of tree

    // -------------------------------------------------------------
    public Tree()                  // constructor
    {
        root = null;
    }            // no nodes in tree yet

    // -------------------------------------------------------------
    public TreeNode find(int key)      // find node with given key
    {                           // (assumes non-empty tree)
        TreeNode current = root;               // start at root
        while (current.getId() != key)        // while no match,
        {
            if (key < current.getId())         // go left?
                current = current.leftChild;
            else                            // or go right?
                current = current.rightChild;
            if (current == null)             // if no child,
                return null;                 // didn't find it
        }
        return current;                    // found it
    }

    // -------------------------------------------------------------
    public void insert(Node node) {
        TreeNode newTreeNode = new TreeNode();
        newTreeNode.dData = node;
        if (root == null)
            root = newTreeNode;
        else
        {
            TreeNode current = root;       // start at root
            TreeNode parent;
            while (true)                // (exits internally)
            {
                parent = current;
                if (node.getId() < current.getId())  // go left?
                {
                    current = current.leftChild;
                    if (current == null)  // if end of the line,
                    {                 // insert on left
                        parent.leftChild = newTreeNode;
                        return;
                    }
                }  // end if go left
                else                    // or go right?
                {
                    current = current.rightChild;
                    if (current == null)  // if end of the line
                    {                 // insert on right
                        parent.rightChild = newTreeNode;
                        return;
                    }
                }  // end else go right
            }  // end while
        }  // end else not root
    }  // end insert()

    // -------------------------------------------------------------
    public boolean delete(int key) // delete node with given key
    {                           // (assumes non-empty list)
        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeftChild = true;

        while (current.getId() != key)        // search for node
        {
            parent = current;
            if (key < current.getId())         // go left?
            {
                isLeftChild = true;
                current = current.leftChild;
            } else                            // or go right?
            {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null)             // end of the line,
                return false;                // didn't find it
        }  // end while
        // found node to delete

        // if no children, simply delete it
        if (current.leftChild == null &&
                current.rightChild == null) {
            if (current == root)             // if root,
                root = null;                 // tree is empty
            else if (isLeftChild)
                parent.leftChild = null;     // disconnect
            else                            // from parent
                parent.rightChild = null;
        }

        // if no right child, replace with left subtree
        else if (current.rightChild == null)
            if (current == root)
                root = current.leftChild;
            else if (isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;

            // if no left child, replace with right subtree
        else if (current.leftChild == null)
            if (current == root)
                root = current.rightChild;
            else if (isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;

        else  // two children, so replace with inorder successor
        {
            // get successor of node to delete (current)
            TreeNode successor = getSuccessor(current);

            // connect parent of current to successor instead
            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;

            // connect successor to current's left child
            successor.leftChild = current.leftChild;
        }  // end else two children
        // (successor cannot have a left child)
        return true;                                // success
    }

    // -------------------------------------------------------------
    // returns node with next-highest value after delTreeNode
    // goes to right child, then right child's left descendents
    private TreeNode getSuccessor(TreeNode delTreeNode) {
        TreeNode successorParent = delTreeNode;
        TreeNode successor = delTreeNode;
        TreeNode current = delTreeNode.rightChild;   // go to right child
        while (current != null)               // until no more
        {                                 // left children,
            successorParent = successor;
            successor = current;
            current = current.leftChild;      // go to left child
        }
        // if successor not
        if (successor != delTreeNode.rightChild)  // right child,
        {                                 // make connections
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delTreeNode.rightChild;
        }
        return successor;
    }

    // -------------------------------------------------------------
    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder traversal:  ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println();
    }

    // -------------------------------------------------------------
    private void preOrder(TreeNode localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.getId() + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    // -------------------------------------------------------------
    private void inOrder(TreeNode localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.getId() + " ");
            inOrder(localRoot.rightChild);
        }
    }

    // -------------------------------------------------------------
    private void postOrder(TreeNode localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.getId() + " ");
        }
    }

    // -------------------------------------------------------------
    public void displayTree() {
        Stack<TreeNode> globalStack = new Stack<TreeNode>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
                "......................................................");
        while (!isRowEmpty) {
            Stack<TreeNode> localStack = new Stack<TreeNode>();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++)
                System.out.print(' ');

            while (!globalStack.isEmpty()) {
                TreeNode temp = globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.getId());
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.leftChild != null ||
                            temp.rightChild != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++)
                    System.out.print(' ');
            }  // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while (!localStack.isEmpty())
                globalStack.push(localStack.pop());
        }  // end while isRowEmpty is false
        System.out.println(
                "......................................................");
    }

    public double cost(){
        return 0;
    }

    public boolean valid(){
        return false;
    }
}
