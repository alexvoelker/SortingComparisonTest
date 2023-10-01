class QuickSort implements Runnable {
    int[] data;
    long sortTime;

    public QuickSort(int[] array) {
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



        this.sortTime = System.currentTimeMillis() - start;
    }
}
