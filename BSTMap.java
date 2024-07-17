/* Katie Bernard
 * 11/28/22
 */
import java.util.ArrayList;
import java.util.Random;
public class BSTMap <K extends Comparable<K>, V> implements MapSet<K, V>{
    //Implements the MapSet interface

    //Class Node
    private class Node{
        Node left;
        Node right;
        KeyValuePair<K, V> kvp;

        public Node(KeyValuePair<K, V> kvp){
            this.kvp = kvp;
            left = null;
            right= null;
        }

        public K getKey(){
            return kvp.getKey();
        }
        
        public V getValue(){
            return kvp.getValue();
        }

        public void setValue(V value){
            kvp.setValue(value);
        }
    }

    //Construct the BST class
    private Node root;
    private int size;
    public ArrayList<Node> nodes;

    public BSTMap(){
        root = null;
        size = 0;
    }

    //Return the size
    public int size(){
        return size;
    }

    //Resets fields to default values
    public void clear(){
        root = null;
        size = 0;
    }

    
    /* 
     * Returns the value to which the specified key is mapped, 
     * or null if this map contains no mapping for the key.
     */
    public V get(K key){
        return get(key, root);
    }

    //get recursive helper function
    private V get(K key, Node cur){
        if (cur == null){
            return null;
        }

        if(key.compareTo(cur.getKey())<0){
            return get(key, cur.left);
        } else if(key.compareTo(cur.getKey()) > 0){
            return get(key, cur.right);
        } else{
            return cur.getValue();
        }
    }

    /**
     *  Associates the specified value with the specified key in this map. 
     * If the map previously contained a mapping for the key, the old value is replaced.
     * Does nothing if value is null. Returns the previous value associated with key,
     * or null if there was no mapping for key.
     */
    public V put(K key, V value){
        if(root == null){
            Node newNode = new Node(new KeyValuePair<K, V> (key, value));
            root = newNode;
            size++;
        }
        return put(key, value, root);
    }

    //Put helper recursive function
    private V put(K key, V value, Node cur) {
        
        if (key.compareTo(cur.getKey()) < 0){
            if (cur.left != null){
                return put(key, value, cur.left);
            } else {
                Node newNode = new Node(new KeyValuePair<K,V>(key, value));
                cur.left = newNode;
                size++;
                return null;

            }
        } else if (key.compareTo(cur.getKey()) > 0){
            if (cur.right != null){
                return put(key, value, cur.right);
            } else {
                Node newNode = new Node(new KeyValuePair<K,V>(key, value));
                cur.right = newNode;
                size++;
                return null;
            }
        } else { // in this case, cur.getKey() == key
            V oldVal = cur.getValue();
            cur.setValue(value);
            return oldVal;
        }
    }

    
    /* Returns true if this map contains a mapping for the specified key to a value.
     */
    public boolean containsKey(K key){
        Node cur = root;//begins at the root

        //repeating while loop until returns true or false
        if(root != null){ 
            while(true){ 
                if (key.compareTo(cur.getKey()) < 0){
                    if (cur.left != null){
                        cur = cur.left;
                    } else {
                        return false;
                    }
                } else if (key.compareTo(cur.getKey()) > 0){
                    if (cur.right != null){
                        cur = cur.right;
                    } else {
                        return false;
                    }
                } else { // in this case, cur.getKey() == key
                    if(cur.getValue() != null){
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        } return false;
    }

    
    /* 
    *Returns an ArrayList of all the keys in the map in sorted order from least to greatest
     */
    public ArrayList<K> keySet(){
        ArrayList<K> keys = new ArrayList<K>();
        keySet(root, keys);
        return keys;
    }

    //KeySet's helper recursive function
    private void keySet(Node cur, ArrayList<K> output){
        if (cur == null){
            return;
        }

        keySet(cur.left, output);
        output.add(cur.getKey());
        keySet(cur.right, output);
    }
   
    /* 
     * Returns an ArrayList of all the values in the map in the same order returned by keySet()
     */
    public ArrayList<V> values(){
        ArrayList<K> keys = keySet();
        ArrayList<V> values = new ArrayList<V>();
        for(int i = 0; i<keys.size(); i++){
            K key = keys.get(i);
            V value = get(key);
            values.add(value);
        }
        return values;
    }

    /*
    * Returns an ArrayList of each KeyValuePair in the map in the same order as the keys as returned by keySet().
    */
    public ArrayList<KeyValuePair<K, V>> entrySet(){
        ArrayList<K> keys = keySet();
        ArrayList<V> values = values();
        ArrayList<KeyValuePair<K,V>> kvps = new ArrayList<KeyValuePair<K,V>>();
        for(int i = 0; i<keys.size(); i++){
            K key = keys.get(i);
            V value = values.get(i);
            KeyValuePair<K,V> kvp = new KeyValuePair<K,V>(key, value);
            kvps.add(kvp);
        }
        return kvps;
    }

    /*
     * preorder traversal of the tree: starts at root, moves left, then right. Processes current node before anything else
     * outputs an arraylist of strings that contain the key and the value
     */
    public ArrayList<String> preOrderTraversal(){
        ArrayList<String> output = new ArrayList<String>();
        preOrderTraversal(root, output);
        return output;
    }

    //recursive helper method
    public void preOrderTraversal(Node cur, ArrayList<String> output){
        if (cur == null){
            return;
        }

        output.add(new String ("" + cur.getKey() +"  " + cur.getValue()));
        preOrderTraversal(cur.left, output);
        preOrderTraversal(cur.right, output);
    }

    public int depth(){
        if(root == null){
            return 0;
        }
        else{
            return depth(root);
        }
    }

    private int depth(Node node){
        if (node == null)
            return 0;
        else {
            //get depth of each subtree
            int lDepth = depth(node.left);
            int rDepth = depth(node.right);
  
            //use the bigger subtree
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }

    @Override
    public int collisions(){
        return 0;
    }
    
    /* 
     * To String method
     */
    public String toString(){
        return toString(root, 0, "root");
    }

    //recursive helper method
    private String toString(Node cur, int depth, String direction){
        if(cur == null){
            return "";
        }

        String left = toString(cur.left, depth+1, "left");
        String myself = direction + '\t' + "   ".repeat(depth) + cur.kvp + '\n';
        String right = toString(cur.right, depth + 1, "right");

        return right + myself + left;

    }

    public static void main(String[] args){
        //TESTS
        BSTMap bst = new BSTMap();
        bst.put("banana", 2);
        bst.put("apple", 8);
        bst.put("carrot", 1);
        bst.put("strawberry", 10);
        System.out.println("BST toString method: " + "\n" + bst);
        System.out.println("2 = " + bst.get("banana"));
        System.out.println("4 = " + bst.size());
        System.out.println("true = " + bst.containsKey("carrot"));
        System.out.println("false = " + bst.containsKey("blueberry"));
        System.out.println("Array of keys: " + bst.keySet());
        System.out.println("Array of values: " + bst.values());
        System.out.println("Array of Key Value Pairs: " + bst.entrySet());
        System.out.println("Array of Strings in pre order traversal order: " + bst.preOrderTraversal());
        bst.clear();
        System.out.println("Empty tree: " + "\n" + bst);

    }

      
}
