class SelectionSort implements Runnable {
    int[] data;
    long sortTime;

    public SelectionSort(int[] array) {
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

        for (int i = 0; i < data.length; i++) {
            for (int j = i; j < data.length; j++) {
                if (data[i] > data[j]) {
                    int temp = data[j];
                    data[j] = data[i];
                    data[i] = temp;
                }
            }
        }

        this.sortTime = System.currentTimeMillis() - start;
    }
}
