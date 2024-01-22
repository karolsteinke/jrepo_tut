public class DataStructure {
    
    //Creata an array
    private final static int SIZE = 15;
    private int[] arrayOfInts = new int[SIZE];

    //CONSTRUCTOR - fill the array
    public DataStructure() {
        for (int i=0; i<SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }

    //Print out values of even indices of the array
    //Use iterator which is implemented as inner class
    public void printEven() {
        DataStructureIterator iterator = this.new EvenIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    //Inner class implements the DataStructureIterator interface
    //which extends the Iterator<Integer> interface
    //It's an example for helper inner class
    interface DataStructureIterator extends java.util.Iterator<Integer> { }
    private class EvenIterator implements DataStructureIterator {
        
        private int nextIndex = 0;

        public boolean hasNext() {
            //Check if the current element is the last in the array
            return (nextIndex <= SIZE - 1);
        }

        public Integer next() {
            //note that inner class refer to the arrayOfInts DIRECTLY
            Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);
            //Get the next even element
            nextIndex += 2;
            return retValue;
        }
    }

    public static void main(String[] args) throws Exception {
        DataStructure ds = new DataStructure();
        ds.printEven();
    }
}
