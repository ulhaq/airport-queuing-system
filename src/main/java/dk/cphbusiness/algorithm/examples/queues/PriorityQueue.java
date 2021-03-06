package dk.cphbusiness.algorithm.examples.queues;

public interface PriorityQueue<T extends Comparable<T>> extends Queue<T> {
    void Sort(T[] inputArr, int low, int high);
  }
