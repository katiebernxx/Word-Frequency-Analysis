/* Katie Bernard
 * 11/28/22
 * TEST FILE
 */
import java.util.Comparator;
public class HeapTests {

    public static <T> void main(String[] args){
        // case 1: Constructor
        {
            // setup
            Heap<Integer> heap = new Heap<Integer>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

            // verify
            System.out.println(heap);

            // test
            assert heap != null : "Error in Heap::heap()";
        }

        
        // case 2: offer / peek - they have to be tested together
        {
            // setup
            Heap<Integer> heap1 = new Heap<Integer>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

            Heap<Integer> heap2 = new Heap<Integer>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

            heap1.offer(6);
            heap2.offer(7);
            heap2.offer(1);

            // verify
            System.out.println("6 = " + heap1.peek());
            System.out.println("7 = " + heap2.peek());
            heap2.offer(9);
            System.out.println("9 = " + heap2.peek());

            // test
            assert heap1.peek() == 6 : "Error in Heap::offer or peek()";
            assert heap2.peek() == 9 : "Error in Heap::offer or peek()";

        }

        // case 3: Size getter
        {
            // setup
            Heap<Integer> heap1 = new Heap<Integer>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

            Heap<Integer> heap2 = new Heap<Integer>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

            heap2.offer(6);
            heap2.offer(7);
            heap2.offer(1);

            // verify
            System.out.println("0 = " + heap1.size());
            System.out.println("3 = " + heap2.size());

            // test
            assert heap1.size() == 0 : "Error in Heap::size()";
            assert heap2.size() == 3 : "Error in Heap::size()";

        }

        // case 4: Capacity
        {
            // setup
            Heap<Integer> heap1 = new Heap<Integer>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

            Heap<Integer> heap2 = new Heap<Integer>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

            Heap<Integer> heap3 = new Heap<Integer>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

            for(int i = 0; i<17; i++){
                heap2.offer(i);
            }
            
            // verify
            System.out.println("16 = " + heap1.capacity());
            System.out.println("32 = " + heap2.capacity());

            // test
            assert heap1.capacity() == 16 : "Error in Heap::capacity()";
            assert heap2.capacity() == 32 : "Error in Heap::capacity()";

        }

          // case 5: poll
          {
            // setup
            Heap<Integer> heap1 = new Heap<Integer>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

            Heap<Integer> heap2 = new Heap<Integer>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

            heap1.offer(6);
            heap1.offer(0);
            heap1.offer(45);
            heap2.offer(7);
            heap2.offer(1);
            heap2.offer(5);
            heap2.offer(18);

            // verify
            System.out.println("45 = " + heap1.poll());
            System.out.println("6 = " + heap1.poll());
            System.out.println("18 = " + heap2.poll());
            System.out.println("7 = " + heap2.poll());
            System.out.println("5 = " + heap2.poll());
            

            // test
            assert heap1.poll() == 0 : "Error in Heap::poll()";
            assert heap2.poll() == 1 : "Error in Heap::poll()";

        }

         
        System.out.println("Tests all done!");
    }

}