/* Blake Impecoven - May, 2016 - CSCD320 - Programming Assignment 2
 * Min-Heap data structure,
 * Implemented with 1-Based indexing.
**/

public class Min_Heap {

    protected int[] heap;
    protected int size;
    protected int maximum;

    public Min_Heap(final int maximum) {
        this.maximum = maximum;
        this.size = 0;
        this.heap = new int[maximum + 1];
        this.heap[0] = Integer.MIN_VALUE;
    }//end constructor

    /*
     * Some functions to help us more easily access parent and children nodes
    **/
    public int parent(final int index) {
        return index / 2;
    }//end parent

    public int leftChild(final int index) {
        return index * 2;
    }//end leftChild

    public int rightChild(final int index) {
        return (index * 2) + 1;
    }//end rightChild

    public boolean leaf(int index) {
        if(index <= this.size && index >= (this.size / 2)) {
            return true;
        }//end if
        else {
            return false;
        }//end else
    }//end leaf

    public void swap(final int first, final int second) {
        int hldr = this.heap[first];
        this.heap[first] = this.heap[second];
        this.heap[second] = hldr;
    }//end swap

    public void minHeapify(final int index) {
        if(!leaf(index)) {
            if(this.heap[index] > this.heap[leftChild(index)] || this.heap[index] > this.heap[rightChild(index)]) {
               if(this.heap[index] > this.heap[leftChild(index)]) {//check to see if left child is smaller
                   swap(index, leftChild(index));
                   minHeapify(leftChild(index));
               }//end if
               if(this.heap[index] > this.heap[rightChild(index)]) {//check to see if right child is smaller
                   swap(index, rightChild(index));
                   minHeapify(rightChild(index));
               }//end elseif
            }//end if
        }//end if
    }//end minHeapify
    
    public void maxHeapify(final int index) {
        if(!leaf(index)) {
           if(this.heap[index] < this.heap[leftChild(index)] || this.heap[index] < this.heap[rightChild(index)]) {
               if(this.heap[index] < this.heap[leftChild(index)]) {//check to see if left child is smaller
                   swap(index, leftChild(index));
                   maxHeapify(leftChild(index));
               }//end if
               if(this.heap[index] < this.heap[rightChild(index)]) {//check to see if right child is smaller
                   swap(index, rightChild(index));
                   maxHeapify(rightChild(index));
               }//end if
            }//end if
        }//end if
    }//end minHeapify

    public void insert(final int value) {
        this.size++;//increment our size and then put it at the end of the heap
        this.heap[size] = value;//last element of the heap
        int cur = this.size;

        if(this.size > 1){
           //while our new value is smaller than its parent, we are going to swap them
           while(this.heap[cur] < heap[parent(cur)]) {
               swap(cur, parent(cur));
               cur = parent(cur);//continuing to move up the heap
           }//end while
        }//end if
    }//end insert

    public void createMaxHeap() {
        for(int pos = (size / 2); pos >= 1; pos--) {
            maxHeapify(pos);
        }//end for
    }//end createMinHeap

    public void delete(final int index) {
        this.heap[index] = this.heap[this.size];
        this.size--;
        minHeapify(index);
    }//end delete
    
    public void heapSort() {
         for(int x = this.size; x >= 2 ; x--) {
            swap(1, x);
            maxHeapify(1);
            this.size--;
         }//end for
    }//end heapSort

}//end class