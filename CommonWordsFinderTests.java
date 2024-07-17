/* Katie Bernard
 * 11/28/22
 * TESTFILE
 */
import java.util.ArrayList;

public class CommonWordsFinderTests{
    public static void main(String[] args){
        //Case 1: constructor
        {
            //setup
            CommonWordsFinder cwf = new CommonWordsFinder("counttest.txt");

            //verify
            System.out.println(cwf);

            //assert
            assert cwf != null : "Problem in constructor";
        }

        //Case 2: readWords
        {
            //setup
            CommonWordsFinder cwf = new CommonWordsFinder("counttest.txt");

            //verify
            System.out.println(cwf.words);

            //assert
            assert cwf.words != null : "Problem in readWords";
        }

        //Case 3: buildBST
        {
            //setup
            CommonWordsFinder cwf = new CommonWordsFinder("counttest.txt");
            
            //verify
            System.out.println(cwf.bst);

            //assert
            assert cwf.bst != null : "Problem in buildBST";
        }

        //Case 4: buildHeap
        {
            //setup
            CommonWordsFinder cwf = new CommonWordsFinder("counttest.txt");
            
            //verify
            System.out.println(cwf.heap);

            //assert
            assert cwf.heap != null : "Problem in buildHeap";
        }

        System.out.println("Tests All Done!");
    }
}