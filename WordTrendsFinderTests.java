/* Katie Bernard
 * 11/28/22
 * TEST FILE
 */
public class WordTrendsFinderTests{
    public static void main(String[] args){
        //Case 1: constructor
        {
            //setup
            String[] info = {"reddit_comments_", "2008", "2010", "sony", "portal", "ipad"};
            WordTrendsFinder wtf = new WordTrendsFinder( info);

            //verify
            System.out.println(wtf);

            //assert
            assert wtf != null : "Problem in constructor";
        }

        //Case 2: getFreqs
        {
            //setup
            String[] info = {"reddit_comments_", "2008", "2010", "sony", "portal", "ipad"};
            WordTrendsFinder wtf = new WordTrendsFinder( info);

            //verify
            System.out.println(wtf.getFreqs());

            //assert
            assert wtf.getFreqs() != null : "Problem in getFreqs";
        }
}
}