class BubbleSort implements Runnable {
    int[] data;
    long sortTime;

    public BubbleSort(int[] array) {
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

        //      Sorting algorithm here
        boolean wasSwapped;
        int temp, i, j;
        for (i = 0; i < data.length; i++) {
            wasSwapped = false;
            for (j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    wasSwapped = true;
                }
            }
            if (!wasSwapped)
                break;
        }
        this.sortTime = System.currentTimeMillis() - start;
    }
}
