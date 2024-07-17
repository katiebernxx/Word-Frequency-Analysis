/* Katie Bernard
 * 11/28/22
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class NodeCommonWordsFinder{

    String file;
    int wordCount;
    ArrayList<String> words;
    BSTMap<String, Integer> bst;
    Heap<MapSet.KeyValuePair<String,Double>> heap;

    public NodeCommonWordsFinder(String filename){
        file = filename;
        wordCount = 0;
        bst = new BSTMap<String, Integer>();
        words = readWords();
        buildBST(words);
        heap = new Heap<MapSet.KeyValuePair<String,Double>>(new Comparator<MapSet.KeyValuePair<String,Double>>(){
            @Override
            public int compare(MapSet.KeyValuePair<String, Double> kvp1, MapSet.KeyValuePair<String, Double> kvp2) {
                return kvp1.getValue().compareTo(kvp2.getValue());
            }
            });
        buildHeap(bst);


    }

    /**
     * Generates an array list of words from a given file
     */
    public ArrayList<String> readWords(){
        ArrayList<String> toReturn = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            // split line into words.
            
            while(line != null){ 
                String[] words = line.split("[^a-zA-Z0-9']");
                for (int i = 0; i < words.length; i++) {
                    String word = words[i].trim().toLowerCase();

                    //updates map with each word
                    if(word.length() != 0){
                        wordCount++;
                        toReturn.add(word);               
                    }
                }
                line = br.readLine();
            } br.close();
            return toReturn;
        } catch (FileNotFoundException ex) {
            System.out.println("WordCounter.analyze():: unable to open file " + file);
            return toReturn;
        } catch (IOException ex) {
            System.out.println("WordCounter.analyze():: error reading file " + file);
            return toReturn;
        }
    }

    public void buildBST(ArrayList<String> words){

        for(String word : words){
            if(bst.containsKey(word)){
                int value = bst.get(word);
                bst.put(word, value+1); 
            } else{
                bst.put(word, 1);
            }     
        }   
        }


    public void buildHeap(BSTMap<String, Integer> bst){
        double initialTime = System.nanoTime();

        ArrayList<MapSet.KeyValuePair<String, Integer>> kvps = bst.entrySet();
        int size = bst.size();
        for(MapSet.KeyValuePair<String, Integer> kvp : kvps){

            double val = kvp.getValue();
            double freq = val / (double)size;
            MapSet.KeyValuePair<String, Double> newKvp = new MapSet.KeyValuePair(kvp.getKey(), freq);
            heap.offer(newKvp);
            wordCount++;
        }

        double finalTime = System.nanoTime();
        double difference = finalTime - initialTime;
        double milis = difference * 1000000;
        System.out.println("Runtime : " + milis);
        
    }

    public static void main(String[] args){

        NodeCommonWordsFinder cwf2008 = new NodeCommonWordsFinder("reddit_comments_2008.txt");
        System.out.println("10 Top Common Words in 2008");
        for(int i = 0; i<10; i++){ 
            System.out.print(cwf2008.heap.poll().getKey() + ", ");
        }
      

        System.out.println();

        CommonWordsFinder cwf2009 = new CommonWordsFinder("reddit_comments_2009.txt");
        System.out.println("10 Top Common Words in 2009");
        for(int i = 0; i<10; i++){ 
            System.out.print(cwf2009.heap.poll().getKey() + ", ");
        }

        System.out.println();

        CommonWordsFinder cwf2010 = new CommonWordsFinder("reddit_comments_2010.txt");
        System.out.println("10 Top Common Words in 2010");
        for(int i = 0; i<10; i++){ 
            System.out.print(cwf2010.heap.poll().getKey() + ", ");
        }

        System.out.println();

        CommonWordsFinder cwf2011 = new CommonWordsFinder("reddit_comments_2011.txt");
        System.out.println("10 Top Common Words in 2011");
        for(int i = 0; i<10; i++){ 
            System.out.print(cwf2011.heap.poll().getKey() + ", ");
        }

        System.out.println();

        CommonWordsFinder cwf2012 = new CommonWordsFinder("reddit_comments_2012.txt");
        System.out.println("10 Top Common Words in 2012");
        for(int i = 0; i<10; i++){ 
            System.out.print(cwf2012.heap.poll().getKey() + ", ");
        }

        System.out.println();

        CommonWordsFinder cwf2013 = new CommonWordsFinder("reddit_comments_2013.txt");
        System.out.println("10 Top Common Words in 2013");
        for(int i = 0; i<10; i++){ 
            System.out.print(cwf2013.heap.poll().getKey() + ", ");
        }

        System.out.println();

        CommonWordsFinder cwf2014 = new CommonWordsFinder("reddit_comments_2014.txt");
        System.out.println("10 Top Common Words in 2014");
        for(int i = 0; i<10; i++){ 
            System.out.print(cwf2014.heap.poll().getKey() + ", ");
        }

        System.out.println();

        CommonWordsFinder cwf2015 = new CommonWordsFinder("reddit_comments_2015.txt");
        System.out.println("10 Top Common Words in 2015");
        for(int i = 0; i<10; i++){ 
            System.out.print(cwf2015.heap.poll().getKey() + ", ");
        }


    }
    

}
