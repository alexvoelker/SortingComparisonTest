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

        mergeSort(this.data, 0, this.data.length - 1);

        this.sortTime = System.currentTimeMillis() - start;
    }

    private synchronized void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;

            // Sort the left and right halves of the array
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            // Merge the sorted array back together again
            merge(array, left, middle, right);
        }
    }
    private synchronized void merge(int[] array, int left, int middle, int right) {
        // Create two aub-arrays for each section of the array to be merged together
        int sizeLeft = middle - left + 1;
        int sizeRight = right - middle;

        int[] leftSubArray = new int[sizeLeft];
        int[] rightSubArray = new int[sizeRight];

        System.arraycopy(array, left, leftSubArray, 0, sizeLeft);
        System.arraycopy(array, middle + 1, rightSubArray, 0, sizeRight);

        // With the sub-arrays made, combine them with elements in order
        int indexLeftSub = 0;
        int indexRightSub = 0;
        int indexCombinedArray = left;

        while (indexLeftSub < sizeLeft && indexRightSub < sizeRight) {
            if (leftSubArray[indexLeftSub] < rightSubArray[indexRightSub]) {
                array[indexCombinedArray] = leftSubArray[indexLeftSub];
                indexLeftSub++;
            } else {
                array[indexCombinedArray] = rightSubArray[indexRightSub];
                indexRightSub++;
            }
            indexCombinedArray++;
        }

        // There could be elements from one of the sub-arrays left over
        while (indexLeftSub < sizeLeft) {
            array[indexCombinedArray] = leftSubArray[indexLeftSub];
            indexCombinedArray++;
            indexLeftSub++;
        }

        while (indexRightSub < sizeRight) {
            array[indexCombinedArray] = rightSubArray[indexRightSub];
            indexCombinedArray++;
            indexRightSub++;
        }
    }
}
