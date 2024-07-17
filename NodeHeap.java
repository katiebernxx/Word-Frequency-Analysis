/* Katie Bernard
 * 11/28/22
 */
import java.util.Comparator;
import java.util.LinkedList;

public class NodeHeap<T> {

    private class Node{
        Node left;
        Node right;
        Node parent;
        T value;
        public Node(T val){
            value = val;
        }
    }

    private int size;
    private Comparator<T> comparator;
    Node last;
    Node root;

    public NodeHeap(Comparator<T> comparator){
        size = 0;
        this.comparator = comparator;
        root = null;
    }

    public int size(){
        return size;
    }


    
    /* Switches nodes */
    private void swap(Node node1, Node node2){
        T val = node1.value;
        node1.value = node2.value;
        node2.value = val;
    }

    /* adjusts a node up the tree until it reaches a place where it is following the rules
     * of being bigger than everything below it and smaller than everuthing above it.
     */
    private void bubbleUp(Node cur){
        if(cur == root){
            return;
        }

        if(comparator.compare(cur.value, cur.parent.value)>0){
            swap(cur, cur.parent);
            bubbleUp(cur.parent);
        }
    }

    /* Similar to bubble up, but this takes a node and moves it down through the tree until
    it is smaller than everything above it and still bigger than everything below it */
    private void bubbleDown(Node cur){
        if(cur.left == null && cur.right == null){
            return;
        }

        if(cur.right == null){
            if(comparator.compare(cur.value, cur.left.value)<0){
                swap(cur, cur.left);
                bubbleDown(cur.left);
            }
        }

        if(cur.left == null){
            if(comparator.compare(cur.value, cur.right.value)<0){
                swap(cur, cur.right);
                bubbleDown(cur.right);
            }
        }

        if(cur.right!=null && cur.left!=null){
            if(comparator.compare(cur.left.value, cur.right.value)>0){
                swap(cur, cur.left);
                bubbleDown(cur.left);
            }
            else{
                swap(cur, cur.right);
                bubbleDown(cur.right);
            }
        }
        
    }

    /* adding a new item to the heap */
    public void offer(T item){
        Node newNode = new Node(item);
        if(root == null){
            root = newNode;
            last = root;
            size++;
            return;
        }

        if(last == root){
            root.left = newNode;
            root.right = null;
            size++;
            last = root.left;
            last.parent = root;
            if(comparator.compare(root.value, item) < 0){
                swap(root, last);
            }
            return;
        }

        if(last == last.parent.left){
            Node parent = last.parent;
            parent.right = newNode;
            last = parent.right;
            last.parent = parent;
            size++;
            
        }

        else if(last == last.parent.right){
            Node cur = last;
            while(cur!= root && cur.parent.right == cur){
                cur = cur.parent;
            }

            if(cur == root){
                while(cur.left !=null){ 
                    cur = cur.left;
                }
                cur.left = newNode;
                cur.left.parent = cur;
                last = cur.left;
                size++;
            }

            if(cur != root){
                cur = cur.parent.right;
                while(cur.left != null){
                    cur = cur.left;
                }
                cur.left = newNode;
                cur.left.parent = cur;
                last = cur.left;
                size++;
            }
            
        }

        bubbleUp(last);
    
    }

    /* returning the first priority item from the heap */
    public T peek(){
        return root.value;
    }

    /* Returning and removing the first priority item from the heap */
    public T poll(){
        T next = root.value;
        root.value = last.value;
        size --;
        //update my pointer to my last
        bubbleDown(root);
        return next;
    }


    /* Compares key value pairs */
    public class KeyValuePairComparatorByValue<K, V extends Comparable<? super V>>
        implements Comparator<MapSet.KeyValuePair<K, V>> {
    public int compare(MapSet.KeyValuePair<K, V> kvp1, MapSet.KeyValuePair<K, V> kvp2) {
        return kvp1.getValue().compareTo(kvp2.getValue());
    }
    }

    public String toString(){
        String toReturn = "";
        while(peek() != null){
            toReturn += ("" + poll());
        }
        return toReturn;
    }


    public static void main(String[] args){
        NodeHeap<Integer> heap = new NodeHeap<Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
       
        heap.offer(5);
        // System.out.println("Offer 5");
        // System.out.println("root" + heap.root.value);
        // System.out.println();
     

        heap.offer(9);
        // System.out.println("Offer 9");
        // System.out.println("root" + heap.root.value);
        // System.out.println("left" + heap.root.left.value);
        // System.out.println();

        
        heap.offer(1);
        // System.out.println("Offer 1");
        // System.out.println("root" + heap.root.value);
        // System.out.println("left" + heap.root.left.value);
        // System.out.println("right" + heap.root.right.value);
        // System.out.println();

        heap.offer(10);
        // System.out.println("Offer 10");
        // System.out.println("root" + heap.root.value);
        // System.out.println("left" + heap.root.left.value);
        // System.out.println("right" + heap.root.right.value);
        // System.out.println();

        heap.offer(2);
        // System.out.println("Offer 2");
        // System.out.println("root" + heap.root.value);
        // System.out.println("left" + heap.root.left.value);
        // System.out.println("right" + heap.root.right.value);
        // System.out.println();
        // System.out.println();

        //TESTS
        System.out.println("10 = " +heap.peek());
        System.out.println("10 = " +heap.poll());
        System.out.println("9 = " +heap.poll());
        System.out.println("5 = " +heap.poll());
        System.out.println("2 = " +heap.poll());


    }

}