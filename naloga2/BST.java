package naloga2;

public class BST {
    class ElementBST {

        public int value;
        public int counter;
        public ElementBST left, right;

        public ElementBST(int e) {
            this(e, null, null);
        }

        public ElementBST(int e, ElementBST lt, ElementBST rt) {
            value = e;
            counter = 1;
            left = lt;
            right = rt;
        }
    }

    ElementBST rootNode;
    private boolean deleteLeftChild = true;
    private  int comparisons;

    public BST() {
        rootNode = null;
        comparisons = 0;
    }

    private ElementBST insertLeaf(int e, ElementBST node) {
        if (node == null)
            return new ElementBST(e);

        comparisons++;
        if (e < node.value) {
            //comparisons++; //LAST CHANGE
            node.left = insertLeaf(e, node.left);
        } else if (e > node.value) {
          // comparisons+=2; //LAST CHANGEEEEEEE
            node.right = insertLeaf(e, node.right);
        } else {
           // comparisons+=2;
            node.counter++;
        }
        return node;
    }

    private boolean member(int e, ElementBST node) {
        if (node == null)
            return false;

        comparisons++;
        if (e == node.value) {
            return true;
        } else if (e < node.value) {
           // comparisons++;
            return member(e, node.left);
        } else {
           // comparisons++;
            return member(e, node.right);
        }
    }

    private int[] findSmallestValue(ElementBST element) {
        if (element.left == null) {
            return new int[]{element.value, element.counter};
        }
        return findSmallestValue(element.left);
    }

    private int[] findBiggestValue(ElementBST element) {
        if (element.right == null) {
            return new int[]{element.value, element.counter};
        }
        return findBiggestValue(element.right);
    }

    private ElementBST delete(int e, ElementBST node) {
        if (node == null) {
            return null;
        }

       // comparisons++;
        if (e == node.value) {
            if (node.counter > 1) {
                node.counter--;
            }

            else {
                comparisons++;
                if (node.left == null && node.right == null) {
                    return null;
                }
                if (node.right == null) {
                    return node.left;
                }
                if (node.left == null) {

                    return node.right;
                }
                if (deleteLeftChild) {
                    deleteLeftChild = false;
                    int[] biggestNodeParams = findBiggestValue(node.left);
                    node.value = biggestNodeParams[0];
                    node.counter = biggestNodeParams[1];
                    node.left = unConditionalDelete(biggestNodeParams[0], node.left);

                }
                else {
                    deleteLeftChild = true;
                    int[] smallestNodeParams = findSmallestValue(node.right);
                    node.value = smallestNodeParams[0];
                    node.counter = smallestNodeParams[1];
                    node.right = unConditionalDelete(smallestNodeParams[0], node.right);
                }
            }
            return node;
        }
       // comparisons++;
        if (e < node.value) {
           // comparisons++;
            node.left = delete(e, node.left);
            return node;
        }
        comparisons++;
        node.right = delete(e,node.right);
        return node;
    }

    private ElementBST unConditionalDelete(int e, ElementBST node) {
        if (node == null) {
            return null;
        }
        //comparisons++;
        if (e == node.value) {
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            //comparisons++;
            return node;
        }

        //comparisons++;
        if (e < node.value) {
            node.left = unConditionalDelete(e, node.left);
            return node;
        }
        node.right = unConditionalDelete(e,node.right);
        return node;
    }

    private void printInorder(ElementBST node) {
        if (node == null) {
            return;
        }

        printInorder(node.left);
        System.out.println(node.value);
        printInorder(node.right);
    }

    private void printPreorder(ElementBST node) {
        if (node == null) {
            return;
        }
        System.out.println(node.value);
        printPreorder(node.left);
        printPreorder(node.right);
    }

    private void printPostorder(ElementBST node) {
        if (node == null) {
            return;
        }
        printPostorder(node.left);
        printPostorder(node.right);
        System.out.println(node.value);
    }

    public void printPostorder() {
        printPostorder(rootNode);
    }

    public void printPreorder() {
        printPreorder(rootNode);
    }

    public void printInorder() {
        printInorder(rootNode);

    }

    public void printNodeComparisons() {
        System.out.println(comparisons);
    }

    public void delete(int e) {
        rootNode = delete(e, rootNode);
    }

    public boolean find(int e) {
        return member(e, rootNode);
    }

    public void insert(int e) {
        rootNode = insertLeaf(e, rootNode);
    }


    public static void main(String[] args) {

        BST b = new BST();

        b.insert(19);
        b.printNodeComparisons();
        System.out.println("--");

        b.insert(11); b.insert(23); b.insert(31); b.insert(42); b.insert(29);
        System.out.println( b.find(29) );
        System.out.println("--");

        b.insert(23); b.insert(29); b.delete(31);
        System.out.println( b.find(31) );
        System.out.println( b.find(23) );
        System.out.println("--");
        b.printInorder();
        System.out.println("--");

        b.printNodeComparisons();




    }

}
