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

        quickSort(this.data, 0, this.data.length - 1);

        this.sortTime = System.currentTimeMillis() - start;
    }

    private void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int part = partition(array, left, right);
            quickSort(array, left, part - 1);
            quickSort(array, part + 1, right);
        }
    }

    private int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[right];
        array[right] = temp;
        return i + 1;
    }
}
