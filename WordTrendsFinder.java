/* Katie Bernard
 * 11/28/2022
 */

//Command line arguments should be formatted in this order:
//<BaseFilename> <FileNumberBegin> <FileNumberEnd> <Word1> <Word2> ...

import java.util.ArrayList;

public class WordTrendsFinder{
    String baseFile;
    int startNum;
    int endNum;
    ArrayList<String> files;
    ArrayList<String> words;
    ArrayList<Integer> years;  

    public WordTrendsFinder(String[] info){
        baseFile = info[0];
        startNum = Integer.parseInt(info[1]);
        endNum = Integer.parseInt(info[2]);
        words = new ArrayList<String>();
        for(int i = 3; i<info.length; i++){
            words.add(info[i]);
        }

        files = new ArrayList<String>();
        years = new ArrayList<Integer>();
        for(int i = startNum; i<endNum + 1; i++){
            String newFile = ("" + baseFile + i + ".txt");
            files.add(newFile);
            years.add(i);
        }
    }

    //Returns an array list of mini arrays of length 3 for each word in each year
    // containing the word, the frequency, and the year
    public ArrayList<String[]> getFreqs(){
        ArrayList<String[]> wfyTriads = new ArrayList<String[]>();
    
        //For each year:
        for(int i = 0; i < files.size(); i ++){

            String file = files.get(i);
            CommonWordsFinder cwf = new CommonWordsFinder(file);
            cwf.buildBST(cwf.readWords());

            //For each word
            for(String word : words){
                double freq;
                if(cwf.bst.containsKey(word)){
                    freq = cwf.bst.get(word) / (double) cwf.bst.size();
                } else {freq = 0; }
                System.out.println("" + word + " : " + freq + " : " + years.get(i));
                String[] wfy = new String[3];
                wfy[0]=(word); wfy[1]=("" + freq); wfy[2]=("" + years.get(i));
                wfyTriads.add(wfy);
            }

        } return wfyTriads;
        
    }

    public static void main(String[] args){
        WordTrendsFinder wtf = new WordTrendsFinder(args);
        wtf.getFreqs();
    }

}