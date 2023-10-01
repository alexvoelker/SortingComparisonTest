class MergeSort implements Runnable {
    int[] data;
    long sortTime;

    public MergeSort(int[] array) {
        this.data = array.clone();
    }

    public void run() {
        this.sort();
    }

    /**
     * Sort the data, and update the time taken to sort
     */
    private synchronized void sort() {
        long start = System.currentTimeMillis();

//            Sorting algorithm here

        this.sortTime = System.currentTimeMillis() - start;
    }
}
