public class DataStructure {
    
    //Define an array
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
    /*
    public void printEven() {
        DataStructureIterator iterator = this.new EvenIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
    */

    //helper inner class
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

    //Exercise 2.a
    public void print(DataStructureIterator iterator) {
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    //Exercise 2.c
    public void printC(java.util.function.Function<Integer, Boolean> iterator) {
        for (int i=0; i<SIZE; i++) {
            if (iterator.apply(i)) {
                System.out.print(arrayOfInts[i] + " ");
            }
        }
        System.out.println();
    }

    //Exercise 2.d
    public static boolean isEvenIndex(int index) {
        return index % 2 == 0;
    }
    
    public static boolean isOddIndex(int index) {
        return index % 2 == 1;
    }


    // ******************** STATIC MAIN ********************
    public static void main(String[] args) throws Exception {
        DataStructure ds = new DataStructure();
        
        //Exercise 2.a - iterator instantiated as inner class
        System.out.println("*** Exercise 2.a ***");
        EvenIterator iterator = ds.new EvenIterator();
        ds.print(iterator);

        //Exercies 2.b - iterator defined and instantiated as anonymous class
        System.out.println("*** Exercise 2.b ***");
        DataStructureIterator evenIterator = new DataStructureIterator() {
            private int nextIndex = 0;
    
            public boolean hasNext() {
                //Check if the current element is the last in the array
                return (nextIndex <= SIZE - 1);
            }
    
            public Integer next() {
                //note that inner class refer to the arrayOfInts DIRECTLY
                Integer retValue = Integer.valueOf(ds.arrayOfInts[nextIndex]);
                //Get the next even element
                nextIndex += 2;
                return retValue;
            }
        };
        ds.print(evenIterator);

        //Exercise 2.c - lambda represents functional interface, it defines what 'apply' method do
        System.out.println("*** Exercise 2.c ***");
        ds.printC(a -> a % 2 == 0);
        ds.printC(a -> a % 2 == 1);

        //Exercise 2.d - method reference
        System.out.println("*** Exercise 2.d ***");
        ds.printC(DataStructure::isEvenIndex);
        ds.printC(DataStructure::isOddIndex);
    }
}
