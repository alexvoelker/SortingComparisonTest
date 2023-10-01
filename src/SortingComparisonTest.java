import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SortingComparisonTest {
    public static void main(String[] args) {
        comparisonTest(1_000_000);
//        testSort(1000);
    }

    /** Test if the sorting algorithm was properly implemented.
     * */
    public static void testSort(int num) {
        SelectionSort testSelectionSort = new SelectionSort(generateRandomData(num));
        testSelectionSort.run();
        System.out.println("Selection Sort");
        printIsInOrder(testSelectionSort.data);

        BubbleSort testBubbleSort = new BubbleSort(generateRandomData(num));
        testBubbleSort.run();
        System.out.println("Bubble Sort");
        printIsInOrder(testBubbleSort.data);

        MergeSort testMergeSort = new MergeSort(generateRandomData(num));
        testMergeSort.run();
        System.out.println("Merge Sort");
        printIsInOrder(testMergeSort.data);

        QuickSort testQuickSort = new QuickSort(generateRandomData(num));
        testQuickSort.run();
        System.out.println("Quick Sort");
        printIsInOrder(testQuickSort.data);
    }

    /** A multithreaded program to perform multiple different
     *  types of sorting algorithms at once.
     */
    public static void comparisonTest(int maxSize) {

        System.out.println("+--------------+----------------+---------------+--------------+--------------+");
        System.out.println("|  Array Size  | Insertion Sort |  Bubble Sort  |  Merge Sort  |  Quick Sort  |");
        System.out.println("+--------------+----------------+---------------+--------------+--------------+");
        // Program will sort arrays of random integers
        // Arrays will start at size 10, and increase exponentially to 100,000,000 (10^8)

        int size = 10;

        while (size <= maxSize) {
            ExecutorService threadPool = Executors.newFixedThreadPool(4);

            int[] data = generateRandomData(size);
            SelectionSort selectionSort = new SelectionSort(data);
            BubbleSort bubbleSort = new BubbleSort(data);
            MergeSort mergeSort = new MergeSort(data);
            QuickSort quickSort = new QuickSort(data);

            threadPool.execute(selectionSort);
            threadPool.execute(bubbleSort);
            threadPool.execute(mergeSort);
            threadPool.execute(quickSort);

            threadPool.shutdown();

            // Wait until all tasks are finished
            while (!threadPool.isTerminated()) {}

            // Print the sorting time output
            System.out.printf("| N: %1$-9d | %2$12dms | %3$11dms | %4$10dms | %5$10dms |\n",
                    size, selectionSort.sortTime, bubbleSort.sortTime, mergeSort.sortTime, quickSort.sortTime);
            System.out.println("+--------------+----------------+---------------+--------------+--------------+");

            size *= 10;
        }
    }
    public static int[] generateRandomData(int n) {
        int[] out = new int[n];
        int i = 0;
        do {
            out[i] = (int) (Math.random() * 100000);
        } while (++i < n);
        return out;
    }
    public static void printIsInOrder(int[] array) {
        System.out.println(Arrays.toString(array) + "\n" + inOrder(array) + "\n");
    }
    public static boolean inOrder(int[] array) {
        int i = 0;
        do {
            if (array[i] > array[i + 1])
                return false;
        } while (++i < array.length - 1);
        return true;
    }
}

