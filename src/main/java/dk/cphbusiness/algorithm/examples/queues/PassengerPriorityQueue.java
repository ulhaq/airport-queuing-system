package dk.cphbusiness.algorithm.examples.queues;

import dk.cphbusiness.airport.template.Passenger;

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
        if (size == items.length)
            throw new IllegalStateException("Cannot add to full queue");
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
//        System.out.println("popped item from queue" + item);

        Sort(items);
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

    @Override
    public void Sort(Passenger[] inputArr) {
        for (int i = size() / 2 - 1; i >= 0; i--) {
            this.Heapify(inputArr, size(), i);
        }

        for (int i = size() - 1; i >= 0; i--) {
            Passenger temp = inputArr[0];
            inputArr[0] = inputArr[i];
            inputArr[i] = temp;

            Heapify(inputArr, i, 0);
        }
    }

    public void Heapify(Passenger[] inputArr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (size() == 1) return;

        if (left < n && inputArr[left].getCategory().compareTo(inputArr[largest].getCategory()) > 0) {
            largest = left;
        }
        if (right < n && inputArr[right].getCategory().compareTo(inputArr[largest].getCategory()) > 0) {
            largest = right;
        }

        if (largest != i) {
            Passenger swap = inputArr[i];
            inputArr[i] = inputArr[largest];
            inputArr[largest] = swap;
            Heapify(inputArr, n, largest);
        }
    }

}
