package dk.cphbusiness.algorithm.examples.queues;

import dk.cphbusiness.airport.template.Category;
import dk.cphbusiness.airport.template.Passenger;
import dk.cphbusiness.airport.template.Plane;
import dk.cphbusiness.airport.template.Time;

import java.util.NoSuchElementException;

public class PassengerPriorityQueue implements PriorityQueue<Passenger> {
    private final Passenger[] items;
    private int size = 0;
    private int head = 0; // index of the current front item, if one exists
    private int tail = 0; // index of next item to be added

    public PassengerPriorityQueue(int capacity) {
        items = new Passenger[capacity];

    }


    public void enqueue(Passenger item) {
        if(size == 0){
            items[tail] = item;
            tail = (tail + 1) % items.length;
            size++;
        }
        if (size == items.length)
            throw new IllegalStateException("Cannot add to full queue");
        for(int i = size-1; i >= 0; i--){
            if(items[i].getCategory().ordinal() > item.getCategory().ordinal()){
                items[i+1] = items[i];
            } else break;
        }
        items[tail] = item;
        tail = (tail + 1) % items.length;
        size++;
    }

    public Passenger dequeue() {
        if (size == 0)
            throw new NoSuchElementException("Cannot remove from empty queue");
        Passenger item = items[head];
        items[head] = null;
        head = (head + 1) % items.length;
        size--;
        return item;
    }

    public Passenger peek() {
        if (size == 0)
            throw new NoSuchElementException("Cannot peek into empty queue");
        return items[head];
    }

    public int size() {
        return size;
    }

    public void sort(Passenger[] inputArr){
        int n = size();
        Sort(inputArr, head, n);
    }


    @Override
    public void Sort(Passenger[] inputArr, int low, int high) {
        if(low < high) {
            int middle = (low + high)/ 2;
            Sort(inputArr, low, middle);
            Sort(inputArr, middle+1, high);
            merge(inputArr, low, middle, high);
        }
    }
    public void merge(Passenger[] arr, int low, int mid, int high){
        if(size() == 1) return;
        int lengthforLeftArr = (mid - low) + 1;
        int lengthforRightArr = (high - mid);

        Passenger[] left = new Passenger[lengthforLeftArr];
        Passenger[] right = new Passenger[lengthforRightArr];

        for(int i = 0; i < left.length; i++){
            left[i] = arr[low + i];
        }

        for(int j = 0; j < right.length; j++){
            right[j] = arr[mid + 1 + j];
        }

        int j = 0, i = 0, k = low;

        while(i < lengthforLeftArr && j < lengthforRightArr) {
            if (left[i].getCategory().compareTo(right[j].getCategory()) <= 0) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
        }
    }
}
