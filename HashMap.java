/* Katie Bernard
 * 11/28/22
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class HashMap<K, V> implements MapSet<K, V>{
    LinkedList<KeyValuePair<K, V>>[] map;
    int size;
    int capacity;
    int resetCapacity;
    double loadingFactor;
    int collisions;

    @SuppressWarnings ("unchecked")
    public HashMap(int initialCapacity){
        map = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[initialCapacity];
        capacity = initialCapacity;
        resetCapacity = initialCapacity;
        loadingFactor = 0.75;
        collisions = 0;
    }

    @SuppressWarnings ("unchecked")
    public HashMap(){
        capacity = 100;
        map = (LinkedList<KeyValuePair<K,V>>[]) new LinkedList[capacity];
        resetCapacity = 100;
        loadingFactor = 0.75;
        collisions = 0;
    }

    private int capacity(){
        return map.length;
    }

    //Generates a hash
    private int hash(K key){
        return Math.abs(key.hashCode() % capacity()); //this returns a value between 0 and capacity()-1, inclusive
    }

    //Putsa kvp into the map
    @SuppressWarnings ("unchecked")
    public V put(K key, V value) {
       int index = hash(key);

       if(map[index]==null){
            map[index] = new LinkedList<KeyValuePair<K, V>>();
            map[index].add(new KeyValuePair<K, V>(key, value));
            size++;
            return null;
       } else{
            for(KeyValuePair<K,V> kvp : map[index]){
                V oldValue = kvp.getValue();
                if (kvp.getKey().equals(key)){
                    kvp.setValue(value);
                    return oldValue;
                }
            } map[index].add(new KeyValuePair<K,V>(key, value));
            size++;
            collisions++;

            if(size > loadingFactor * capacity()) resizeUp();
 
            }
            return null;
       }

       //increases the length of the array
       private void resizeUp(){
        collisions = 0;
        ArrayList<KeyValuePair<K, V>> kvps = entrySet();
        map = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[(int) (capacity() / loadingFactor)];
        size = 0;
        for(KeyValuePair<K, V> kvp : kvps){
            put(kvp.getKey(), kvp.getValue());
        }
    }

    //returns true if it contains the key
    public boolean containsKey(K key) {
        int index = hash(key);
        if(map[index] == null){
            return false;
        } else{
            for(KeyValuePair<K,V> kvp : map[index]){
                if(kvp.getKey().equals(key)){
                    return true;
                }
            }
            return false;
        }
    }

    //returns the value associated with the key
    public V get(K key) {
        int index = hash(key);

        if(map[index] == null){
            return null;
        } else{
            for(KeyValuePair<K,V> kvp : map[index]){
                if(kvp.getKey().equals(key)){
                    return kvp.getValue();
                }
            } return null;
        }

    }

    //returns a list of kvps
    public ArrayList<MapSet.KeyValuePair<K, V>> entrySet() {
        ArrayList<MapSet.KeyValuePair<K, V>> toReturn = new ArrayList<MapSet.KeyValuePair<K, V>>();
        for(int i = 0; i<map.length; i++){
            if(map[i] != null){ 
                for(KeyValuePair<K,V> kvp : map[i]){
                    toReturn.add(kvp);
                }
            }

        }
        return toReturn;
    }

    //returns a list of keys
    public ArrayList<K> keySet() {
        ArrayList<K> toReturn = new ArrayList<K>();
        ArrayList<MapSet.KeyValuePair<K, V>> pairs = entrySet();
        for(KeyValuePair<K, V> pair : pairs){
            toReturn.add(pair.getKey());
        }
        return toReturn;
    }

//returns a list of values
    public ArrayList<V> values() {
        ArrayList<V> toReturn = new ArrayList<V>();
        ArrayList<MapSet.KeyValuePair<K, V>> pairs = entrySet();
        for(KeyValuePair<K, V> pair : pairs){
            toReturn.add(pair.getValue());
        }
        return toReturn;
    }


    public int size() {
        return size;
    }

    public int collisions(){
        return collisions;
    }

    public void clear() {
       size = 0;
       capacity = resetCapacity;
       map = (LinkedList<KeyValuePair<K,V>>[]) new LinkedList[capacity];

       
    }

    //removes a kvp
    public K remove(K key){
        int index = hash(key);
        if(map[index] == null){
            return null;
        } else{
            for(KeyValuePair<K,V> kvp : map[index]){
                if(kvp.getKey().equals(key)){
                    K k = kvp.getKey();
                    map[index].remove(kvp);
                    return k;
                }
            }
            return null;
        }    
    }

    @Override
    public int depth(){
        return 0;
    }

    public String toString(){
        String toReturn = "";
        for (int i = 0; i<map.length-1; i++){
            toReturn = toReturn + ("" + map[i]);
        } 
        return toReturn;
    }

    public static void main(String[] args) throws IOException{
        HashMap<String, Integer> hm = new HashMap<String, Integer>(5);
        FileReader fr = new FileReader("wordCount.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while(line != null){ 
            String[] words = line.split("[^a-zA-Z0-9']");
            ArrayList<String> realWords = new ArrayList<String>();
            for (int i = 0; i < words.length; i++) {
                String item = words[i].trim().toLowerCase();
                if(item.length() != 0){
                    realWords.add(item);
                }
            }
                String word = realWords.get(0);
                int value = Integer.parseInt(realWords.get(1));
                hm.put(word, value);
                line = br.readLine();

            }
        
        br.close();
        
        System.out.println("6 = " + hm.get("was"));
        System.out.println("true = " + hm.containsKey("worst"));
        System.out.println("false = " + hm.containsKey("hello"));
        System.out.println("list of values " + hm.values());
        System.out.println("list of kvps " + hm.entrySet());
        System.out.println("list of keys after removing " + hm.remove("times") + " : " + hm.keySet());
        System.out.println(hm); 
        System.out.println(hm.size());       
    }
}




    